package controller.product;

import dao.hibernate.HibernateProductDAOImpl;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/choose-product")
public class ChooseProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateProductDAOImpl hibernateProductDAO = new HibernateProductDAOImpl();
        List<Product> products = hibernateProductDAO.getAll();

        req.setAttribute("listOfProducts", products);
        req.getRequestDispatcher("/products/choose-product.jsp").forward(req, resp);
    }
}
