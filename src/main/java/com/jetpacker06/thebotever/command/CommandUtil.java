package com.jetpacker06.thebotever.command;

import com.jetpacker06.thebotever.util.entity.entities.Guilds;
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
        if (!(event.getGuild().equals(Guilds.theBoys) || event.getGuild().equals(Guilds.testServer))) {
            return true;
        }
        return false;
    }
}
