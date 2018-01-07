<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Manufacturer" %>
<%@ page import="java.util.List" %>
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

    <title>Add New Product</title>
</head>
<body>
    <div class="header-h1">
        <h1 class="text-center">Add New Product:</h1>
    </div>
    <form action="add-product" method="post">
        <div class="form-group form-correction">
            <label for="product"><strong>Enter name of product:</strong></label><br>
                <input type="text" name="product" id="product" placeholder="Enter name of product here"  class="form-control"/><br>
            <label for="price"><strong>Enter price of product:</strong></label><br>
                <input type="text" name="price" id="price" class="form-control"/><br>
            <label for="productManufacturer"><strong>Select manufacturer:</strong></label><br>

            <% List<Manufacturer> manufacturers = (List<Manufacturer>) request.getAttribute("manufacturersList");
            %>
            <select class="form-control" name="productManufacturer" id="productManufacturer">
                <% for(Manufacturer m : manufacturers) {
                %>
                <option><%out.println(m.getName());%></option>
                <% }
                %>

            </select><br>
            <button type="submit" name="submit" class="btn btn-primary">Save</button>
        </div>
    </form>
</body>
</html>
