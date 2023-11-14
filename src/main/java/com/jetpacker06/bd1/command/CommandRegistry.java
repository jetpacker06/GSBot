package com.jetpacker06.bd1.command;

import com.jetpacker06.bd1.BD1;
import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.command.commands.basic.*;
import com.jetpacker06.bd1.command.commands.jetpackhub.RoleRequestCommand;
import com.jetpacker06.bd1.command.commands.theboys.GiveEditorCommand;
import com.jetpacker06.bd1.command.commands.theboys.PlanCommand;
import com.jetpacker06.bd1.command.commands.theboys.SaveMeCommand;
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

        put(new GiveEditorCommand());
        put(new SaveMeCommand());
        put(new PlanCommand());

        put(new RoleRequestCommand());

        CommandListUpdateAction commandListUpdateAction = BD1.jda.updateCommands();
        commandListUpdateAction.addCommands(commandDataArrayList);
        commandListUpdateAction.queue();
    }
    private static void put(Command command) {
        EventHandler.commands.put(command.getName(), command);
    }
}
