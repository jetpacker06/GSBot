package com.jetpacker06.gsbot.command.slash.commands;

import com.jetpacker06.gsbot.command.slash.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ThinkCommand extends Command {
    @Override
    public String getName() {
        return "think";
    }
    @Override
    public String getDescription() {
        return "Hmm...";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
    }
}
