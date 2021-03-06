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
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@WebServlet("/update-product")
public class UpdateProduct extends HttpServlet {
    private String docType;
    private String h1Title;
    private String metaCharset;
    private String metaNameContent;
    private String bootstrap;
    private String css;

    private UUID productID;

    public void init() throws ServletException {
        docType = "<!DOCTYPE html>";
        h1Title = "Update Product:";
        metaCharset = "<meta charset=\"utf-8\">";
        metaNameContent = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">";
        bootstrap = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">";
        css = "<link rel=\"stylesheet\" href=\"css/styles.css\">";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateProductDAOImpl hibernateProductDAO = new HibernateProductDAOImpl();
        HibernateManufacturerDAOImpl hibernateManufacturerDAO = new HibernateManufacturerDAOImpl();
        List<Manufacturer> manufacturers = hibernateManufacturerDAO.getAll();
        List<Product> products = hibernateProductDAO.getAll();
        PrintWriter writer = resp.getWriter();

        String productName = req.getParameter("product");
        Product product;
        Manufacturer manufacturer;
        BigDecimal productPrice;

        Boolean isNameExists = hibernateProductDAO.exists(productName);
        if(isNameExists) {
            product = hibernateProductDAO.getbyName(productName);
            manufacturer = product.getManufacturer();
            productID = product.getId();
            productPrice = product.getPrice().setScale(2, RoundingMode.HALF_DOWN);

            req.setAttribute("id", productID);
            req.setAttribute("productName", productName);
            req.setAttribute("productPrice", productPrice);
            req.setAttribute("productManufacturer", manufacturer);
            req.setAttribute("productManufacturers", manufacturers);
            req.setAttribute("listOfProducts", products);
            req.getRequestDispatcher("/products/update-product.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html");

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
            writer.print("<p class=\"text-center text-danger margin-top label-header\">Product not found.</p>");
            writer.println("<div class=\"form-group form-correction text-center\">");
            writer.print("<a href=\"../index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
            writer.println("<div>");
            writer.println("</body>");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateManufacturerDAOImpl hibernateManufacturerDAO = new HibernateManufacturerDAOImpl();
        HibernateProductDAOImpl hibernateProductDAO = new HibernateProductDAOImpl();
        PrintWriter writer = response.getWriter();

        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
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
        writer.println("<title>" + h1Title + "</title>");
        writer.println("</head>");

        writer.println("<body>");

        writer.println("<div class=\"header-h1\">");
        writer.println("<h1 class=\"text-center\">" + h1Title + "</h1>");
        writer.println("</div>");

        if(productName.trim().length() != 0) {
            try {
                Product newProduct = new Product(productID, productName, new BigDecimal(productPrice).setScale(2, RoundingMode.HALF_DOWN), manufacturer);
                hibernateProductDAO.update(newProduct);

                writer.println("<p class=\"text-center text-success margin-top label-header\">Product " + productName + " has updated</p>");
                writer.println("<div class=\"form-group form-correction text-center\">");
                writer.print("<a href=\"update-product\" class=\"btn btn-primary active\">Update another one</a>");
                writer.print("<a href=\"/index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                writer.println("</div>");
            } catch (NumberFormatException e) {
                writer.println("<p class=\"text-center text-success margin-top label-header\">You're enter incorrect price</p>");
                writer.println("<div class=\"form-group form-correction text-center\">");
                writer.print("<a href=\"/index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                writer.println("</div>");
            }
        } else {
            writer.println("<p class=\"text-center text-danger margin-top label-header\">You're didn't enter product's name</p>");
            writer.println("<div class=\"form-group form-correction text-center\">");
            writer.print("<a href=\"update-product\" class=\"btn btn-primary active\">Enter name</a>");
            writer.print("<a href=\"../index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
            writer.println("<div>");
        }
        writer.println("</body>");
    }
}
