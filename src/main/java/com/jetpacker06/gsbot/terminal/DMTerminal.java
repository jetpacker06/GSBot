package com.jetpacker06.gsbot.terminal;

import com.jetpacker06.gsbot.util.entity.entities.Guilds;
import com.jetpacker06.gsbot.util.entity.entities.Roles;
import com.jetpacker06.gsbot.util.entity.entities.UserIDs;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class DMTerminal {

    public static void handleDM(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().getIdLong() == UserIDs.CODY) {
            System.out.println("cody");
            event.getMessage().reply(Terminal.executeTerminalCommand(event.getMessage().getContentRaw())).queue();
            return;
        }
        if (Guilds.gsServer == null) {
            System.out.println("gs server is null");
            return;
        }
        Member member = Guilds.gsServer.getMemberById(event.getAuthor().getId());
        if (member != null) {
            if (member.getRoles().contains(Roles.mod) || member.getIdLong() == UserIDs.CODY) {
                String result = Terminal.executeTerminalCommand(event.getMessage().getContentRaw());
                event.getMessage().reply(result).queue();
            }
        }
    }
}
