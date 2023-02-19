package com.bot.severBot.service;

public interface SendBotMessageService {
    void sendMessage(Long chatId, String message);
}
