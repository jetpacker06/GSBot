package com.jetpacker06.gsbot.command.anywhereinmessage;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AnywhereInMessageCommands {
    public static HashMap<String, AnywhereInMessageCommandTask> tasks = new HashMap<>();

    public static void init() {
        tasks.put("marcus", event -> event.getMessage().reply("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfoIliIJgOPKgk7OMW0g9UReRSvTL-xuXfTA&s").queue());
        tasks.put("clanker", event -> event.getMessage().reply("https://c.tenor.com/8-NM974OdjMAAAAd/tenor.gif").queue());
        tasks.put("the thing", event -> event.getMessage().reply("https://c.tenor.com/ls5WZFRO1xUAAAAd/tenor.gif").queue());
        tasks.put("let me break it down for you", event -> event.getMessage().reply("https://c.tenor.com/9fjEY93SqSMAAAAC/tenor.gif").queue());
        tasks.put("fantastic", (event) -> {

            event.getMessage().reply("https://c.tenor.com/56CE99HqWyQAAAAd/tenor.gif").queue();

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> {
                event.getChannel().sendMessage("https://c.tenor.com/kuBxGM0yU98AAAAd/tenor.gif").queue();
                scheduler.shutdown();
            }, 5, TimeUnit.SECONDS);
        });
    }
}
