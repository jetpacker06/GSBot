package com.jetpacker06.gsbot.command.slash.commands;

import com.jetpacker06.gsbot.command.slash.Command;
import com.jetpacker06.gsbot.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class FactCheckCommand extends Command {
    @Override
    public String getName() {
        return "factcheck";
    }

    @Override
    public String getDescription() {
        return "Request a fact check from Marcus";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply(Util.coinFlip() ? "https://github.com/jetpacker06/GSBot/blob/master/assets/false.png?raw=true" : "https://github.com/jetpacker06/GSBot/blob/master/assets/true.png?raw=true").queue();
    }
}
