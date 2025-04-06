package com.jetpacker06.bd1.terminal;

import com.jetpacker06.bd1.BD1;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.*;
import java.util.function.Consumer;

public class Terminal {
    public static void startTerminal() {
        HashMap<String, Consumer<ArrayList<String>>> functions = getTerminalCommandToFunctionMap();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command: ");
            String[] splitInput = sc.nextLine().split(" ");
            if (splitInput.length == 0) {
                System.out.println("Invalid command");
                continue;
            }
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
                System.out.println("Invalid command");
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {}
        }
    }

    public static HashMap<String, Consumer<ArrayList<String>>> getTerminalCommandToFunctionMap() {
        HashMap<String, Consumer<ArrayList<String>>> functions = new HashMap<>();
        JDA jda = BD1.jda;

        functions.put("say", list -> {
            list.remove(0);
            TextChannel channel = Objects.requireNonNull(jda.getTextChannelById(Long.parseLong(list.remove(0))));
            channel.sendMessage(repairMessage(list)).queue();
            System.out.println("Message sent in " + channel.getName());
        });
        functions.put("reply", list -> {
            TextChannel channel = Objects.requireNonNull(jda.getTextChannelById(Long.parseLong(list.get(1))));
            long messageID = Long.parseLong(list.get(1));
            list.remove(0);
            list.remove(0);
            list.remove(0);

            channel.getIterableHistory().takeAsync(100).thenApply(messages -> {
                for (Message message : messages) {
                    if (message.getIdLong() == messageID) {
                        message.reply(repairMessage(list)).reference(message).failOnInvalidReply(true) .queue();
                        break;
                    }
                }
                return messages;
            });


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

        return functions;
    }

    /**
     * @param message A list of strings
     * @return The strings joined with spaces between them
     */
    private static String repairMessage(ArrayList<String> message) {
        StringBuilder bobTheBuilder = new StringBuilder();
        for (String s : message) {
            bobTheBuilder.append(s);
            bobTheBuilder.append(" ");
        }
        return bobTheBuilder.toString();
    }
}
