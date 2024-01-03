package ru.gb.Lections.Lection4.Task2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class Db {
    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";

    public static void con() {
//        Connector connector = new Connector();
//        Session session = connector.getSession();
//        Magic magic = new Magic("Волшебная стрела", 10, 0, 0); session.beginTransaction();
//        session.save(magic);
//        magic = new Magic("Молния", 25, 0, 0); session.save(magic);
//        magic = new Magic("Каменная кожа", 0, 0, 6); session.save(magic);
//        magic = new Magic("Жажда крови", 0, 6, 0); session.save(magic);
//        magic = new Magic("Ледяная стрела", 9, 0, 0); session.save(magic);
//        magic = new Magic("Проклятие", 0, -3, 0); session.save(magic);
//        magic = new Magic("Лечение", -30, 0, 0); session.save(magic);
//        session.getTransaction().commit();
//        session.close();

//        Connector connector = new Connector();
//        try (Session session = connector.getSession()) {
//            List<Magic> books = session.createQuery("FROM Magic ", Magic.class).getResultList();
//            books.forEach(System.out::println);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        Connector connector = new Connector();
//        try (Session session = connector.getSession()) {
//            String hql = "from Magic where id= :id";
//
//            Query<Magic> query = session.createQuery(hql, Magic.class);
//            query.setParameter("id", 4);
//            Magic magic = query.getSingleResult();
//            System.out.println(magic);
//            magic.setAttBonus(100);
//            magic.setName("Ярость");
//            session.beginTransaction();
//            session.update(magic);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            Transaction t = session.beginTransaction();
            List<Magic> magics = session.createQuery("FROM Magic ", Magic.class).getResultList();
            magics.forEach(m -> session.delete(m));
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
