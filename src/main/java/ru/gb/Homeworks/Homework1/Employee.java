package ru.gb.Homeworks.Homework1;

import java.util.Random;

/**
 * Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 */
public class Employee {
    private static int counter = 0;
    private static Random rand = new Random();

    public String getName() {
        return name;
    }

    String name;
    int age;

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    double salary;
    String department;

    public Employee() {
        counter++;
        this.name = "Employee " + counter;
        this.age = rand.nextInt(18, 65);
        this.salary = rand.nextDouble(5_000, 100_000);
        this.department = "Department #" + rand.nextInt(1, 4);
    }

    public Employee(String name, int age, double salary, String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s/ Возраст: %d/ Зарплата: %f/ Отдел: %s", this.name, this.age, this.salary, this.department);
    }
}
