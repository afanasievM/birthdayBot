package com.bot.severBot.dto;

import java.time.LocalDate;

public class Person {
    String name;
    LocalDate birthDay;
    LocalDate currentBirthday;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public LocalDate getCurrentBirthday() {
        return currentBirthday;
    }

    public void setCurrentBirthday(LocalDate currentBirthday) {
        this.currentBirthday = currentBirthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!getName().equals(person.getName())) return false;
        if (!getBirthDay().equals(person.getBirthDay())) return false;
        return getCurrentBirthday().equals(person.getCurrentBirthday());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getBirthDay().hashCode();
        result = 31 * result + getCurrentBirthday().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", nextBirthday=" + currentBirthday +
                '}';
    }
}
