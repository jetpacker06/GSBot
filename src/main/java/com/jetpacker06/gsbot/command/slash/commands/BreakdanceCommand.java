package com.jetpacker06.gsbot.command.slash.commands;

import com.jetpacker06.gsbot.command.slash.Command;
import com.jetpacker06.gsbot.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class BreakdanceCommand extends Command {

    @Override
    public String getName() {
        return "letmebreakitdownforyou";
    }
    @Override
    public String getDescription() {
        return "Let me break it down for you.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.replyEmbeds(Util.createImageEmbed("https://c.tenor.com/9fjEY93SqSMAAAAC/tenor.gif")).queue();
    }

}
