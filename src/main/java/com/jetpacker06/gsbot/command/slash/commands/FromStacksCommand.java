package com.jetpacker06.gsbot.command.slash.commands;

import com.jetpacker06.gsbot.command.slash.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;

public class FromStacksCommand extends Command {

    @Override
    public String getName() {
        return "fromstacks";
    }
    @Override
    public String getDescription() {
        return "Convert an amount of stacks to a raw number.";
    }
    @Override
    public ArrayList<OptionData> getOptions() {
        return optionsList(
                intOption("number", "The number to convert from stacks.", true),
                intOption("stacksize", "The amount of items per stack, defaults to 64.", false)
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        int input = getIntOption("number");
        int stackSize = intOrElse("stacksize", 64);
        if (input <= 0) {
            event.reply("Input must be positive").setEphemeral(true).queue();
            return;
        }
        if (stackSize <= 0) {
            event.reply("Stack size must be positive").setEphemeral(true).queue();
            return;
        }
        int output = input * stackSize;
        EmbedBuilder bobTheBuilder = new EmbedBuilder();
        bobTheBuilder.setTitle(String.valueOf(output));
        bobTheBuilder.appendDescription(input + " stacks of " + stackSize + " is " + output);
        event.replyEmbeds(bobTheBuilder.build()).queue();
    }
}
