package controller.product;

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

@WebServlet("/add-product")
public class AddProduct extends HttpServlet {
    private String docType;
    private String h1Title;
    private String metaCharset;
    private String metaNameContent;
    private String bootstrap;
    private String css;

    public void init() throws ServletException {
        docType = "<!DOCTYPE html>";
        h1Title = "Add New Product:";
        metaCharset = "<meta charset=\"utf-8\">";
        metaNameContent = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">";
        bootstrap = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">";
        css = "<link rel=\"stylesheet\" href=\"css/styles.css\">";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateManufacturerDAOImpl hibernateManufacturerDAO = new HibernateManufacturerDAOImpl();
        List<Manufacturer> manufacturers = hibernateManufacturerDAO.getAll();

        req.setAttribute("manufacturersList", manufacturers);
        req.getRequestDispatcher("/products/add-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateProductDAOImpl hibernateProductDAO = new HibernateProductDAOImpl();
        HibernateManufacturerDAOImpl hibernateManufacturerDAO =  new HibernateManufacturerDAOImpl();
        PrintWriter writer = response.getWriter();

        String productName = request.getParameter("product");
        String productPrice = request.getParameter("price");
        String productManufacturer = request.getParameter("productManufacturer");
        boolean submit = request.getParameter("submit") != null;
        BigDecimal bigDecimal = new BigDecimal(productPrice);
        Manufacturer manufacturer = hibernateManufacturerDAO.getbyName(productManufacturer);

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
        writer.println("<title>" + "Add New Product" + "</title>");
        writer.println("</head>");

        writer.println("<body>");

        writer.println("<div class=\"header-h1\">");
        writer.println("<h1 class=\"text-center\">" + h1Title + "</h1>");
        writer.println("</div>");

        if(!productName.trim().isEmpty()) {
            Product product = new Product(UUID.randomUUID(), productName, bigDecimal, manufacturer);
            hibernateProductDAO.save(product);

            writer.println("<p class=\"text-center text-success\">Product " + productName + " has added</p>");
            writer.println("<p class=\"text-center\"><a href=\"/index.jsp\" class=\"btn btn-default btn-lg active\">Go to main menu --></a></p>");
        } else {
            if(productName.trim().length() == 0) {
                writer.println("<p class=\"text-center text-danger\">Please, enter name of product</p>");
                writer.println("<p class=\"text-center\"><a href=\"add-product\" class=\"btn btn-default btn-lg active\"><-- Back</a></p>");
            }
        }
        writer.println("</body>");
    }
}
