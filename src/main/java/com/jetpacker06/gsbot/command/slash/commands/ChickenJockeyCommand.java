package com.jetpacker06.gsbot.command.slash.commands;

import com.jetpacker06.gsbot.command.slash.Command;
import com.jetpacker06.gsbot.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ChickenJockeyCommand extends Command {

    @Override
    public String getName() {
        return "chickenjockey";
    }
    @Override
    public String getDescription() {
        return "chicken jockey?";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.replyEmbeds(Util.createImageEmbed("https://c.tenor.com/IB3gNH9R59YAAAAC/tenor.gif")).queue();
    }

}
