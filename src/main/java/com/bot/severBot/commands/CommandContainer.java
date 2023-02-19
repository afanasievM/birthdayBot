package com.bot.severBot.commands;

import com.bot.severBot.service.GoogleSheetService;
import com.bot.severBot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.bot.severBot.commands.CommandName.*;

@Slf4j
@Component
public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final Command noCommand;

    @Autowired
    @Lazy
    public CommandContainer(Command nextBirthdayCommand, Command noCommand, Command unknownCommand) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(NEXT.getCommandName(), nextBirthdayCommand)
                .build();

        this.unknownCommand = unknownCommand;
        this.noCommand = noCommand;
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

    public Command noCommand() {
        return noCommand;
    }
}
