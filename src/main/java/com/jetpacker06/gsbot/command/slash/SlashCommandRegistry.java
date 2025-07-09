package com.jetpacker06.gsbot.command.slash;

import com.jetpacker06.gsbot.GSBot;
import com.jetpacker06.gsbot.command.slash.commands.*;
import com.jetpacker06.gsbot.event.SlashCommandEvents;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.ArrayList;
@SuppressWarnings("ResultOfMethodCallIgnored")
public class SlashCommandRegistry {
    public static ArrayList<CommandData> commandDataArrayList = new ArrayList<>();
    public static void init() {
        put(new FromStacksCommand());
        put(new ThinkCommand());
        put(new ToStacksCommand());
        put(new CoinFlipCommand());
        put(new HowToJoinMinecraftCommand());
        put(new MarcusGifCommand());
        put(new ChickenJockeyCommand());
        put(new BreakdanceCommand());

        CommandListUpdateAction commandListUpdateAction = GSBot.jda.updateCommands();
        commandListUpdateAction.addCommands(commandDataArrayList);
        commandListUpdateAction.queue();
    }
    private static void put(Command command) {
        SlashCommandEvents.commands.put(command.getName(), command);
    }

}
