package controller.manufacturer;

import dao.hibernate.HibernateManufacturerDAOImpl;
import model.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list-of-manufacturers")
public class ListOfManufacturers extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateManufacturerDAOImpl hibernateManufacturerDAO = new HibernateManufacturerDAOImpl();
        List<Manufacturer> manufacturers = hibernateManufacturerDAO.getAll();

        request.setAttribute("manufacturersList", manufacturers);
        request.getRequestDispatcher("/manufacturers/list-of-manufacturers.jsp").forward(request, response);
    }
}
