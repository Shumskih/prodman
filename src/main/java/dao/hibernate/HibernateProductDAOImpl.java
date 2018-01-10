package dao.hibernate;

import dao.GenericDAO;
import dao.HibernateUtil;
import model.Manufacturer;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HibernateProductDAOImpl implements GenericDAO<Product, UUID> {
    HibernateUtil hibernateUtil = new HibernateUtil();
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void save(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Product getbyName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Product product = null;
        List<Product> products = null;

        try {
            transaction = session.beginTransaction();
            products = session.createQuery("FROM Product WHERE name = :name").setParameter("name", name)
                    .list();
            for(Product p : products) {
                product = p;
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }

    public List<Product> getByManufacturerId(UUID manufacturerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Product> products = null;

        try {
            transaction = session.beginTransaction();
            products = session.createQuery("FROM Product where manufacturer.id = :manufacturerId")
                    .setParameter("manufacturerId", manufacturerId)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            sessionFactory = null;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    public Boolean exists (String productName) {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("select 1 from Product where name = :name")
                    .setParameter("name", productName);
        return (query.uniqueResult() != null);
    }

    public List<Product> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Product> products = new ArrayList<Product>();

        try {
            transaction = session.beginTransaction();
            products = session.createQuery("FROM Product").list();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    public void update(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        UUID id = product.getId();
        String name = product.getName();
        BigDecimal price = product.getPrice();

        Manufacturer manufacturer = product.getManufacturer();

        try {
            transaction = session.beginTransaction();
            product = session.get(Product.class, id);

            product.setName(name);
            product.setManufacturer(manufacturer);
            product.setPrice(price);

            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(UUID id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE Product WHERE name = :name")
                    .setParameter("name", name)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            HibernateUtil.closeSessionFactory(sessionFactory);
            sessionFactory = null;
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
