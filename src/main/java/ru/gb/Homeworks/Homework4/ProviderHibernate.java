package ru.gb.Homeworks.Homework4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.sql.Statement;

public class ProviderHibernate {

    private Configuration cfg;
    private SessionFactory sessionFactory;
    private Session session;
    ProviderHibernate(){
//        cfg = new Configuration().addResource("hibernate.cfg.xml");
//        sessionFactory = cfg.buildSessionFactory();
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
//        sessionFactory = cfg.buildSessionFactory(builder.build());
    }
    public void connect(){
        session = sessionFactory.openSession();
        System.out.println("Hibernate connected");
    }

    public void disconect(){
        session.close();
        System.out.println("Hibernate disconnected");
    }

    public void insertIntoTable(Object object){
        if (session != null && object != null) {
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        }
    }
    public void updateIntoTable(Object object){
        if (session != null && object != null) {
            Transaction transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        }
    }

    public Object uploadFromTable(int id){
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
