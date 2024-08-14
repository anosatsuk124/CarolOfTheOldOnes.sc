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

        f = { |score, prefix|
            var path, name;
            var md;

            path = PathName.new(~path.asString());
            name = prefix ++ "_" ++ path.fileNameWithoutExtension();

            path = path.parentPath() +/+ (name ++ ".mid");
            path = path.asString();

            md = SimpleMIDIFile(path);
            md.init1(2, 80, "4/4"); // Minimum track is 2
            md.fromPattern(score);
            md.write();
        };
    
        f.(~songScore, "song");
        f.(~melody, "melody");
        f.(~chords, "chords");
    };

    // For GUI to work
    AppClock.play(r);
)
