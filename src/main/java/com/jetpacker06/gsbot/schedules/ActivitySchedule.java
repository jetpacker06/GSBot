package com.jetpacker06.gsbot.schedules;

import com.jetpacker06.gsbot.GSBot;
import com.jetpacker06.gsbot.util.Util;
import net.dv8tion.jda.api.entities.Activity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ActivitySchedule {

    private static final String[] bands = {
            "P.O.D.",
            "Skillet",
            "Flyleaf",
            "Dream Theater",
            "TOOL",
            "Black Tide",
            "Trivium",
            "Rush",
            "Judas Priest",
            "Priestess",
            "Stryper",
            "Pearl Jam",
            "Creed",
            "Foo Fighters",
    };

    public static void init() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            GSBot.jda.getPresence().setActivity(Activity.listening(Util.randomFromArray(bands)));
        }, 0, 300, TimeUnit.SECONDS);
    }
}
