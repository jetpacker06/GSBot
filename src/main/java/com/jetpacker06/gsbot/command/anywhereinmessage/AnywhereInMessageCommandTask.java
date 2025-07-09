package com.jetpacker06.gsbot.command.anywhereinmessage;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@FunctionalInterface
public interface AnywhereInMessageCommandTask {
    void run(MessageReceivedEvent event);
}
