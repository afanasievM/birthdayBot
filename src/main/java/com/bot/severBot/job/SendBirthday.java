package com.bot.severBot.job;

import com.bot.severBot.dto.Person;
import com.bot.severBot.service.PersonService;
import com.bot.severBot.service.SendBotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
@Slf4j
public class SendBirthday {
    private SendBotMessageService sendBotMessageService;
    private PersonService personService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Value("${bot.chatId}")
    private Long CHAT_ID;


    @Autowired
    public SendBirthday(SendBotMessageService sendBotMessageService, PersonService personService) {
        this.sendBotMessageService = sendBotMessageService;
        this.personService = personService;
    }

    @Scheduled(cron = "0 0 9 * * *")
//    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    public void sendTodayBirthday() {
        List<Person> persons = personService.findAllByDate(LocalDate.now());
        StringBuilder sb = new StringBuilder();
        persons.stream()
                .forEach(p -> {
                    sb.append("Наступний день народження %s у *%s*. Виповнюється *%d років*.\n\n"
                            .formatted(
                                    p.getCurrentBirthday().format(formatter),
                                    p.getName(),
                                    p.getCurrentBirthday().getYear() - p.getBirthDay().getYear())
                    );
                });
        sendBotMessageService.sendMessage(CHAT_ID, sb.toString());
    }

}
