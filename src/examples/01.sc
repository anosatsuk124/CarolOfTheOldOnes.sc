~long_fn = { |long|
    Pbind(
        \degree, Pseq([0, 1, 2, 3], long),
        \dur, Pseq([0.5, 0.25, 0.25, 0.5], long),
    ).play();
};

// ~long_fn.(10);

~fn = { | short |
    Pbind(
        \degree, Pseq([0, 1, 2, 3], short),
        \dur, Pseq([0.5, 0.25, 0.25, 0.5], short),
    ).play();
};

// ~fn.(3);

{
    var stream = Pseq([3, 4, 5, 6], inf).asStream;
    10.do({
        stream.next().postln;
    });
};//.value()

~bssA = {
    SynthDef(\bss, { |gate=1, amp=0.1, sustain=1, freq=440|
        var sig, env;
        env = EnvGen.kr(Env.adsr, gate, amp, 0, sustain, 2);
        // ２つのノコギリ波をmixし少し歪みを加える
        sig = Mix(Saw.ar([freq,freq+0.1])).tanh;
        sig = RLPF.ar(sig, 1000, 0.8);
        sig = Pan2.ar(sig, 0, env);
        Out.ar(0, sig);
    }).add;

    Pbind(\instrument, \bss, 
        \dur, Pseq([//　注１：入れ子状
            Pseq([1.5, 1.5, 1.5, 1.5, 1, 1, 2, 0.5, 1, 2.5, 1, 1], 1), 
            Pseq([1.5, 1.5, 1.5, 1.5, 1, 1, 2, 2, 2, 1, 1], 1), 
        ], 1),//　注２：有限パターン（１回のみ）
        \legato, 0.7, 
        \amp, 0.6, 
        \scale, [2, 4, 5, 7, 9, 10, 12], //　注３：レ、ミ、ファ、ソ、ラ、シb、ド
        \degree, Pseq([
            Pseq([0, 1, 2, 0, 6, 2, 5, \, 4, 3, 5, 4], 1),//　注４：ゼロ度始まり
            Pseq([0, 1, 2, 0, 6, 2, 5, 5, 5, 5, 4], 1)
        ], inf),
        \octave, Pseq([
            Pseq([3, 3, 3, 3, 2, 3, 2, 2, 2, 2, 2, 2], 1),//　注５
            Pseq([3, 3, 3, 3, 2, 3, 2, 2, 2, 2, 2], 1)
        ], inf)
    )
}.();

// ~bssA.play(TempoClock(80/60)); // 80bpm

~bpm = { |num|
    TempoClock(num/60)
};

// ~bssA.play(~bmp.(80)); // 80bpm

{
    "one thing".postln;
    2.wait;
    "別のもの".postln;
    1.5.wait;
    "最後の1つ".postln;
}; //.fork(); 

~forkPlay = {
    t = TempoClock(76/60); 
    {
        Pbind( 
            \note,Pseq([[4,11],[6,9]],32),
            \dur,1/6,
            \amp,Pseq([0.05,0.03],inf)
        ).play(t);
        2.wait;

        Pbind(
            \note,Pseq([[-25,-13,-1],[-20,-8,4 ],\rest],3), 
            \dur,Pseq([1,1,Rest( 1)],inf),
            \amp,0.1,
            \legato,Pseq([0.4,0.7,\rest],inf)
        ).play(t);
        2.75.wait;

        Pbind(
            \note,Pseq([23,21,25,23,21,20,18,16,16,20,21,23,21],inf),
            \dur,Pseq([0.25,0.75,0.25,1.75,0.125,0.125,0.80,0.20,0.125,0.125,1],1),
            \amp,0.1,
            \legato,0.5
        ).play(t);
    }.fork(t);
};
