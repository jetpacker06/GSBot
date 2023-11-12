package com.jetpacker06.bd1.command;

import com.jetpacker06.bd1.util.entity.entities.Guilds;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandUtil {
    /**
     * @param event The event.
     * @return False if the command was sent in the main or test servers, true otherwise.
     */
    public static boolean notInCorrectServer(SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild();
        if (guild == null) {
            return true;
        }
        return !(event.getGuild().equals(Guilds.theBoys) || event.getGuild().equals(Guilds.testServer));
    }
}
