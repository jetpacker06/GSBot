package com.jetpacker06.gsbot.command.slash.commands;

import com.jetpacker06.gsbot.command.slash.Command;
import com.jetpacker06.gsbot.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class MarcusGifCommand extends Command {

    @Override
    public String getName() {
        return "marcus";
    }
    @Override
    public String getDescription() {
        return "marcus?";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.replyEmbeds(Util.createImageEmbed(Util.randomFromArray(gifs))).queue();
    }

    private static final String[] gifs = {
            "https://c.tenor.com/rDaAtQz6TO8AAAAC/tenor.gif",
            "https://c.tenor.com/XfshzCLh8-AAAAAd/tenor.gif",
            "https://c.tenor.com/MWj07HHVQ10AAAAd/tenor.gif",
            "https://c.tenor.com/BvzmGt0T9owAAAAd/tenor.gif",
            "https://c.tenor.com/LOGpy5nd5cQAAAAd/tenor.gif",
            "https://media.tenor.com/trAiZtre4pcAAAAM/marcus-marcus-the-worm.gif"
    };

}
