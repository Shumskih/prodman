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

        <title>Delete Product</title>
    </head>
    <body>
        <div class="header-h1">
            <h1 class="text-center">Delete Product:</h1>
        </div>
        <form action="delete-product" method="post">
        <div class="form-group form-correction">
            <label for="product">Enter name of product you are going to delete:</label><br>
            <input type="text" name="product" id="product" placeholder="Enter name of product here"  class="form-control"/><br>
            <button type="submit" name="submit" class="btn btn-primary">Delete</button>
        </div>
        </form>
        <div class="text-center">
            There is list of products:<br>
            <% List<Product> products = (List<Product>) request.getAttribute("productList");
            for(Product p : products) {
                out.println(p);
            }
            %>
        </div>
    </body>
</html>

