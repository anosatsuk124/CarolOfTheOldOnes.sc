s.boot();

// MIDI Boot
(
    MIDIClient.init;
    MIDIClient.destinations();
    ~midi = MIDIOut.new(0);
)

(
    var u = "./utils/mod.sc".loadRelative[0];
    ~time4 = [1/3, (1/3)/2.dup(2), 1/3].flatten;
    ~time5 = [1/3, (1/3)/2.dup(4)].flatten;
    ~bpm = TempoClock(80/60);

    ~melody = {
        m = [0, -1, 0, -2];
        Pbind(
            \degree, Pseq([
                Pseq(m, 16),
                Pseq(m + 1, 4),

                Pseq([2.5.dup(3), 2, 1.5].flatten),
                Pseq([1.dup(3), 1.5, 1].flatten),
                Pseq([-0.5.dup(3), 0, -0.5].flatten),
                Pseq([-1, -2.dup(3), \].flatten),
            ], 1),
            \dur, Pseq([
                Pseq(~time4, 16),
                Pseq(~time4, 4),

                Pseq(~time5.dup(4).flatten, 1),
            ]),
            \amp, 0.5,
        );
    }.();
    
    ~chords = {
        Pbind(
            \type, \midi,
            \midicmd, \noteOn,
            \midiout, ~midi,
            \chan, 0,
            \degree, Pseq([
                Rest(4),
                2.5,
                2,
                1.5,
                1,
                1.5,
                1,
                0.5,
            ]),
            \dur, Pseq([
                1,
            ], inf),
            \amp, 0.25,
        );
    }.();
    
    ~midiOpts = { |channel, midiOut|
        (type: \midi,
        midicmd: \noteOn,
        midiout: midiOut,
        chan: channel);
    };
    
    ~midiPlay = { |bpm|
        (~melody <> ~midiOpts.(0, ~midi)).play(bpm);
        (~chords <> ~midiOpts.(1, ~midi)).play(bpm);
    };
    
    ~play = { |bpm|
        ~melody.play(bpm);
        ~chords.play(bpm);
    };
    
    // ~midiPlay.(~bpm);
    ~play.(~bpm);
)
