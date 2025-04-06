package com.jetpacker06.bd1;

import com.jetpacker06.bd1.command.SlashCommandRegistry;
import com.jetpacker06.bd1.db.DBClient;
import com.jetpacker06.bd1.event.MiscEvents;
import com.jetpacker06.bd1.event.SlashCommandEvents;
import com.jetpacker06.bd1.terminal.Terminal;
import com.jetpacker06.bd1.util.entity.entities.Channels;
import com.jetpacker06.bd1.util.entity.entities.Guilds;
import com.jetpacker06.bd1.util.entity.entities.Roles;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class BD1 {
    public static GenericEvent recentEvent;
    public static SlashCommandInteractionEvent recentCommandEvent;
    public static MessageReceivedEvent recentMessageEvent;

    public static final String BOT_KEY = System.getenv("BD1KEY");
    public static JDA jda;

    public static void print(Object message) {
        System.out.println(message);
    }

    public static void main(String[] args) throws LoginException, InterruptedException {
        print("Booted up!");
        jda = JDABuilder.createDefault(BOT_KEY)
        .setActivity(Activity.watching("closely."))
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .enableIntents(GatewayIntent.GUILD_MEMBERS)
        .setStatus(OnlineStatus.ONLINE)
        .addEventListeners(new MiscEvents(), new SlashCommandEvents())
        .build()
        .awaitReady();

        initSnowflakes();

        DBClient.init();

        SlashCommandRegistry.registerSlashCommands();
        Terminal.startTerminal();
    }

    public static void initSnowflakes() {
        Guilds.jetpackHub = jda.getGuildById(871409050808643594L);
        Guilds.testServer = jda.getGuildById(945662624224382998L);

        Channels.testNoCody = jda.getVoiceChannelById(1073007761060806667L);
        Channels.jgeneral = jda.getTextChannelById(1076725461054402570L);

        Channels.announcements = jda.getTextChannelById(961816766923821076L);
        Channels.botcmds_jetpackhub = jda.getTextChannelById(1173008414423646208L);
        Channels.showcase = jda.getTextChannelById(1211090000268234853L);

        Roles.editor = jda.getRoleById(1080620779743498330L);
        Roles.noEditor = jda.getRoleById(1199923037454204928L);
        Roles.rkelly = jda.getRoleById(1151675275420110911L);
        Roles.online = jda.getRoleById(1080685865447194744L);
    }
}