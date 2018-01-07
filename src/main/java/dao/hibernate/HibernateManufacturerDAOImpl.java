package dao.hibernate;

import dao.GenericDAO;
import dao.HibernateUtil;
import model.Manufacturer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HibernateManufacturerDAOImpl implements GenericDAO<Manufacturer, UUID> {
    HibernateUtil hibernateUtil = new HibernateUtil();
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void save(Manufacturer manufacturer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.save(manufacturer);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        }
    }

    public Manufacturer getbyName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Manufacturer manufacturer = null;
        List<Manufacturer> manufacturers = null;

        try {
            transaction = session.beginTransaction();
            manufacturers = session.createQuery("FROM Manufacturer WHERE name = :name").setParameter("name", name).list();
            for(Manufacturer m : manufacturers) {
                manufacturer = m;
            }

            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        }
        return manufacturer;
    }

    public List<Manufacturer> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

        try {
            transaction = session.beginTransaction();
            manufacturers = session.createQuery("FROM Manufacturer").list();

            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        }
        return manufacturers;
    }

    public void update(Manufacturer manufacturer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        UUID id = manufacturer.getId();
        String name = manufacturer.getName();


        try {
            transaction = session.beginTransaction();
            manufacturer = session.get(Manufacturer.class, id);

            manufacturer.setName(name);

            session.update(manufacturer);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            e.printStackTrace();
        }
    }

    public void delete(UUID id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Manufacturer manufacturer = session.get(Manufacturer.class, id);
            session.delete(manufacturer);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE Manufacturer WHERE name = :name")
                    .setParameter("name", name)
                    .executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        }
    }
}
