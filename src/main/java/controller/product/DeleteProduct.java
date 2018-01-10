package controller.product;

import dao.hibernate.HibernateProductDAOImpl;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/delete-product")
public class DeleteProduct extends HttpServlet {
    private String docType;
    private String h1Title;
    private String metaCharset;
    private String metaNameContent;
    private String bootstrap;
    private String css;

    public void init() throws ServletException {
        docType = "<!DOCTYPE html>";
        h1Title = "Delete Product:";
        metaCharset = "<meta charset=\"utf-8\">";
        metaNameContent = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">";
        bootstrap = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">";
        css = "<link rel=\"stylesheet\" href=\"css/styles.css\">";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateProductDAOImpl hibernateProductDAO = new HibernateProductDAOImpl();
        List<Product> products = hibernateProductDAO.getAll();

        req.setAttribute("productList", products);
        req.getRequestDispatcher("/products/delete-product.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateProductDAOImpl hibernateProductDAO = new HibernateProductDAOImpl();
        PrintWriter writer = response.getWriter();

        String productName = request.getParameter("product");

        response.setContentType("text/html");

        PrintWriter messageWriter = response.getWriter();
        messageWriter.println(docType);
        messageWriter.println("<html lang=\"en\">");
        messageWriter.println("<head>");
        messageWriter.println("<!-- Required meta tags -->");
        messageWriter.println(metaCharset);
        messageWriter.println(metaNameContent);
        messageWriter.println("<!-- Bootstrap CSS -->");
        messageWriter.println(bootstrap);
        messageWriter.println("<!-- Styles -->");
        messageWriter.println(css);
        messageWriter.println("<title>" + "List Of Products" + "</title>");
        messageWriter.println("</head>");

        messageWriter.println("<body>");

        writer.println("<div class=\"header-h1\">");
        writer.println("<h1 class=\"text-center\">" + h1Title + "</h1>");
        writer.println("</div>");

        if(productName.trim().length() != 0) {

            Boolean isNameExists = hibernateProductDAO.exists(productName);
            if (isNameExists) {
                hibernateProductDAO.delete(productName);

                writer.println("<p class=\"text-center text-success margin-top label-header\">Product " + productName + " has deleted</p>");
                writer.println("<div class=\"form-group form-correction text-center\">");
                writer.print("<a href=\"delete-product\" class=\"btn btn-primary active\">Create another one</a>");
                writer.print("<a href=\"/index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                writer.println("</div>");
            } else {
                writer.println("<p class=\"text-center text-danger margin-top label-header\">Product not found. Enter name of existing product.</p>");
                writer.println("<div class=\"form-group form-correction text-center\">");
                writer.print("<a href=\"delete-product\" class=\"btn btn-primary active\">Enter name</a>");
                writer.print("<a href=\"/index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                writer.println("</div>");
            }
        } else {
            if(productName.trim().length() == 0) {
                writer.println("<p class=\"text-center text-danger margin-top label-header\">You're didn't enter name of manufacturer</p>");
                writer.println("<div class=\"form-group form-correction text-center\">");
                writer.print("<a href=\"delete-product\" class=\"btn btn-primary active\">Enter name</a>");
                writer.print("<a href=\"/index.jsp\" class=\"btn btn-danger active\">Cancel</a>");
                writer.println("</div>");
            }
        }
        writer.println("</body>");


    }
}
