package com.jetpacker06.bd1.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static com.jetpacker06.bd1.BD1.print;


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
    public static MessageEmbed createImageEmbed(String url) {
        return blueBuilder().setImage(url).build();
    }
    public static void logJSONObject(JsonObject obj) {
        print(new GsonBuilder().setPrettyPrinting().create().toJson(obj));
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
}
