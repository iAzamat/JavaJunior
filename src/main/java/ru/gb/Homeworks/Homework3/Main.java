package ru.gb.Homeworks.Homework3;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Object> packList = new ArrayList<>();


        for (int i = 0; i <= 10; i++) {
            packList.add(new Human());
        }

        List<String> fileList = Packer.packAll(packList);
        List<Object> unpackList = Packer.unpackAll(fileList);

    }
}
