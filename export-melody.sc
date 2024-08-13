(
    var path, name;
    var md;
    var cond = Condition.new;

    "./deps.sc".loadRelative();
    "./src/music.sc".loadRelative();

    r = Routine {
        Dialog.savePanel({|p| 
            path = p.asString();
            cond.unhang;
        });

        //NOTE: Stop thread
        cond.hang;

        path = PathName.new(path.asString());
        name = path.fileNameWithoutExtension();

        path = path.parentPath() +/+ (name ++ ".mid");
        path = path.asString();
        path.postln();

        md = SimpleMIDIFile(path);
        md.init1(2, 180, "3/4"); // Minimum track is 2
        md.fromPattern(~song);
        md.plot();
        md.write();
    };
    
    // For GUI to work
    AppClock.play(r);
)