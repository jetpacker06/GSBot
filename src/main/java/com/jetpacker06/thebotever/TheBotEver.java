package com.jetpacker06.thebotever;

import com.jetpacker06.thebotever.command.CommandInit;
import com.jetpacker06.thebotever.command.Commands;
import com.jetpacker06.thebotever.util.*;
import com.jetpacker06.thebotever.util.entity.entities.Channels;
import com.jetpacker06.thebotever.util.entity.entities.Guilds;
import com.jetpacker06.thebotever.util.entity.entities.Roles;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.reflections.Reflections;

import javax.security.auth.login.LoginException;

public class TheBotEver {
    public static Reflections reflections = new Reflections("com.jetpacker06");
    public static GenericEvent recentEvent;
    public static SlashCommandInteractionEvent recentCommandEvent;
    public static final String BOT_KEY = System.getenv().containsKey("TheBotEverDiscordBotKey")
            ? System.getenv("TheBotEverDiscordBotKey") : Util.readFile("botkey.txt");
    private static boolean logSpamMode = false;
    public static JDA jda;

    public static void main(String[] args) throws LoginException, InterruptedException {
        if (System.getenv().containsKey("DEVMODE")) {
            logSpamMode = Boolean.parseBoolean(System.getenv("DEVMODE"));
        }

        log("Booted up!");
        jda = JDABuilder.createDefault(BOT_KEY)
        .setActivity(Activity.watching("closely."))
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .setStatus(OnlineStatus.ONLINE)
        .addEventListeners(new Commands())
        .build()
        .awaitReady();

        Guilds.theBoys = jda.getGuildById(1080478548948684900L);
        Guilds.testServer = jda.getGuildById(945662624224382998L);
        Channels.plans = jda.getTextChannelById(1089221441699979345L);
        Channels.noLawrence = jda.getVoiceChannelById(1080625271100669962L);
        Channels.noJacob = jda.getVoiceChannelById(1080625184899346512L);
        Channels.testNoCody = jda.getVoiceChannelById(1073007761060806667L);
        Channels.jgeneral = jda.getTextChannelById(1076725461054402570L);
        Roles.editor = jda.getRoleById(1080620779743498330L);
        Roles.noEditor = jda.getRoleById(1106691485774127176L);


        CommandInit.registerSlashCommands();

    }
    public static void log(Object message) {
        if (logSpamMode) {
            System.out.println(message);
        }
    }
}