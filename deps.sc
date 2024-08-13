(
    var deps = [
        "ddwMIDI",
        "wslib",
    ];

    deps.do({ |dep|
        if( not(Quarks.isInstalled(dep)), {
            Quarks.install(dep);
        });
    });
)

