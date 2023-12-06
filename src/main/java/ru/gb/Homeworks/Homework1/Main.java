package ru.gb.Homeworks.Homework1;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();

        //1.0 Создать список из 1_000 рандомных чисел от 1 до 1_000_000
        List<Integer> list = Stream.generate(() -> rand.nextInt(1, 1_000_001)).limit(1000).toList();

        //1.1 Найти максимальное
        Optional<Integer> maxValue = list.stream().max(Comparator.naturalOrder());
        maxValue.ifPresent(integer -> System.out.println("Максимальное число: " + integer));

        //1.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
        System.out.print("Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать: ");
        System.out.println(list.stream().filter(i -> i > 500_000).map(i -> (i * 5 - 150)).mapToLong(i -> i).sum());

        //1.3 Найти количество чисел, квадрат которых меньше, чем 100_000
        System.out.print("Найти количество чисел, квадрат которых меньше, чем 100_000: ");
        System.out.println(list.stream()
                .filter(i -> i * i < 100_000)
                .count());

        //2.1 Создать список из 10-20 сотрудников
        ArrayList<Employee> listEmployees = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listEmployees.add(new Employee());
        }

        //2.2 Вывести список всех различных отделов (department) по списку сотрудников
        System.out.println("Список всех отделов: ");
        listEmployees.stream().map(i -> i.department).distinct().sorted().forEach(System.out::println);

        //2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
        System.out.println("Следующие сотрудники имеют зарплату меньше 10_000: ");
        listEmployees.stream().filter(i -> Double.compare(i.salary, 10000.0) < 0).forEach(System.out::println);
        System.out.println("Следующим сотрудникам повышена зарплата: ");
        listEmployees.stream().filter(i -> Double.compare(i.salary, 10000.0) < 0).map(i -> new Employee(i.name, i.age, 1.2 * i.salary, i.department)).forEach(System.out::println);

        //2.4 Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
        Map<String, List<Employee>> mapEmployees = listEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.toList()));

        System.out.println("Список сотрудников по отделам: ");
        for (Entry<String, List<Employee>> employee : mapEmployees.entrySet()) {
            System.out.println("Ключ: " + employee.getKey());
            for (Employee element : employee.getValue()) {
                System.out.println("Значение: " + element.toString());
            }
        }

        //2.5 Из списка сотрудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
        Map<String, Double> averageSalaries = listEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

        System.out.println("Список средних зарплат по отделам: ");
        for (Entry<String, Double> employee : averageSalaries.entrySet()) {
            System.out.println("Ключ: " + employee.getKey());
            System.out.println("Среднее значение: " + employee.getValue());
        }
    }
}