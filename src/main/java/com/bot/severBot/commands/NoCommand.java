package com.bot.severBot.commands;

import com.bot.severBot.service.SendBotMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class NoCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String NO_MESSAGE = "I support commands that starts with the slash (/).\n"
            + "To view a list of commands write /help";

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), NO_MESSAGE);
    }
}
