package com.bot.severBot.commands;

public enum CommandName {
    NEXT("/next");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
