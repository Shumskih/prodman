import dao.HibernateUtil;
import dao.hibernate.HibernateManufacturerDAOImpl;
import dao.hibernate.HibernateProductDAOImpl;
import model.Manufacturer;
import model.Product;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        HibernateManufacturerDAOImpl hibernateManufacturerDAO = new HibernateManufacturerDAOImpl();
        HibernateProductDAOImpl hibernateProductDAO = new HibernateProductDAOImpl();

        Set<Product> products = new HashSet<Product>();

        Manufacturer mf1 = new Manufacturer(UUID.randomUUID(), "Manufacturer_1");
        Manufacturer mf2 = new Manufacturer(UUID.randomUUID(), "Manufacturer_2");

        Product product1 = new Product(UUID.randomUUID(), "Product_1", new BigDecimal(100), mf1);
        Product product2 = new Product(UUID.randomUUID(), "Product_2", new BigDecimal(200), mf2);
        products.add(product1);
        products.add(product2);

        hibernateManufacturerDAO.save(mf1);
        hibernateManufacturerDAO.save(mf2);

        hibernateProductDAO.save(product1);
        hibernateProductDAO.save(product2);

        hibernateManufacturerDAO.getAll();
        hibernateProductDAO.getAll();

//        hibernateManufacturerDAO.delete("Manufacturer");

        HibernateUtil.closeSessionFactory(sessionFactory);
    }
}
