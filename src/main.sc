(
    "../deps.sc".loadRelative();
    "./music.sc".loadRelative();
)

s.boot();

// MIDI Boot
(
    MIDIClient.init;
    MIDIClient.destinations();
    ~midi = MIDIOut.new(0);
    ~midi.latency = 0;
)

~player.(~bpm);

~midiPlayer.(~bpm);
