package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        try {
            if(sessionFactory == null) {
                sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            }
        } catch (Throwable e) {
            System.err.println("Error in creating SessionFactory object." + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory(SessionFactory sf) {
        sf.close();
        sessionFactory.close();
    }
}
