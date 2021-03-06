package controller.manufacturer;

import dao.hibernate.HibernateManufacturerDAOImpl;
import dao.hibernate.HibernateProductDAOImpl;
import model.Manufacturer;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@WebServlet("/delete-manufacturer")
public class DeleteManufacturer extends HttpServlet {
    private String docType;
    private String h1Title;
    private String metaCharset;
    private String metaNameContent;
    private String bootstrap;
    private String css;

    public void init() throws ServletException {
        docType = "<!DOCTYPE html>";
        h1Title = "Delete Manufacturer:";
        metaCharset = "<meta charset=\"utf-8\">";
        metaNameContent = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">";
        bootstrap = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">";
        css = "<link rel=\"stylesheet\" href=\"../css/styles.css\">";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateManufacturerDAOImpl hibernateManufacturerDAO = new HibernateManufacturerDAOImpl();
        List<Manufacturer> manufacturers = hibernateManufacturerDAO.getAll();

        req.setAttribute("manufacturersList", manufacturers);
        req.getRequestDispatcher("/manufacturers/delete-manufacturer.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateManufacturerDAOImpl hibernateManufacturerDAO = new HibernateManufacturerDAOImpl();
        HibernateProductDAOImpl hibernateProductDAO = new HibernateProductDAOImpl();
        PrintWriter writer = response.getWriter();

        String manufacturerName = request.getParameter("manufacturer");

        response.setContentType("text/html");

        writer.println(docType);
        writer.println("<html lang=\"en\">");
        writer.println("<head>");
        writer.println("<!-- Required meta tags -->");
        writer.println(metaCharset);
        writer.println(metaNameContent);
        writer.println("<!-- Bootstrap CSS -->");
        writer.println(bootstrap);
        writer.println("<!-- Styles -->");
        writer.println(css);
        writer.println("<title>" + h1Title + "</title>");
        writer.println("</head>");

        writer.println("<body>");

        writer.println("<div class=\"header-h1\">");
        writer.println("<h1 class=\"text-center\">" + h1Title + "</h1>");
        writer.println("</div>");

        if(manufacturerName.trim().length() != 0) {
            Boolean isNameExists = hibernateManufacturerDAO.exists(manufacturerName);
            if (isNameExists) {
                Manufacturer manufacturer = hibernateManufacturerDAO.getbyName(manufacturerName);
                System.out.println(manufacturer.getId());
                System.out.println(manufacturer.getName());
                List<Product> products = hibernateProductDAO.getByManufacturerId(manufacturer.getId());
                for(Product p : products) {
                    UUID id = p.getId();
                    String name = p.getName();
                    BigDecimal price = p.getPrice();
                    hibernateProductDAO.delete(id);

                    Product product = new Product(id, name, price);
                    System.out.println(product.getName());

                    hibernateProductDAO.save(product);
                }
                hibernateManufacturerDAO.delete(manufacturerName);

                writer.println("<p class=\"text-center text-success margin-top label-header\">Manufacturer " + manufacturerName + " has deleted</p>");
                writer.println("<div class=\"form-group form-correction text-center\">");
                writer.print("<a href=\"delete-manufacturer\" class=\"btn btn-primary active\">Delete another one</a>");
                writer.print("<a href=\"../index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                writer.println("<div>");
            } else {
                writer.println("<p class=\"text-center text-danger margin-top label-header\">Manufacturer not found. Enter name of existing manufacturer.</p>");
                writer.println("<div class=\"form-group form-correction text-center\">");
                writer.print("<a href=\"delete-manufacturer\" class=\"btn btn-primary active\">Enter existing name</a>");
                writer.print("<a href=\"../index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                writer.println("<div>");
            }
        } else {
            writer.println("<p class=\"text-center text-danger margin-top label-header\">You're didn't enter name of manufacturer</p>");
            writer.println("<div class=\"form-group form-correction text-center\">");
            writer.print("<a href=\"delete-manufacturer\" class=\"btn btn-primary active\">Enter name</a>");
            writer.print("<a href=\"../index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
            writer.println("<div>");
        }
        writer.println("</body>");


    }
}
