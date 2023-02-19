package com.bot.severBot.comparator;

import com.bot.severBot.dto.Person;

import java.util.Comparator;

public class PersonBirthdayComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getCurrentBirthday().compareTo(o2.getCurrentBirthday());
    }
}
