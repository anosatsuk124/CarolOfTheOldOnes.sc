(
    s.boot();

    "../deps.sc".loadRelative();
    "./music.sc".loadRelative();
    
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
        fork {
            ~songScore.play(bpm);
            ~melody.play(bpm);
            ~chords.play(bpm);

            //~song.play();
        }.play;
    };
)
~bpm = TempoClock.new(60/60);

~player.(~bpm);
~songScore.play(~bpm);
~chords.play(~bpm);
~melody.play();

Scale.directory()

// MIDI Boot
(
    MIDIClient.init;
    MIDIClient.destinations();
    ~midi = MIDIOut.new(0);
    ~midi.latency = 0;
)

~midiPlayer.(~bpm);
