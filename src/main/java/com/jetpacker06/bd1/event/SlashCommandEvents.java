package com.jetpacker06.bd1.event;

import com.jetpacker06.bd1.command.commands.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SlashCommandEvents extends ListenerAdapter {
    public static HashMap<String, Command> commands = new HashMap<>();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String name = event.getName();
        if (!commands.containsKey(name)) return;

        Command command = commands.get(name);
        if (command.checkContext(event)) {
            command.execute(event);
        }
        else {
            command.executeForIncorrectContext(event);
        }
    }
}
