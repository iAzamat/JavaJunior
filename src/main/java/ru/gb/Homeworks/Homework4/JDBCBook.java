package ru.gb.Homeworks.Homework4;

public class JDBCBook {
    private static int count;
    String name;
    String author;
    JDBCBook(){
        this.name = this.getClass().getName() + count;
        this.author = this.getClass().getName() + count + " author";
        count ++;
    }
    JDBCBook(String name, String author){
        this.name = name;
        this.author = author;
        count ++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
