package ru.gb.Homeworks.Homework3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Packer {
    public static String pack(Serializable object) throws IOException {
        String fileName = object.getClass().getName() + "_" + UUID.randomUUID() + ".pkg";
        Path path = Path.of(fileName);

        if (!Files.exists(path)) {
            Files.createFile(path);
        } else {
            Files.deleteIfExists(path);
            Files.createFile(path);
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public static Serializable unpack(String fileName) throws ClassNotFoundException {
        Path path = Path.of(fileName);

        if (Files.exists(path)) {
            try (ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(path))) {
                Serializable object = (Serializable) stream.readObject();
                Files.delete(path);
                return (object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<String> packAll(List<Object> objectsList) {
        List<String> resultList = new ArrayList<>();
        //упаковка
        System.out.println("Packing: ");
        objectsList.forEach(it -> {
            try {
                System.out.println(it.toString());
                resultList.add(pack((Serializable) it));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return resultList;
    }

    public static List<Object> unpackAll(List<String> fileList) {
        List<Object> resultList = new ArrayList<>();

        //распаковка
        System.out.println("Unpacking: ");
        for (String file : fileList) {
            try {
                System.out.println(file);
                resultList.add(Packer.unpack(file));
                System.out.println(resultList.get(resultList.size() - 1).toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}