package com.jetpacker06.thebotever.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

@FunctionalInterface
public interface CommandTask {
    void run(SlashCommandInteractionEvent event);
}
