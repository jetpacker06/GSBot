package com.jetpacker06.bd1.event;

import com.jetpacker06.bd1.util.entity.entities.Channels;
import com.jetpacker06.bd1.util.entity.entities.UserIDs;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class VCEvents extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        ensureAllowedInChannel(event.getMember(), event.getChannelJoined(), event.getGuild());
    }

    @Override
    public void onGuildVoiceMove(@NotNull GuildVoiceMoveEvent event) {
        ensureAllowedInChannel(event.getMember(), event.getChannelJoined(), event.getGuild());
    }

    private static void ensureAllowedInChannel(Member member, AudioChannel channel, Guild guild) {
        if (member.getIdLong() == UserIDs.cody & channel == Channels.testNoCody) {
            guild.kickVoiceMember(member).queue();
        }
        if (member.getIdLong() == UserIDs.lawrence & channel == Channels.noLawrence) {
            guild.kickVoiceMember(member).queue();
        }
    }
}
