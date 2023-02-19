package com.bot.severBot.commands;

import com.bot.severBot.service.SendBotMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UnknownCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String UNKNOWN_MESSAGE = "I don't understand this comman.\n" +
            "Try /help to show commands";

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), UNKNOWN_MESSAGE);
    }
}
