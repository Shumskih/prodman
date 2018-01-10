<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
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
        <form action="update-product" method="get">
            <div class="form-group form-correction">
                <label for="product" class="label-header">Enter name of product you are going to update:</label><br>
                <input type="text" name="product" id="product" placeholder="Enter name of product here"  class="form-control"/><br>
                <button type="submit" name="submit" class="btn btn-primary">Enter</button>
                <a href="../index.jsp" class="btn btn-danger active">Cancel</a>
            </div>
        </form>
        <% List<Product> products = (List<Product>) request.getAttribute("listOfProducts");
        %>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-3 col-xs-1"></div>
            <ul class="list-of border col-lg-4 col-md-6 col-sm-6 col-xs-10">
                <% for(Product p : products) {
                %>
                <li class="list"><strong>ID: </strong><%out.println(p.getId());%><br>
                                            <strong>Name: </strong><%out.println(p.getName());%><br>
                                            <strong>Price: </strong><%out.println(p.getPrice());%><br>
                    <% try {%>
                                            <strong>Manufacturer: </strong><%out.println(p.getManufacturer().getName());%></li>
                    <%} catch (NullPointerException e) {%>
                                            no manufacturer</li>
                        <%}%>
                <% }
                %>
            </ul>
            <div class="col-lg-4 col-md-4 col-sm-3 col-xs-1"></div>
</body>
</html>
