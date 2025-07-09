package com.jetpacker06.gsbot.command.slash.commands;

import com.jetpacker06.gsbot.command.slash.Command;
import com.jetpacker06.gsbot.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HowToJoinMinecraftCommand extends Command {

    @Override
    public String getName() {
        return "howtojoinminecraft";
    }
    @Override
    public String getDescription() {
        return "How do I join the Minecraft realm?";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.replyEmbeds(Util.createInstructionalEmbed(
                "How to join the Minecraft realm",
                "Make sure you're signed into Minecraft with an Xbox Live account/Microsoft account",
                "Click the invite link https://realms.gg/tA3hTrhNM8WCSvE or enter the realm code manually: tA3hTrhNM8WCSvE"
        )).queue();
    }

}
