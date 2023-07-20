package com.jetpacker06.thebotever.command.commands;

import com.jetpacker06.thebotever.util.entity.entities.Channels;
import com.jetpacker06.thebotever.util.entity.entities.Emojis;
import com.jetpacker06.thebotever.util.Util;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.Objects;

public class PlanCommand extends Command{
    @Override
    public String getName() {
        return "plan";
    }

    @Override
    public String getDescription() {
        return "Plan an event.";
    }

    @Override
    public boolean forFriendsOnly() {
        return true;
    }

    @Override
    public ArrayList<OptionData> getOptions() {
        return optionsList(
                stringOption("event", "What is the event?", true),
                stringOption("when", "When is the event?", false),
                stringOption("where", "Where is the event?", false),
                stringOption("bring", "What should be brought?", false),
                stringOption("extrainformation", "Extra information?", false),
                boolOption("ping", "Should the bot ping everyone?", false),
                boolOption("thread", "Should a thread be created for the event?", false)
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        EmbedBuilder builder = Util.blueBuilder();
        builder.setTitle(getStrOp("event"));
        builder.addField("Organizer", Objects.requireNonNull(event.getMember()).getEffectiveName(), false);
        builder.addField("Event", getStrOp("event"), false);

        if (optionExists("where"))
            builder.addField("Where", getStrOp("where"), false);
        if (optionExists("when"))
            builder.addField("When", getStrOp("when"), false);
        if (optionExists("bring"))
            builder.addField("Bring", getStrOp("bring"), false);
        if (optionExists("extrainformation"))
            builder.addField("Extra Information", getStrOp("extrainformation"), true);
        MessageBuilder m = new MessageBuilder();
        if (boolOrElse("ping", false))
            m.append("||@everyone||");
        m.setEmbeds(builder.build());

        TextChannel channel;
        if (isTheBoysServer(event)) {
            channel = Channels.plans;
        } else {
            channel = Channels.jgeneral;
        }

        channel.sendMessage(m.build()).queue((message) -> {
            message.addReaction(Emojis.UP_ARROW).queue();
            message.addReaction(Emojis.DOWN_ARROW).queue();
            if (boolOrElse("thread", true)) {
                message.createThreadChannel(getStrOp("event")).queue();
            }
        });
        event.getHook().deleteOriginal().queue();


    }
}
