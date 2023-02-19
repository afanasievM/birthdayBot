package com.bot.severBot.service;

import com.bot.severBot.bot.Bot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
@Slf4j
public class SendBotMessageServiceImpl implements SendBotMessageService {
    private final Bot bot;

    @Autowired
    public SendBotMessageServiceImpl(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        sendMessage.enableMarkdown(true);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
