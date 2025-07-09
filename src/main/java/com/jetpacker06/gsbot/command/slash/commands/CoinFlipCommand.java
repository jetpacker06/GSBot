package com.jetpacker06.gsbot.command.slash.commands;

import com.jetpacker06.gsbot.command.slash.Command;
import com.jetpacker06.gsbot.util.Util;
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
