package com.jetpacker06.gsbot.command.anywhereinmessage;

import java.util.Objects;

public class AIMCDisabler {
    public static boolean commandEnabled(String command) {
        if (Objects.nonNull(System.getenv(command.toUpperCase() + "_COMMAND_ENABLE"))) {
            return !System.getenv(command.toUpperCase() + "_COMMAND_ENABLE").equalsIgnoreCase("false");
        }
        return true;
    }
}
