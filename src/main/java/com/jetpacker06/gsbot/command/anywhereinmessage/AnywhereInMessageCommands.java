package com.jetpacker06.gsbot.command.anywhereinmessage;

import com.jetpacker06.gsbot.util.entity.entities.UserIDs;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AnywhereInMessageCommands {
    public static HashMap<String, AnywhereInMessageCommandTask> anywhereInMessageCommands = new HashMap<>();

    public static void put(String command, AnywhereInMessageCommandTask task) {
        anywhereInMessageCommands.put(command, task);
    }

    public static void init() {
        put("marcus", event -> event.getMessage().reply("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfoIliIJgOPKgk7OMW0g9UReRSvTL-xuXfTA&s").queue());
        put("clanker", event -> event.getMessage().reply("https://c.tenor.com/8-NM974OdjMAAAAd/tenor.gif").queue());
        put("the thing", event -> event.getMessage().reply("https://c.tenor.com/ls5WZFRO1xUAAAAd/tenor.gif").queue());
        put("let me break it down for you", event -> event.getMessage().reply("https://c.tenor.com/9fjEY93SqSMAAAAC/tenor.gif").queue());
        put("doorbob", event -> event.getMessage().reply("https://tenor.com/view/mnewsradio-newsradio-news-radio-wnyx-gif-24310376").queue());
        put("layla", event -> event.getMessage().reply("https://tenor.com/view/eric-clapton-derek-and-the-dominoes-iggydr-rock-80s-gif-18838124").queue());
        put("fantastic", (event) -> {

            if (event.getAuthor().getIdLong() == UserIDs.BRYSON) {
                return;
            }
            event.getMessage().reply("https://c.tenor.com/56CE99HqWyQAAAAd/tenor.gif").queue();

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> {
                event.getChannel().sendMessage("https://c.tenor.com/kuBxGM0yU98AAAAd/tenor.gif").queue();
                scheduler.shutdown();
            }, 5, TimeUnit.SECONDS);
        });
    }
}
