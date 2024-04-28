package com.jetpacker06.bd1.command.commands.common;

import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CoinFlipCommand extends Command {

    @Override
    public String getName() {
        return "coinflip";
    }
    @Override
    public String getDescription() {
        return "Perform a coin flip";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply(Util.coinFlip() ? "Heads" : "Tails").queue();
    }

}
