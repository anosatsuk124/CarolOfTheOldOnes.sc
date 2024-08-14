(
    // ~time4 = [1/3, (1/3)/2.dup(2), 1/3].flatten;
    ~time4 = [1, (1)/2, (1)/2, 1].flatten;
    ~time5 = [1, (1)/2.dup(4)].flatten;

    ~melody = {
        m = [0, -1, 0, -2];
        Pbind(
            \degree, Pseq([
                \,

                Pseq(m, 16),
                Pseq(m + 2, 4), //NOTE: because it's degree

                Pseq([5.dup(3), 4, 3].flatten),
                Pseq([2.dup(3), 1, 0].flatten),
                Pseq([1.dup(3), 2, 1].flatten),
                Pseq([0, -2.dup(3), \].flatten),
            ]),
            \dur, 
            Pseq([
                3,

                Pseq(~time4, 16),
                Pseq(~time4, 4),

                Pseq(~time5.dup(4).flatten),
            ]),
            \legato, 1.0,
            \amp, 0.2,
        );
    }.();

    ~songScore = {
        m = [0, 0, -1, 0, -2];

        ~time5_s = [(1)/2, (1)/2, (1)/2, (1)/2, 1];

        Pbind(
            \degree, Pseq([
                \,

                Pseq(m, 16),
                Pseq(m + 2, 4), //NOTE: because it's degree
            ]),
            \dur, 
            Pseq([
                3,
                
                Pseq(~time5_s, 16),
                Pseq(~time5_s, 4),
            ]),
            \legato, 0.8,
            \amp, 0.5,
        );
    }.();
    
    ~song = {
        var buf = Buffer.read(s, "../misc/song_14.wav".resolveRelative());
        (PlayBuf.ar(1, buf, 1)).dup(2);
        
        // Pan2.ar(player, 0);
    };
    
    ~chords = {
        Pbind(
            \degree, Pseq([
                \,
                
                Rest(3),
                -2,
                -3,
                -4,
                -5,
                -4,
                -5,
                -6,

                -9,
                [-9, -6],
                [-9, -4],
                [-9, -3],
                [-9, -3],
                [-9, -3],
                [-9, -3.5],
                [-9, -1],
                [-9, -3.5],
                
                -9, // -2 - 7
                -7,
                -6,
                -9,
                -11
                -11,
                -5,
                -5,
                -2,
                -3,
                -4,
                -5,
                -9,
                -9,
                -9,
                -9,
                -9, 
            ]),
            \dur, Pseq([
                3,
            ], inf),
            \legato, 1.25,
            \amp, 0.5,
        );
    }.();
)
