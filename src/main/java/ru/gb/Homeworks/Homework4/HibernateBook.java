package ru.gb.Homeworks.Homework4;

import javax.persistence.*;
@Entity
@Table (name = "HibernateShelf")
public class HibernateBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;


    public HibernateBook() {
    }

    public HibernateBook(String name, String author) {
        this.name = name;
        this.author = author;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }


}
