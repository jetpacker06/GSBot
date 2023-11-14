package com.jetpacker06.bd1.command.commands.basic;

import com.jetpacker06.bd1.command.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;

public class ToStacksCommand extends Command {

    @Override
    public String getName() {
        return "tostacks";
    }
    @Override
    public String getDescription() {
        return "Convert a number to an amount of stacks.";
    }

    @Override
    public ArrayList<OptionData> getOptions() {
        return optionsList(
                intOption("number", "The number to convert to stacks.", true),
                intOption("stacksize", "The amount of items per stack, defaults to 64.", false)
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        int input = getIntOp("number");
        int stackSize = intOrElse("stacksize", 64);
        int stacks = 0;
        int remainder = input;
        if (input <= 0) {
            event.reply("Input must be positive").setEphemeral(true).queue();
            return;
        }
        if (stackSize <= 0) {
            event.reply("Stack size must be positive").setEphemeral(true).queue();
            return;
        }
        if (input % stackSize == 0) {
            stacks = input / stackSize;
            EmbedBuilder bobTheBuilder = new EmbedBuilder();
            bobTheBuilder.setTitle(stacks + " stack" + (stacks == 1 ? "" : "s"));
            bobTheBuilder.appendDescription(
                    input + " items is equal to " + stacks + " stack" + (stacks == 1 ? "" : "s") + " of " + stackSize + "."
            );
            event.replyEmbeds(bobTheBuilder.build()).queue();
            return;
        }

        while (remainder > stackSize) {
            stacks++;
            remainder -= stackSize;
        }
        EmbedBuilder bobTheBuilder = new EmbedBuilder();
        bobTheBuilder.setTitle(stacks + " stacks and " + remainder);
        bobTheBuilder.appendDescription(
                input + " items is equal to " + stacks + " stacks of " + stackSize + " and " + remainder + "."
        );
        event.replyEmbeds(bobTheBuilder.build()).queue();
    }
}
