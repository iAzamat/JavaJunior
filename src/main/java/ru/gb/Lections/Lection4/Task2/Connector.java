package ru.gb.Lections.Lection4.Task2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connector {
//    final StandardServiceRegistry registry;
//    SessionFactory sessionFactory;

    //    public Connector() {
//        registry = new StandardServiceRegistryBuilder()
//                .configure()
//                .build();
//        sessionFactory = new MetadataSources(registry)
//                .buildMetadata()
//                .buildSessionFactory();
//    }
    final SessionFactory sessionFactory;

    public Connector() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();
    }


    public Session getSession() {
        return sessionFactory.openSession();
    }
}
