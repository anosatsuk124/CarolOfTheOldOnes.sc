(
    "./deps.sc".loadRelative();
    "./src/music.sc".loadRelative();

    r = Routine {
        var cond = Condition.new;
        var path;

        Dialog.savePanel({|p| 
            path = p.asString();
            cond.unhang();
        });

        cond.hang();
        
        ~path = path;
    };

    // For GUI to work
    AppClock.play(r);
)

(
    f = { |score, prefix|
        var path, name;
        var md;
        var cond = Condition.new;

        path = PathName.new(~path.asString());
        name = prefix ++ "_" ++ path.fileNameWithoutExtension();

        path = path.parentPath() +/+ (name ++ ".mid");
        path = path.asString();

        md = SimpleMIDIFile(path);
        md.init1(2, 60, "3/4"); // Minimum track is 2
        md.fromPattern(score);
        md.write();
    };
    
    f.(~song, "song");
    f.(~melody, "melody");
    f.(~chords, "chords");
)
