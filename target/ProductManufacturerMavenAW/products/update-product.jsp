<%@ page import="model.Manufacturer" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.UUID" %>
<%@ page import="org.apache.commons.lang.ObjectUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Styles -->
        <link rel="stylesheet" href="../css/styles.css">

        <title>Update Product</title>
    </head>
    <body>
        <div class="header-h1">
            <h1 class="text-center">Update Product:</h1>
        </div>
        <%
            UUID id = (UUID) request.getAttribute("id");
            String name = (String) request.getAttribute("productName");
            BigDecimal price = (BigDecimal) request.getAttribute("productPrice");
            Manufacturer manufacturer = (Manufacturer) request.getAttribute("productManufacturer");
            List<Manufacturer> manufacturers = (List<Manufacturer>) request.getAttribute("productManufacturers");
            Boolean select = false;
        %>
        <form action="update-product" method="post">
            <div class="form-group form-correction">
                <!-- Hidden input -->
                <input type="hidden" name="productId" value="<% out.println(id); %>" class="form-control"><br>
                <!-- End of hidden input -->
                <label for="productName" class="label-header">Enter new name of product or leave the existing value:</label><br>
                    <input type="text" name="productName" id="productName" placeholder="Enter new product's name"  value="<% out.println(name); %>" class="form-control" required><br>
                <label for="productPrice" class="label-header">Enter new price or leave the existing value:</label><br>
                    <input type="text" name="productPrice" id="productPrice" placeholder="Enter new product's price"  value="<% out.println(price); %>" class="form-control" required><br>
                <label for="productManufacturer" class="label-header">Select manufacturer or leave the existing value:</label><br>

                <select class="form-control" name="productManufacturer" id="productManufacturer">
                    <option><%
                        try {
                            out.println(manufacturer.getName());
                        } catch (NullPointerException e) {
                            select = true;
                            try {
                                for(Manufacturer m : manufacturers) {%>
                                    <option><%out.println(m.getName());%></option>
                        <%}
                            } catch (NullPointerException n) {
                                out.print("No manufacturers.Create manufacturer first if need.");
                            }
                        }
                        %>
                        <% if(!select) {
                            try {
                                for(Manufacturer m : manufacturers) {
                                    if(!m.getName().equals(manufacturer.getName())) {%>
                                    <option><%out.println(m.getName());%></option>
                                <%}
                                }
                                } catch (NullPointerException e) {%>
                                    <option><%out.println("No manufacturers. Create manufacturer if need");%></option>
                                <%}
                                }%>
                </select><br>
                <button type="submit" name="submit" class="btn btn-primary">Update</button>
                <a href="../index.jsp" class="btn btn-danger active">Cancel</a>
            </div>
        </form>
    </body>
</html>
