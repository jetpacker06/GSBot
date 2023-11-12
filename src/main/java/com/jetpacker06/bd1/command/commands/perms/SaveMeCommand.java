package com.jetpacker06.bd1.command.commands.perms;

import com.jetpacker06.bd1.command.commands.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SaveMeCommand extends Command {
    @Override
    public String getName() {
        return "saveme";
    }

    @Override
    public String getDescription() {
        return "Unmute and undeafen yourself.";
    }

    @Override
    public boolean forFriendsOnly() {
        return true;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (!event.isFromGuild()) {
            return;
        }
        Member member = event.getMember();
        Guild guild = event.getGuild();
        assert member != null;
        assert guild != null;
        try {
            guild.mute(member, false).queue();
            guild.deafen(member, false).queue();
        } catch (IllegalStateException e) {
            event.reply("Must be connected to a voice channel.").setEphemeral(true).queue();
            return;
        }
        event.reply("Done").setEphemeral(true).queue();
    }
}
