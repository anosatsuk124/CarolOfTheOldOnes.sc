(
    forkPlay1: {
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
    },
)