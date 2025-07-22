package com.jetpacker06.gsbot.event;

import com.jetpacker06.gsbot.GSBot;
import com.jetpacker06.gsbot.command.anywhereinmessage.AnywhereInMessageCommands;
import com.jetpacker06.gsbot.terminal.DMTerminal;
import com.jetpacker06.gsbot.util.Util;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class MiscEvents extends ListenerAdapter {
    @Override
    public void onGenericEvent(@NotNull GenericEvent event) {
        GSBot.recentEvent = event;
        if (event instanceof SlashCommandInteractionEvent) {
            GSBot.recentCommandEvent = (SlashCommandInteractionEvent) event;
        } else if (event instanceof MessageReceivedEvent) {
            GSBot.recentMessageEvent = (MessageReceivedEvent) event;
        }
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getChannelType() == ChannelType.PRIVATE) {
            DMTerminal.handleDM(event);
            return;
        }

        for (String key : AnywhereInMessageCommands.anywhereInMessageCommands.keySet()) {
            if (event.getMessage().getContentDisplay().toLowerCase(Locale.ROOT).contains(key.toLowerCase(Locale.ROOT))
            && !Util.isSelf(event.getAuthor())) {
                AnywhereInMessageCommands.anywhereInMessageCommands.get(key).run(event);
            }
        }
    }
}