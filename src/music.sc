(
    // ~time4 = [1/3, (1/3)/2.dup(2), 1/3].flatten;
    ~time4 = [1/3, (1/3)/2, (1/3)/2, 1/3].flatten;
    ~time5 = [1/3, (1/3)/2.dup(4)].flatten;

    ~melody = {
        m = [0, -1, 0, -2];
        Pbind(
            \degree, Pseq([
                Pseq(m, 16),
                Pseq(m + 2, 4), //NOTE: because it's degree

                Pseq([5.dup(3), 4, 3].flatten),
                Pseq([2.dup(3), 1, 0].flatten),
                Pseq([1.dup(3), 2, 1].flatten),
                Pseq([0, -2.dup(3), \].flatten),
            ]),
            \dur, 
            Pseq([
                Pseq(~time4, 16),
                Pseq(~time4, 4),

                Pseq(~time5.dup(4).flatten),
            ]),
            \legato, 0.3,
            \amp, 0.2,
        );
    }.();

    ~song = {
        m = [0, 0, -1, 0, -2];

        ~time5_s = [(1/3)/2, (1/3)/2, (1/3)/2, (1/3)/2, 1/3];

        Pbind(
            \degree, Pseq([
                Pseq(m, 16),
                Pseq(m + 2, 4), //NOTE: because it's degree
            ]),
            \dur, 
            Pseq([
                Pseq(~time5_s, 16),
                Pseq(~time5_s, 4),
            ]),
            \legato, 0.8,
            \amp, 0.5,
        );
    }.();
    
    ~chords = {
        Pbind(
            \degree, Pseq([
                Rest(4),
                3,
                2,
                1,
                0,
                1,
                0,
                -1,
            ] - 7),
            \dur, Pseq([
                1,
            ], inf),
            \amp, 0.25,
        );
    }.();
)
