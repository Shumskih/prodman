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

@WebServlet("/list-of-products")
public class ListOfProducts extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateProductDAOImpl hibernateProductDAO = new HibernateProductDAOImpl();
        List<Product> products = hibernateProductDAO.getAll();

        request.setAttribute("productsList", products);
        request.getRequestDispatcher("/products/list-of-products.jsp").forward(request, response);
    }
}
