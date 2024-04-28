package com.jetpacker06.bd1.event;

import com.jetpacker06.bd1.BD1;
import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.command.commands.common.RoleMenuCommand;
import com.jetpacker06.bd1.db.DBAccessor;
import com.jetpacker06.bd1.util.Util;
import com.jetpacker06.bd1.util.entity.entities.*;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EventHandler extends ListenerAdapter {
    @Override
    public void onGenericEvent(@NotNull GenericEvent event) {
        BD1.recentEvent = event;
        if (event instanceof SlashCommandInteractionEvent) {
            BD1.recentCommandEvent = (SlashCommandInteractionEvent) event;
        } else if (event instanceof MessageReceivedEvent) {
            BD1.recentMessageEvent = (MessageReceivedEvent) event;
        }
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        if (event.getGuild().getIdLong() != Guilds.theBoys.getIdLong()) return;
        if (!event.getUser().isBot()) {
            Guilds.theBoys.addRoleToMember(event.getUser(), Roles.rkelly).queue();
            Guilds.theBoys.addRoleToMember(event.getUser(), Roles.online).queue();
        }
    }

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

    @Override
    public void onSelectMenuInteraction(@NotNull SelectMenuInteractionEvent event) {
        RoleMenuCommand.onRoleCommandInteraction(event);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // if in Showcase channel and has an attachment, send a thumbs up
        if (event.isFromGuild() && event.getChannel().getIdLong() == Channels.showcase.getIdLong() && event.getMessage().getAttachments().size() != 0) {
            event.getMessage().addReaction(Emojis.THUMBS_UP).queue();
        } else if (event.isFromGuild() && event.getGuild().getIdLong() == Guilds.jetpackHub.getIdLong()
                && event.getMessage().getContentRaw().toLowerCase().startsWith("can you believe it guys")
        ) {
            int count = DBAccessor.incrementAndGetCYBIG();
            event.getMessage().reply(String.valueOf(count)).queue();
        }
    }
}