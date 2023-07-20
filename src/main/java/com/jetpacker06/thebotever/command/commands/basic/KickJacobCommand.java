package com.jetpacker06.thebotever.command.commands.basic;

import com.jetpacker06.thebotever.command.commands.Command;
import com.jetpacker06.thebotever.util.entity.entities.UserIDs;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;

public class KickJacobCommand extends Command {

    @Override
    public String getName() {
        return "kickjacob";
    }
    @Override
    public String getDescription() {
        return "hehe";
    }

    @Override
    public boolean forFriendsOnly() {
        return true;
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
        if (!event.isFromGuild()) {
            return;
        }
        Member member = event.getMember();
        Guild guild = event.getGuild();
        assert member != null;
        assert guild != null;

    }
}
