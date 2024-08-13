(
    ~time4 = [1/3, (1/3)/2.dup(2), 1/3].flatten;
    ~time5 = [1/3, (1/3)/2.dup(4)].flatten;
    ~bpm = TempoClock(80/60);

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
            ] + 1),
            \dur, Pseq([
                Pseq(~time4, 16),
                Pseq(~time4, 4),

                Pseq(~time5.dup(4).flatten),
            ]),
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
            ] - 4),
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
    
    ~midiPlayer = { |bpm|
        var channels = [0, 1];

        (~melody <> ~midiOpts.(channels[0], ~midi)).play(bpm);
        (~chords <> ~midiOpts.(channels[1], ~midi)).play(bpm);

        // To unstuck the MIDI
        CmdPeriod.doOnce({
            channels.do({|c|
                ~midi.allNotesOff(c);
            });
        });
    };
    
    ~player = { |bpm|
        ~melody.play(bpm);
        ~chords.play(bpm);
    };
)
