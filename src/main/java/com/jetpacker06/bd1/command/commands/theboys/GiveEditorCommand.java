package com.jetpacker06.bd1.command.commands.theboys;

import com.jetpacker06.bd1.util.entity.entities.Guilds;
import com.jetpacker06.bd1.util.entity.entities.Roles;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GiveEditorCommand extends AbstractTheBoysCommand {
    @Override
    public String getName() {
        return "giveeditor";
    }

    @Override
    public String getDescription() {
        return "Give yourself editor role";
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

        boolean canHaveEditor = guild.equals(Guilds.theBoys);
        for (Role role: member.getRoles()) {
            if (role.getIdLong() == Roles.noEditor.getIdLong()) {
                canHaveEditor = false;
            }
        }
        if (canHaveEditor) {
            guild.addRoleToMember(member, Roles.editor).queue();
            event.reply("Done.").queue();
        } else {
            event.reply("No editor for you :slight_smile:").queue();
        }
    }
}
