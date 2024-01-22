package com.jetpacker06.bd1;

import com.jetpacker06.bd1.command.CommandRegistry;
import com.jetpacker06.bd1.command.EventHandler;
import com.jetpacker06.bd1.util.entity.entities.Channels;
import com.jetpacker06.bd1.util.entity.entities.Guilds;
import com.jetpacker06.bd1.util.entity.entities.Roles;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.*;
import java.util.function.Consumer;

public class BD1 {
    public static GenericEvent recentEvent;
    public static SlashCommandInteractionEvent recentCommandEvent;

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
        .setStatus(OnlineStatus.ONLINE)
        .addEventListeners(new EventHandler())
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
        Roles.editor = jda.getRoleById(1080620779743498330L);
        Roles.noEditor = jda.getRoleById(1106691485774127176L);


        CommandRegistry.registerSlashCommands();

        HashMap<String, Consumer<ArrayList<String>>> functions = new HashMap<>();
        registerTerminalCommands(functions);
        terminalCommandLoop(functions);

    }

    private static String repairMessage(ArrayList<String> message) {
        StringBuilder bobTheBuilder = new StringBuilder();
        for (String s : message) {
            bobTheBuilder.append(s);
            bobTheBuilder.append(" ");
        }
        return bobTheBuilder.toString();
    }

    public static void registerTerminalCommands(HashMap<String, Consumer<ArrayList<String>>> functions) {
        functions.put("say", list -> {
            TextChannel channel = Objects.requireNonNull(jda.getTextChannelById(Long.parseLong(list.get(1))));
            list.remove(0);
            list.remove(0);
            channel.sendMessage(repairMessage(list)).queue();
            System.out.println("Message sent in " + channel.getName());
        });
        functions.put("shutdown", list -> {
            System.out.println("Shutting down from command");
            System.exit(0);
        });
        functions.put("dm", list -> {
            User user = jda.retrieveUserById(Long.parseLong(list.get(1))).complete();
            list.remove(0);
            list.remove(0);
            String message = repairMessage(list);
            Objects.requireNonNull(user).openPrivateChannel().queue((PrivateChannel privateChannel) -> {
                if (privateChannel.canTalk())
                    privateChannel.sendMessage(message).queue();
                else
                    System.out.println("Can't DM this user");
            });
        });
        functions.put("pingstring", list -> {
            User user = jda.retrieveUserById(Long.parseLong(list.get(1))).complete();
            System.out.println(user.getAsMention());
        });
        functions.put("read", list -> {
            User user = Objects.requireNonNull(jda.retrieveUserById(Long.parseLong(list.get(1))).complete());
            user.openPrivateChannel().queue((privateChannel) -> {
                privateChannel.getIterableHistory().queue(messages -> {
                    Collections.reverse(messages);
                    messages.forEach(message -> {
                        String content;
                        if (message.getEmbeds().size() != 0) {
                            content = "(embed)";
                        } else if (message.getAttachments().size() != 0) {
                            content = "(attachment)";
                        } else if (message.isWebhookMessage()) {
                            content = "(webhook)";
                        } else {
                            content = message.getContentDisplay();
                        }
                        System.out.println(message.getAuthor().getName() + ": " + content);
                    });
                });
            });
        });
    }
    public static void terminalCommandLoop(HashMap<String, Consumer<ArrayList<String>>> functions) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command: ");
            String[] splitInput = sc.nextLine().split(" ");
            String command = splitInput[0].toLowerCase();
            ArrayList<String> input = new ArrayList<>(List.of(splitInput));
            if (functions.containsKey(command)) {
                try {
                    functions.get(input.get(0).toLowerCase()).accept(input);
                } catch (Exception e) {
                    System.out.println("ERROR while executing terminal command.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Unknown command");
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {}
        }
    }
}