package com.jetpacker06.bd1.command;

import com.jetpacker06.bd1.BD1;
import com.jetpacker06.bd1.event.EventHandler;
import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.command.commands.common.*;
import com.jetpacker06.bd1.command.commands.jetpackhub.RoleRequestCommand;
import com.jetpacker06.bd1.command.commands.theboys.GiveEditorCommand;
import com.jetpacker06.bd1.command.commands.theboys.PlanCommand;
import com.jetpacker06.bd1.command.commands.theboys.SaveMeCommand;
import com.jetpacker06.bd1.event.SlashCommandEvents;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.ArrayList;
@SuppressWarnings("ResultOfMethodCallIgnored")
public class CommandRegistry {
    public static ArrayList<CommandData> commandDataArrayList = new ArrayList<>();
    public static void registerSlashCommands() {
        put(new EmoteCommand());
        put(new FromStacksCommand());
        put(new PreciseCommand());
        put(new ThinkCommand());
        put(new ToStacksCommand());
        put(new MarieCommand());
        put(new RoleMenuCommand());
        put(new ChatIsThisRealCommand());
        put(new CoinFlipCommand());

        put(new GiveEditorCommand());
        put(new SaveMeCommand());
        put(new PlanCommand());

        put(new RoleRequestCommand());

        CommandListUpdateAction commandListUpdateAction = BD1.jda.updateCommands();
        commandListUpdateAction.addCommands(commandDataArrayList);
        commandListUpdateAction.queue();
    }
    private static void put(Command command) {
        SlashCommandEvents.commands.put(command.getName(), command);
    }
}
