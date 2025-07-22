package com.jetpacker06.gsbot;

import com.jetpacker06.gsbot.command.anywhereinmessage.AnywhereInMessageCommands;
import com.jetpacker06.gsbot.command.slash.SlashCommandRegistry;
import com.jetpacker06.gsbot.event.MiscEvents;
import com.jetpacker06.gsbot.event.SlashCommandEvents;
import com.jetpacker06.gsbot.schedules.ActivitySchedule;
import com.jetpacker06.gsbot.terminal.Terminal;
import com.jetpacker06.gsbot.util.entity.entities.Channels;
import com.jetpacker06.gsbot.util.entity.entities.Guilds;
import com.jetpacker06.gsbot.util.entity.entities.Roles;
import com.jetpacker06.gsbot.verseofday.VerseOfTheDayReader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class GSBot {
    public static GenericEvent recentEvent;
    public static SlashCommandInteractionEvent recentCommandEvent;
    public static MessageReceivedEvent recentMessageEvent;

    public static final String BOT_KEY = System.getenv("GSBOTKEY");
    public static JDA jda;

    public static void print(Object message) {
        System.out.println(message);
    }

    public static void main(String[] args) throws LoginException, InterruptedException, FileNotFoundException, ParseException {

        jda = JDABuilder.createDefault(BOT_KEY)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .enableIntents(GatewayIntent.GUILD_MEMBERS)
        .setStatus(OnlineStatus.ONLINE)
        .addEventListeners(new MiscEvents(), new SlashCommandEvents())
        .build()
        .awaitReady();

        initSnowflakes();
        VerseOfTheDayReader.init();
        ActivitySchedule.init();

        SlashCommandRegistry.init();
        AnywhereInMessageCommands.init();
        Terminal.startTerminal();

    }

    public static void initSnowflakes() {
        Guilds.testServer = jda.getGuildById(945662624224382998L);
        Guilds.gsServer = jda.getGuildById(1338605578033041470L);

        Channels.testServerGeneral = jda.getTextChannelById(1076725461054402570L);
        Channels.main_chat = jda.getTextChannelById(1338605578649338020L);
        Channels.verseOfTheDay = jda.getTextChannelById(1371505789688352778L);

        Roles.mod = jda.getRoleById(1339623173422972989L);
    }
}