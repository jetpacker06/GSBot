package com.jetpacker06.thebotever.command.commands.basic;

import com.jetpacker06.thebotever.command.commands.Command;
import com.jetpacker06.thebotever.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PreciseCommand extends Command {
    @Override
    public String getName() {
        return "precise";
    }
    @Override
    public String getDescription() {
        return "Very precise calculations.";
    }

    @Override
    public boolean forFriendsOnly() {
        return false;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.replyEmbeds(Util.createImageEmbed(gif_link)).queue();
    }


    public static String gif_link = "https://media.tenor.com/t5xwKNKc2cIAAAAd/very-very-precise-steve-kornacki.gif";
}
