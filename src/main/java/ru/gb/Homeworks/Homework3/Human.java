package ru.gb.Homeworks.Homework3;

import java.io.Serializable;
import java.util.Random;

public class Human implements Serializable {
    private String firstName;
    private String secondName;
    private String patronymic;
    private Random rand = new Random();

    public Human() {
        int number = rand.nextInt();
        firstName = "firstName " + number;
        secondName = "secondName " + number;
        patronymic = "patronymic " + number;
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
