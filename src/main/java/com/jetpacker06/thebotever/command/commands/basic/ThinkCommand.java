package com.jetpacker06.thebotever.command.commands.basic;

import com.jetpacker06.thebotever.command.commands.Command;
import com.jetpacker06.thebotever.util.Util;
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
    public boolean forFriendsOnly() {
        return false;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
    }
}
