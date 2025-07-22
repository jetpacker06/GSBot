package com.jetpacker06.gsbot.terminal;

import com.jetpacker06.gsbot.GSBot;
import com.jetpacker06.gsbot.util.Util;
import com.jetpacker06.gsbot.util.entity.entities.Channels;
import com.jetpacker06.gsbot.util.entity.entities.UserIDs;
import com.jetpacker06.gsbot.verseofday.VerseOfTheDayReader;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

public class Terminal {
    public static void startTerminal() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command: ");
            System.out.println(executeTerminalCommand(sc.nextLine()));
        }
    }
    public static String executeTerminalCommand(String givenInput) {
        String[] splitInput = givenInput.split(" ");
        if (splitInput.length == 0) {
            return "Invalid command";
        }
        String command = splitInput[0].toLowerCase();
        ArrayList<String> input = new ArrayList<>(List.of(splitInput));
        if (functions.containsKey(command)) {
            try {
                functions.get(input.get(0).toLowerCase()).accept(input);
            } catch (Exception e) {
                e.printStackTrace();
                return "ERROR while executing terminal command.";
            }
        } else {
            return "Invalid command";
        }
      //  try {
      //      Thread.sleep(1000);
      //  } catch (Exception ignored) {}
        return "done";
    }

    private static final HashMap<String, Consumer<ArrayList<String>>> functions = getTerminalCommandToFunctionMap();

    public static HashMap<String, Consumer<ArrayList<String>>> getTerminalCommandToFunctionMap() {
        HashMap<String, Consumer<ArrayList<String>>> functions = new HashMap<>();
        JDA jda = GSBot.jda;

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
        functions.put("verseoftheday", list -> {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String todayStr = sdf.format(new Date());  // e.g. "2025-07-05"
            Date today = null;
            try {
                today = sdf.parse(todayStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            for (Date date : VerseOfTheDayReader.versesMap.keySet()) {
                if (date.getTime() == today.getTime()) {
                    EmbedBuilder bob = Util.blueBuilder();
                    bob.setTitle("YouVersion Verse of the Day: " + VerseOfTheDayReader.wordFormat.format(date));
                    bob.appendDescription(VerseOfTheDayReader.versesMap.get(date).name);
                    bob.appendDescription("\n");
                    bob.appendDescription(VerseOfTheDayReader.versesMap.get(date).text);

                    Channels.verseOfTheDay.sendMessageEmbeds(bob.build()).queue();
                    System.out.println("verse of the day sent in " + Channels.verseOfTheDay.getName());
                    return;
                }
            }
            System.out.println("did not find today's date in the file");
            Channels.main_chat.sendMessage("i cannot seem to find today's Verse of the Day... <@" + UserIDs.CODY + ">").queue();

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
