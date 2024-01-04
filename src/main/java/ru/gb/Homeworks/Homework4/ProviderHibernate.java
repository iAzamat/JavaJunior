package ru.gb.Homeworks.Homework4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProviderHibernate {
    private final SessionFactory sessionFactory;
    private Session session;

    ProviderHibernate() {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure()
//                .build();
//        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        sessionFactory = new Configuration()
                .configure("hibernate3.cfg.xml").buildSessionFactory();
    }

    public void connect() {
        session = sessionFactory.openSession();
        System.out.println("Hibernate connected");
    }

    public void disconnect() {
        session.close();
        System.out.println("Hibernate disconnected");
    }

    public void insertIntoTable(Object object) {
        if (session != null && object != null) {
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        }
    }

    public void updateIntoTable(Object object) {
        if (session != null && object != null) {
            Transaction transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        }
    }

    public Object uploadFromTable(int id) {
        if (session != null) {
            return session.get(Object.class, id);
        }
        return null;
    }

    public void deleteIntoTable(Object object) {
        if (session != null) {
            Transaction transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        }
    }


}
