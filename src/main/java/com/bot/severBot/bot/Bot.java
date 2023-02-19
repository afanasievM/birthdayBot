package com.bot.severBot.bot;


import com.bot.severBot.commands.CommandContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {

    final private String COMMAND_PREFIX = "/";
    final private String COMMAND_SPLITTER = " ";
    private final CommandContainer commandContainer;

    @Value("${bot.botName}")
    private String botName;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.adminId}")
    private Long adminId;


    @Autowired
    public Bot(CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getFrom().getId().equals(adminId)) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(COMMAND_SPLITTER)[0].toLowerCase();
                log.info(update.toString());
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.noCommand().execute(update);
            }
        }
    }


    //
    @Override
    public String getBotUsername() {
        return botName;
    }


    @Override
    public String getBotToken() {
        return token;
    }

}
