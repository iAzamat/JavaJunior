package ru.gb.Homeworks.Homework4;
public class Main {
    public static void main(String[] args) {
        final String DB_URL = "jdbc:mysql://localhost:32769";
        final String DB_USER = "root";
        final String DB_PASSWORD = "admin";
        final String DB_Name = "LibraryShelf";

        //1.1 - 1.3
        ProviderJDBC providerJDBC = new ProviderJDBC(DB_URL, DB_USER, DB_PASSWORD);
        providerJDBC.connect();
        providerJDBC.createDB(DB_Name);
        providerJDBC.createTable(DB_Name, "JDBCshelf", JDBCBook.class);
        JDBCBook JDBCBook1 = new JDBCBook("Book 1", "Author 1");
        JDBCBook JDBCBook2 = new JDBCBook("Book 2", "Author 2");
        JDBCBook JDBCBook3 = new JDBCBook("Book 3", "Author 3");
        JDBCBook JDBCBook4 = new JDBCBook();
        JDBCBook JDBCBook5 = new JDBCBook();
        JDBCBook JDBCBook6 = new JDBCBook();
        JDBCBook JDBCBook7 = new JDBCBook();
        JDBCBook JDBCBook8 = new JDBCBook();
        JDBCBook JDBCBook9 = new JDBCBook();
        JDBCBook JDBCBook10 = new JDBCBook();
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook1);
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook2);
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook3);
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook4);
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook5);
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook6);
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook7);
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook8);
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook9);
        providerJDBC.insertIntoTable(DB_Name, "JDBCshelf", JDBCBook10);
        JDBCBook3.setName("new book 3 name");
        providerJDBC.updateIntoTable(DB_Name, "JDBCshelf", JDBCBook3);
        JDBCBook4 = (JDBCBook) providerJDBC.findIntoTable(DB_Name, "JDBCshelf", JDBCBook2);
        System.out.println("Book 4 name: " + JDBCBook4.name + "/author: " + JDBCBook4.author);
        providerJDBC.disconnect();
        //2.1 - 2.3
        ProviderHibernate providerHibernate = new ProviderHibernate();
        providerHibernate.connect();
        HibernateBook hibernateBook1 = new HibernateBook("Book 1", "Author 1");
        HibernateBook hibernateBook2 = new HibernateBook("Book 2", "Author 2");
        HibernateBook hibernateBook3 = new HibernateBook("Book 3", "Author 3");
        HibernateBook hibernateBook4 = new HibernateBook("Book 4", "Author 4");
        HibernateBook hibernateBook5 = new HibernateBook("Book 5", "Author 5");
        HibernateBook hibernateBook6 = new HibernateBook("Book 6", "Author 6");
        HibernateBook hibernateBook7 = new HibernateBook("Book 7", "Author 7");
        HibernateBook hibernateBook8 = new HibernateBook("Book 8", "Author 8");
        HibernateBook hibernateBook9 = new HibernateBook("Book 9", "Author 9");
        HibernateBook hibernateBook10 = new HibernateBook("Book 10", "Author 10");
        providerHibernate.insertIntoTable(hibernateBook1);
        providerHibernate.insertIntoTable(hibernateBook2);
        providerHibernate.insertIntoTable(hibernateBook3);
        providerHibernate.insertIntoTable(hibernateBook4);
        providerHibernate.insertIntoTable(hibernateBook5);
        providerHibernate.insertIntoTable(hibernateBook6);
        providerHibernate.insertIntoTable(hibernateBook7);
        providerHibernate.insertIntoTable(hibernateBook8);
        providerHibernate.insertIntoTable(hibernateBook9);
        providerHibernate.insertIntoTable(hibernateBook10);

        hibernateBook3.setName("Book 3 renamed");

        providerHibernate.deleteIntoTable(hibernateBook2);
        providerHibernate.updateIntoTable(hibernateBook3);

        providerHibernate.disconect();
    }
}
