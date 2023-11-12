package com.jetpacker06.bd1.command;

import com.jetpacker06.bd1.BD1;
import com.jetpacker06.bd1.command.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.ArrayList;
import java.util.Set;

import static com.jetpacker06.bd1.BD1.print;
@SuppressWarnings("ResultOfMethodCallIgnored")
public class CommandInit {
    public static ArrayList<CommandData> commandDataArrayList = new ArrayList<>();
    public static void registerSlashCommands() {
        // Get all classes that extend the Command class
        Set<Class<? extends Command>> classes = Commands.getCommandClasses();
        for (Class<? extends Command> clazz : classes) {
            //Create an instance of each of the classes
            try {
                Command c = clazz.getConstructor().newInstance();
                String[] splitName = clazz.getName().split("\\.");
                Commands.commands.put(c.getName(), c);
                //print("Loaded " + splitName[splitName.length - 1]);
            } catch (Exception ignored) {}
        }
        CommandListUpdateAction commandListUpdateAction = BD1.jda.updateCommands();
        commandListUpdateAction.addCommands(commandDataArrayList);
        commandListUpdateAction.queue();
    }
}
