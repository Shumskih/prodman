package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    public HibernateUtil() {

    }

    public static SessionFactory getSessionFactory() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory;
    }

    public static void closeSessionFactory(SessionFactory sf) {
        sf.close();
        sessionFactory.close();
    }
}
