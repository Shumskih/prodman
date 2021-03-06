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

        if(productName.trim().length() != 0) {

            Boolean isNameExists = hibernateProductDAO.exists(productName);
            if(!isNameExists) {
                try {
                    BigDecimal bigDecimal = new BigDecimal(productPrice);
                    Product product = new Product(UUID.randomUUID(), productName, bigDecimal, manufacturer);
                    hibernateProductDAO.save(product);

                    writer.println("<p class=\"text-center text-success margin-top label-header\">Product " + productName + " has added</p>");
                    writer.println("<div class=\"form-group form-correction text-center\">");
                    writer.print("<a href=\"add-product\" class=\"btn btn-primary active\">Create another one</a>");
                    writer.print("<a href=\"/index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                    writer.println("</div>");
                } catch (NumberFormatException e) {
                    writer.println("<p class=\"text-center text-success margin-top label-header\">You're didn't enter price of Product</p>");
                    writer.println("<div class=\"form-group form-correction text-center\">");
                    writer.print("<a href=\"add-product\" class=\"btn btn-primary active\">Enter price</a>");
                    writer.print("<a href=\"/index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                    writer.println("</div>");
                }
            } else {
                writer.print("<p class=\"text-center text-danger margin-top label-header\">Product already exists.</p>");
                writer.println("<div class=\"form-group form-correction text-center\">");
                writer.print("<a href=\"add-product\" class=\"btn btn-primary active\">Try again</a>");
                writer.print("<a href=\"../index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                writer.println("<div>");
            }
        } else {
            writer.println("<p class=\"text-center text-danger margin-top label-header\">You're didn't enter name of Product</p>");
            writer.println("<div class=\"form-group form-correction text-center\">");
            writer.print("<a href=\"add-product\" class=\"btn btn-primary active\">Enter name</a>");
            writer.print("<a href=\"../index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
            writer.println("<div>");
        }
        writer.println("</body>");
    }
}
