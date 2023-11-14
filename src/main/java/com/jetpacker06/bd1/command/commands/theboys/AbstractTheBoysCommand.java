package com.jetpacker06.bd1.command.commands.theboys;

import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.util.entity.entities.Guilds;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Objects;

public abstract class AbstractTheBoysCommand extends Command {
    @Override
    public boolean correctContext(SlashCommandInteractionEvent event) {
        return event.isFromGuild() && Objects.equals(event.getGuild(), Guilds.theBoys);
    }
}
