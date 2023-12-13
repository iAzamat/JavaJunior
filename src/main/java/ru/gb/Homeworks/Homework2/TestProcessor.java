package ru.gb.Homeworks.Homework2;

import ru.gb.Homeworks.Homework2.Annotations.AfterEach;
import ru.gb.Homeworks.Homework2.Annotations.BeforeEach;
import ru.gb.Homeworks.Homework2.Annotations.Skip;
import ru.gb.Homeworks.Homework2.Annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestProcessor {

    /**
     * Данный метод находит все void методы без аргументов в классе, и запускает их.
     * Для запуска создается тестовый объект с помощью конструктора без аргументов.
     */
    public static void runTest(Class<?> testClass) {
        final Constructor<?> declaredConstructor;
        try {
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Для класса \"" + testClass.getName() + "\" не найден конструктор без аргументов");
        }

        final Object testObj;
        try {
            testObj = declaredConstructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
        }

        HashMap<Integer, List<Method>> methodMap = new HashMap<>();
        List<Method> beforeEach = new ArrayList<>();
        List<Method> afterEach = new ArrayList<>();

        for (Method method : testClass.getDeclaredMethods()) {
            if (checkMethod(method, BeforeEach.class, void.class, 0) &&
                    !checkMethod(method, Skip.class, void.class, 0)
            ) {
                beforeEach.add(method);
            }

            if (checkMethod(method, AfterEach.class, void.class, 0) &&
                    !checkMethod(method, Skip.class, void.class, 0)
            ) {
                afterEach.add(method);
            }

            if (checkMethod(method, Test.class, void.class, 0) &&
                    !checkMethod(method, Skip.class, void.class, 0)
            ) {

                if (!methodMap.containsKey(method.getAnnotation(Test.class).order())) {
                    List<Method> tempList = new ArrayList<Method>();
                    tempList.add(method);
                    methodMap.put(method.getAnnotation(Test.class).order(), tempList);
                } else {
                    methodMap.get(method.getAnnotation(Test.class).order()).add(method);
                }

            }
        }

        for (Integer key : methodMap.keySet().stream().sorted().toList()) {
            methodMap.get(key).forEach(keyMethod -> {
                beforeEach.forEach(t -> run(t, testObj));
                run(keyMethod, testObj);
                afterEach.forEach(t -> run(t, testObj));
            });
        }
    }

    private static boolean checkMethod(Method method, Class<? extends Annotation> annotation, Class returnClass, int parameterCount) {
        return (method.getReturnType().isAssignableFrom(returnClass) &&
                method.getParameterCount() == parameterCount &&
                method.isAnnotationPresent(annotation)
        );
    }

    private static void run(Method testMethod, Object testObj) {
        try {
            (testMethod).invoke(testObj);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось запустить метод \"" + testMethod.getName() + "\"");
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    class myMethod {
        public Method before;
        public Method test;
        public Method after;
    }
}
