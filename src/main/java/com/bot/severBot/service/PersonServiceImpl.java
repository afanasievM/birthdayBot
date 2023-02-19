package com.bot.severBot.service;

import com.bot.severBot.dto.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private GoogleSheetService googleSheetService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public PersonServiceImpl(GoogleSheetService googleSheetService) {
        this.googleSheetService = googleSheetService;
    }

    @Override
    public List<Person> findAll() {
        return parse(googleSheetService.getRange("B2:C1000"));
    }

    @Override
    public List<Person> findAllByDate(LocalDate date) {
        return findAll().stream().filter(p->p.getCurrentBirthday().isEqual(date)).toList();
    }


    private List<Person> parse(List<List<Object>> list) {
        List<Person> persons = new ArrayList<>();
        for (Object obj : list) {
            String[] arr = obj.toString()
                    .replace("]", "")
                    .replace("[", "")
                    .split(",");
            Person person = new Person();
            person.setName(arr[0].strip());
            person.setBirthDay(LocalDate.parse(arr[1].strip(), formatter));
            person.setCurrentBirthday(person.getBirthDay().withYear(LocalDate.now().getYear()));
            persons.add(person);
        }
        return persons;
    }
}
