package ru.gb.Homeworks.Homework2;

import ru.gb.Homeworks.Homework2.Annotations.AfterEach;
import ru.gb.Homeworks.Homework2.Annotations.BeforeEach;
import ru.gb.Homeworks.Homework2.Annotations.Skip;
import ru.gb.Homeworks.Homework2.Annotations.Test;

public class Main {

    /**
     * Расширить пример с запуском тестов следующими фичами:
     * 1. Добавить аннотации BeforeEach, AfterEach,
     * которые ставятся над методами void без аругментов и запускаются ДО и ПОСЛЕ всех тестов соответственно.
     * 2. В аннотацию Test добавить параметр order() со значением 0 по умолчанию.
     * Необходимо при запуске тестов фильтровать тесты по этому параметру (от меньшего к большему).
     * Т.е. если есть методы @Test(order = -2) void first, @Test void second, Test(order = 5) void third,
     * то порядок вызовов first -> second -> third
     * 3.* Добавить аннотацию @Skip, которую можно ставить над тест-методами. Если она стоит - то тест не запускается.
     * 4.* При наличии идей, реализовать их и написать об этом в комментарии при сдаче.
     */


    public static void main(String[] args) {
        TestProcessor.runTest(MyTest.class);
    }

    static class MyTest {

        @AfterEach
        void clear1() {
            System.out.println("Завершающий метод 1");
        }


        @AfterEach
        void clear2() {
            System.out.println("Завершающий метод 2");
        }


        @AfterEach
        void clear3() {
            System.out.println("Завершающий метод 3");
        }


        @BeforeEach
        void init1() {
            System.out.println("Подготовительный метод 1");
        }

        @BeforeEach
        void init2() {
            System.out.println("Подготовительный метод 2");
        }

        @Test(order = -2)
        void test1() {
            System.out.println("Очередь -2: test1 запущен");
        }

        @Test(order = 1)
        void test2() {
            System.out.println("Очередь 1: test2 запущен");
        }

        @Test(order = 1)
        void test3() {
            System.out.println("Очередь 1: test3 запущен");
        }

        @Test
        @Skip
        void test4() {
            System.out.println("Очередь 0: test4 запущен");
        }

        @Test(order = 1)
        void test5() {
            System.out.println("Очередь 1: test5 запущен");
        }

        @Test()
        void test6() {
            System.out.println("Очередь 0: test6 запущен");
        }
    }


}
