package com.bot.severBot.commands;

import com.bot.severBot.comparator.PersonBirthdayComparator;
import com.bot.severBot.dto.Person;
import com.bot.severBot.service.GoogleSheetService;
import com.bot.severBot.service.PersonService;
import com.bot.severBot.service.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Component
public class NextBirthdayCommand implements Command {
    private PersonService personService;
    private SendBotMessageService sendBotMessageService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Autowired
    public NextBirthdayCommand(PersonService personService, SendBotMessageService sendBotMessageService) {
        this.personService = personService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        PersonBirthdayComparator comparator = new PersonBirthdayComparator();
        List<Person> persons = personService.findAll()
                .stream()
                .filter(p -> !p.getCurrentBirthday().isBefore(LocalDate.now()))
                .sorted(comparator)
                .toList();
        LocalDate nextBirthDate = persons.get(0).getCurrentBirthday();
        StringBuilder sb = new StringBuilder();
        persons.stream()
                .filter(p -> p.getCurrentBirthday().isEqual(nextBirthDate))
                .forEach(p -> {
                    sb.append("Наступний день народження %s у *%s*. Виповнюється *%d років*.\n\n"
                            .formatted(
                                    p.getCurrentBirthday().format(formatter),
                                    p.getName(),
                                    p.getCurrentBirthday().getYear() - p.getBirthDay().getYear())
                    );
                });
        sendBotMessageService.sendMessage(chatId, sb.toString());
    }

}

