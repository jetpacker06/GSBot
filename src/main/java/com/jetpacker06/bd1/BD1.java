package com.jetpacker06.bd1;

import com.jetpacker06.bd1.command.CommandInit;
import com.jetpacker06.bd1.command.Commands;
import com.jetpacker06.bd1.util.*;
import com.jetpacker06.bd1.util.entity.entities.Channels;
import com.jetpacker06.bd1.util.entity.entities.Guilds;
import com.jetpacker06.bd1.util.entity.entities.Roles;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.reflections.Reflections;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BD1 {
    public static Reflections reflections = new Reflections("com.jetpacker06");
    public static GenericEvent recentEvent;
    public static SlashCommandInteractionEvent recentCommandEvent;

    public static final String BOT_KEY = System.getenv("BD1KEY");
    public static JDA jda;

    public static void main(String[] args) throws LoginException, InterruptedException {
        print("Booted up!");
        jda = JDABuilder.createDefault(BOT_KEY)
        .setActivity(Activity.watching("closely."))
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .setStatus(OnlineStatus.ONLINE)
        .addEventListeners(new Commands())
        .build()
        .awaitReady();

        Guilds.theBoys = jda.getGuildById(1080478548948684900L);
        Guilds.testServer = jda.getGuildById(945662624224382998L);
        Channels.plans = jda.getTextChannelById(1132046698265464853L);
        Channels.noLawrence = jda.getVoiceChannelById(1080625271100669962L);
        Channels.testNoCody = jda.getVoiceChannelById(1073007761060806667L);
        Channels.jgeneral = jda.getTextChannelById(1076725461054402570L);
        Roles.editor = jda.getRoleById(1080620779743498330L);
        Roles.noEditor = jda.getRoleById(1106691485774127176L);


        CommandInit.registerSlashCommands();
    }
    public static void print(Object message) {
        System.out.println(message);
    }
}