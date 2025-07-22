package com.jetpacker06.gsbot.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.jetpacker06.gsbot.util.entity.entities.UserIDs;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static com.jetpacker06.gsbot.GSBot.print;


public class Util {
    public static <T> T randomFromArray(T[] array) {
        return array[new Random().nextInt(array.length)];
    }
    public static <T> boolean isThingInList(T thing, T[] list) {
        boolean toReturn = false;
        for (T s : list) {
            if (Objects.equals(s, thing)) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
    }
    public static void sendMessage(String message, TextChannel channel) {
        channel.sendMessage(message).queue();
    }
    public static EmbedBuilder blueBuilder() {
        return coloredEmbedBuilder(Colors.BLUE);
    }
    public static EmbedBuilder coloredEmbedBuilder(int color) {
        return new EmbedBuilder().setColor(color);
    }
    public static MessageEmbed createEmbed(String title) {
        return new EmbedBuilder().setTitle(title)
                .setColor(Colors.BLUE)
                .build();
    }
    public static MessageEmbed createInstructionalEmbed(String title, String... steps) {
        var embed = new EmbedBuilder().setTitle(title)
                .setColor(Colors.BLUE);
        for (int i = 0; i < steps.length; i++) {
            embed.appendDescription(i + 1 + ". " + steps[i]);
            embed.appendDescription("\n");
        }
        return embed.build();
    }
    public static MessageEmbed createImageEmbed(String url) {
        return blueBuilder().setImage(url).build();
    }
    public static void logJSONObject(JsonObject obj) {
        print(JSONObjectToString(obj));
    }
    public static String getUsername(User user) {
        return user.getName() + "#" + user.getDiscriminator();
    }
    public static String readFile(String path) {
        try {
            boolean created = new File(path).createNewFile();
            File data_file = new File(path);
            Scanner fileScanner = new Scanner(data_file);
            StringBuilder contents = new StringBuilder();
            while (fileScanner.hasNext()) {
                contents.append(fileScanner.next());
            }
            fileScanner.close();
            return contents.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static String JSONObjectToString(JsonObject json) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(json);
    }
    public static boolean coinFlip() {
        return new Random().nextBoolean();
    }
    public static boolean isSelf(User user) {
        return user.getIdLong() == UserIDs.GSBOT;
    }
}
