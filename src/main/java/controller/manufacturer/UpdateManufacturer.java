package controller.manufacturer;

import dao.hibernate.HibernateManufacturerDAOImpl;
import model.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet("/update-manufacturer")
public class UpdateManufacturer extends HttpServlet {
    private String docType;
    private String h1Title;
    private String metaCharset;
    private String metaNameContent;
    private String bootstrap;
    private String css;

    public void init() throws ServletException {
        docType = "<!DOCTYPE html>";
        h1Title = "Update Manufacturer:";
        metaCharset = "<meta charset=\"utf-8\">";
        metaNameContent = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">";
        bootstrap = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css\" integrity=\"sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy\" crossorigin=\"anonymous\">";
        css = "<link rel=\"stylesheet\" href=\"css/styles.css\">";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateManufacturerDAOImpl hibernateManufacturerDAO = new HibernateManufacturerDAOImpl();
        List<Manufacturer> manufacturers = hibernateManufacturerDAO.getAll();

        req.setAttribute("manufacturersList", manufacturers);
        req.getRequestDispatcher("/manufacturers/update-manufacturer.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateManufacturerDAOImpl hibernateManufacturerDAO = new HibernateManufacturerDAOImpl();
        PrintWriter writer = response.getWriter();

        String oldManufacturerName = request.getParameter("oldManufacturerName");
        String newManufacturerName = request.getParameter("newManufacturerName");
        boolean submit = request.getParameter("submit") != null;

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
        messageWriter.println("<title>" + "List Of Manufacturers" + "</title>");
        messageWriter.println("</head>");

        messageWriter.println("<body>");

        writer.println("<div class=\"header-h1\">");
        writer.println("<h1 class=\"text-center\">" + h1Title + "</h1>");
        writer.println("</div>");

        if((!oldManufacturerName.trim().isEmpty()) && (!newManufacturerName.trim().isEmpty())) {

            Manufacturer oldManufacturer = hibernateManufacturerDAO.getbyName(oldManufacturerName);
            UUID id = oldManufacturer.getId();
            String name = oldManufacturer.getName();
            Manufacturer newManufacturer = new Manufacturer(id, newManufacturerName);
            hibernateManufacturerDAO.update(newManufacturer);

            writer.println("<p class=\"text-center\">Manufacturer " + oldManufacturerName + " has updated with new name " + newManufacturerName + "</p>");
            writer.println("<p class=\"text-center\"><a href=\"/index.jsp\">Go to main menu --></a></p>");
        } else {
                writer.println("<p class=\"text-center\">Please, enter name of manufacturer</p>");
                writer.println("<p class=\"text-center\"><a href=\"update-manufacturer.jsp\">Back</a></p>");
        }
        writer.println("</body>");
    }
}
