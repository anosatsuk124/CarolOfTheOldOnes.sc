(
    "../deps.sc".loadRelative();
    "./music.sc".loadRelative();
    s.boot();
    
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
        ~song.play(bpm);
        ~melody.play(bpm);
        ~chords.play(bpm);
    };
)
~bpm = TempoClock.new(80/60);

~player.(~bpm);
~song.play(~bpm);
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
