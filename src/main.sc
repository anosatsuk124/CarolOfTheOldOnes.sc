(
    "../deps.sc".loadRelative();
    "./music.sc".loadRelative();
    s.boot();
)

~player.(~bpm);

// MIDI Boot
(
    MIDIClient.init;
    MIDIClient.destinations();
    ~midi = MIDIOut.new(0);
    ~midi.latency = 0;
)

~midiPlayer.(~bpm);
