package com.bot.severBot.service;

import com.bot.severBot.dto.Person;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;

public interface PersonService {
    List<Person> findAll();
    List<Person> findAllByDate(LocalDate date);
}
