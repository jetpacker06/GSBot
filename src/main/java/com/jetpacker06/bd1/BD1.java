package com.jetpacker06.bd1;

import com.jetpacker06.bd1.command.CommandRegistry;
import com.jetpacker06.bd1.event.EventHandler;
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
        .addEventListeners(new EventHandler(), new SlashCommandEvents())
        .build()
        .awaitReady();

        Guilds.theBoys = jda.getGuildById(1080478548948684900L);
        Guilds.jetpackHub = jda.getGuildById(871409050808643594L);
        Guilds.testServer = jda.getGuildById(945662624224382998L);
        Channels.plans = jda.getTextChannelById(1132046698265464853L);
        Channels.noLawrence = jda.getVoiceChannelById(1080625271100669962L);
        Channels.testNoCody = jda.getVoiceChannelById(1073007761060806667L);
        Channels.jgeneral = jda.getTextChannelById(1076725461054402570L);
        Channels.botCmds = jda.getTextChannelById(1173008414423646208L);
        Channels.showcase = jda.getTextChannelById(1211090000268234853L);
        Roles.editor = jda.getRoleById(1080620779743498330L);
        Roles.noEditor = jda.getRoleById(1199923037454204928L);
        Roles.rkelly = jda.getRoleById(1151675275420110911L);
        Roles.online = jda.getRoleById(1080685865447194744L);

        CommandRegistry.registerSlashCommands();
        Terminal.startTerminal();
    }
}