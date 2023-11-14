package com.jetpacker06.bd1.command.commands;

import com.jetpacker06.bd1.BD1;
import com.jetpacker06.bd1.command.CommandRegistry;
import com.jetpacker06.bd1.util.entity.entities.Guilds;
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
    public abstract void execute(SlashCommandInteractionEvent event);

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
        CommandRegistry.commandDataArrayList.add(command);
    }

    public void executeForIncorrectContext(SlashCommandInteractionEvent event) {
        event.reply("This command does not work in this server").setEphemeral(true).queue();
    }
    public boolean checkContext(SlashCommandInteractionEvent event) {
        return true;
    }

    public String getStrOp(String optionName) {
        return Objects.requireNonNull(BD1.recentCommandEvent.getOption(optionName)).getAsString();
    }
    public boolean getBoolOp(String optionName) {
        return Objects.requireNonNull(BD1.recentCommandEvent.getOption(optionName)).getAsBoolean();
    }
    public int getIntOp(String optionName) {
        return Objects.requireNonNull(BD1.recentCommandEvent.getOption(optionName)).getAsInt();
    }

    public int intOrElse(String name, int backup) {
        if (optionExists(name)) {
            return getIntOp(name);
        }
        return backup;
    }
    public String stringOrElse(String name, String backup) {
        if (optionExists(name)) {
            return getStrOp(name);
        }
        return backup;
    }
    public boolean boolOrElse(String name, boolean backup) {
        if (optionExists(name)) {
            return getBoolOp(name);
        }
        return backup;
    }
    public boolean isTheBoysServer(SlashCommandInteractionEvent event) {
        return Objects.requireNonNull(event.getGuild()).getIdLong() == Guilds.theBoys.getIdLong();
    }
    public boolean isTestServer() {
        return Objects.requireNonNull(BD1.recentCommandEvent.getGuild()).getIdLong() == Guilds.testServer.getIdLong();
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
            return BD1.recentCommandEvent.getOption(name) != null;
        } catch (Exception ignored) {}
        return false;
    }
    public static ArrayList<OptionData> optionsList(OptionData... options) {
        return new ArrayList<>(Arrays.asList(options));
    }
}
