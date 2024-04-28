package com.jetpacker06.bd1.command.commands.common;

import com.jetpacker06.bd1.BD1;
import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.util.Util;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenuInteraction;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoleMenuCommand extends Command {

    @Override
    public String getName() {
        return "rolemenu";
    }

    @Override
    public String getDescription() {
        return "Create a role selection menu";
    }

    @Override
    public boolean checkContext(SlashCommandInteractionEvent event) {
        return !(event.getChannel() instanceof PrivateChannel);
    }

    @Override
    public ArrayList<OptionData> getOptions() {
        ArrayList<OptionData> list = optionsList(
                stringOption("title", "the menu title", true),
                stringOption("id", "a unique menu id", true)
        );
        list.add(stringOption("role0", "the first role", true));
        for (int i=1;i<12;i++) {
            list.add(stringOption("role" + i, "the next role", false));
        }
        return list;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        ArrayList<Role> rolesList = new ArrayList<>();
        for (int i=0;i<12;i++) {
            try {
                Role roleToAdd = BD1.jda.getRoleById(getStringOption("role" + i));
                if (roleToAdd != null)
                    rolesList.add(roleToAdd);
            } catch (NullPointerException ignored) {}
        }
        SelectMenu.Builder builder = SelectMenu.create(getStringOption("id"));
        builder.setPlaceholder("Select any that apply")
                .setMinValues(0)
                .setMaxValues(rolesList.size());
        for (Role role : rolesList) {
            builder.addOption(role.getName(), role.getId());
        }

        MessageEmbed embed = Util.createEmbed(getStringOption("title"));
        MessageAction action = event.getChannel().asTextChannel().sendMessageEmbeds(embed).setActionRow(builder.build());
        action.queue();
        event.reply("done").setEphemeral(true).queue();
    }

    public static void onRoleCommandInteraction(SelectMenuInteractionEvent event) {
        SelectMenuInteraction interaction = event.getInteraction();
        Guild guild = Objects.requireNonNull(event.getGuild());

        for (SelectOption option : event.getSelectedOptions()) {
            guild.addRoleToMember(event.getUser(), Objects.requireNonNull(BD1.jda.getRoleById(option.getValue()))).queue();
        }
        List<SelectOption> notSelected = interaction.getSelectMenu().getOptions().stream().filter(o -> !event.getSelectedOptions().contains(o)).toList();
        for (SelectOption option : notSelected) {
            guild.removeRoleFromMember(event.getUser(), Objects.requireNonNull(BD1.jda.getRoleById(option.getValue()))).queue();
        }
        interaction.reply("done").setEphemeral(true).queue();
    }
}
