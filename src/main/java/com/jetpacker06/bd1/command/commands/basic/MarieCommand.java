package com.jetpacker06.bd1.command.commands.basic;

import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class MarieCommand extends Command {

    @Override
    public String getName() {
        return "mariequote";
    }
    @Override
    public String getDescription() {
        return "lasagna";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.replyEmbeds(Util.createImageEmbed("https://github.com/jetpacker06/Create-Broken-Bad/blob/1.18/images/marie.png?raw=true")).queue();
    }
}
