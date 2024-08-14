(
    "./deps.sc".loadRelative();
    "./src/music.sc".loadRelative();

    r = Routine {
        var cond = Condition.new;
        var path;

        Dialog.savePanel({|p| 
            path = p.asString();
            cond.unhang();
        }, path: "./".resolveRelative);

        cond.hang();
        
        ~path = path;

        f = { |score, prefix|
            var path, name;
            var md;

            path = PathName.new(~path.asString());
            name = prefix ++ "_" ++ path.fileNameWithoutExtension();

            path = path.parentPath() +/+ (name ++ ".mid");
            path = path.asString();

            md = SimpleMIDIFile(path);
            md.init0(60, "3/4"); // NOTE: 60 ticks needed for 1/4 note by Pbind \dur
            md.timeMode = \ticks; // NOTE:
            md.fromPattern(score);
            md.setTempo(160); // NOTE: Set 160 BPM as a metadata
            md.write();
        };
    
        f.(~songScore, "song");
        f.(~melody, "melody");
        f.(~chords, "chords");
    };

    // For GUI to work
    AppClock.play(r);
)
