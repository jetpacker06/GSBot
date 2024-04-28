package com.jetpacker06.bd1.command.commands.jetpackhub;

import com.jetpacker06.bd1.BD1;
import com.jetpacker06.bd1.util.Util;
import com.jetpacker06.bd1.util.entity.entities.UserIDs;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.Objects;

public class RoleRequestCommand extends AbstractJetpackHubCommand {
    @Override
    public String getName() {
        return "rolerequest";
    }

    @Override
    public String getDescription() {
        return "Request a role that you think could apply to you, e.g. YouTuber or Mod Dev (not admin/moderator)";
    }

    @Override
    public ArrayList<OptionData> getOptions() {
        ArrayList<OptionData> options = new ArrayList<>();
        options.add(stringOption("role", "the role to request", true));
        options.add(stringOption("link", "a link if relevant, e.g. YouTube, Curseforge or Github", false));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder bobTheBuilder = Util.blueBuilder();
        bobTheBuilder.setTitle("Request sent!");
        bobTheBuilder.appendDescription("Sent a request for role \"" + getStringOption("role") + "\". jetpacker will get back to you soon!");
        if (optionExists("link")) {
            bobTheBuilder.appendDescription("\nLink attached:\n");
            bobTheBuilder.appendDescription(getStringOption("link"));
        }
        event.replyEmbeds(bobTheBuilder.build()).setEphemeral(true).queue();

        Objects.requireNonNull(BD1.jda.getUserById(UserIDs.cody)).openPrivateChannel().queue((PrivateChannel privateChannel) -> {
            EmbedBuilder dmEmbed = Util.blueBuilder();
            dmEmbed.setTitle(Objects.requireNonNull(event.getMember()).getEffectiveName() + " requested the role \"" + getStringOption("role") + "\"");
            if (optionExists("link")) {
                dmEmbed.appendDescription("\nLink attached:\n");
                dmEmbed.appendDescription(getStringOption("link"));
            }
            dmEmbed.setImage(event.getUser().getAvatarUrl());
            privateChannel.sendMessageEmbeds(dmEmbed.build()).queue();
        });
    }
}
