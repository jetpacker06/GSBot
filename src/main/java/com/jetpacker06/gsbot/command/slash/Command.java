package com.jetpacker06.gsbot.command.slash;

import com.jetpacker06.gsbot.GSBot;
import com.jetpacker06.gsbot.util.entity.entities.Guilds;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class Command {
    public abstract String getName();
    public abstract String getDescription();

    public ArrayList<OptionData> getOptions() {
        return new ArrayList<>();
    }
    public Command() {
        SlashCommandData command = net.dv8tion.jda.api.interactions.commands.build.Commands.slash(
                this.getName(), this.getDescription()
        );
        for (OptionData option : this.getOptions()) {
            command.addOption(option.getType(), option.getName(), option.getDescription(), option.isRequired());
        }
        SlashCommandRegistry.commandDataArrayList.add(command);
    }

    // called if the below method returns true
    public abstract void execute(SlashCommandInteractionEvent event);
    // can be overridden to ensure that a command is allowed to be used in the context
    public boolean checkContext(SlashCommandInteractionEvent event) {
        return true;
    }
    // called if the above method returns false
    public void executeForIncorrectContext(SlashCommandInteractionEvent event) {
        event.reply("This command does not work in this server").setEphemeral(true).queue();
    }

    public String getStringOption(String optionName) {
        return Objects.requireNonNull(GSBot.recentCommandEvent.getOption(optionName)).getAsString();
    }
    public boolean getBoolOption(String optionName) {
        return Objects.requireNonNull(GSBot.recentCommandEvent.getOption(optionName)).getAsBoolean();
    }
    public int getIntOption(String optionName) {
        return Objects.requireNonNull(GSBot.recentCommandEvent.getOption(optionName)).getAsInt();
    }

    public int intOrElse(String name, int backup) {
        if (optionExists(name)) {
            return getIntOption(name);
        }
        return backup;
    }
    public String stringOrElse(String name, String backup) {
        if (optionExists(name)) {
            return getStringOption(name);
        }
        return backup;
    }
    public boolean boolOrElse(String name, boolean backup) {
        if (optionExists(name)) {
            return getBoolOption(name);
        }
        return backup;
    }
    public boolean isTestServer() {
        return Objects.requireNonNull(GSBot.recentCommandEvent.getGuild()).getIdLong() == Guilds.testServer.getIdLong();
    }
    public static OptionData stringOption(String name, String description, boolean required) {
        return new OptionData(OptionType.STRING, name, description, required);
    }
    public static OptionData intOption(String name, String description, boolean required) {
        return new OptionData(OptionType.INTEGER, name, description, required);
    }
    public static OptionData boolOption(String name, String description, boolean required) {
        return new OptionData(OptionType.BOOLEAN, name, description, required);
    }
    public static boolean optionExists(String name) {
        try {
            return GSBot.recentCommandEvent.getOption(name) != null;
        } catch (Exception ignored) {}
        return false;
    }
    public static ArrayList<OptionData> optionsList(OptionData... options) {
        return new ArrayList<>(Arrays.asList(options));
    }
}
