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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
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
                <label for="product">Enter name of product you are going to update:</label><br>
                <input type="text" name="product" id="product" placeholder="Enter name of product here"  class="form-control"/><br>
                <button type="submit" name="submit" class="btn btn-primary">Enter</button>
            </div>
        </form>
        <% List<Product> products = (List<Product>) request.getAttribute("listOfProducts");
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
</body>
</html>
