package com.jetpacker06.gsbot.command.slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

@FunctionalInterface
public interface SlashCommandTask {
    void run(SlashCommandInteractionEvent event);
}
