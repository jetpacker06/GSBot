package com.jetpacker06.bd1.command.commands.common;

import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ChatIsThisRealCommand extends Command {

    @Override
    public String getName() {
        return "chatisthisreal";
    }
    @Override
    public String getDescription() {
        return "Perform a fact check";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.replyEmbeds(Util.createImageEmbed(Util.coinFlip() ? "https://media1.tenor.com/m/YrQwgyXfLGYAAAAC/so-true-fact-checked.gif" : "https://media1.tenor.com/m/I68b_om3MrEAAAAC/cat.gif")).queue();
    }

}
