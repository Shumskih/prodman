<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        <title>List Of Products</title>
    </head>
    <body>
    <div class="header-h1">
        <h1 class="text-center">List Of Products:</h1>
    </div>
    <% List<Product> products = (List<Product>) request.getAttribute("productsList");
    %>
    <div class="row  text-center">
        <ul class="list-of">
            <% for(Product p : products) {
            %>
            <li class="list text-left"><strong>ID: </strong><%out.println(p.getId());%></li>
            <li class="list  text-left"><strong>Name: </strong><%out.println(p.getName());%></li>
            <li class="list  text-left"><strong>Price: </strong><%out.println(p.getPrice());%></li>
            <li class="list  text-left"><strong>Manufacturer: </strong><%out.println(p.getManufacturer());%></li>
            <li class="list last-list  text-left">=========================================</li>
            <% }
            %>
        </ul>
    </div>
</body>
</html>

