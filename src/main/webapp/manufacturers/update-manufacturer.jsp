<%@ page import="model.Manufacturer" %>
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

        <title>Update Manufacturer</title>
    </head>
    <body>
        <div class="header-h1">
            <h1 class="text-center">Update Manufacturer:</h1>
        </div>
        <form action="update-manufacturer" method="post">
            <div class="form-group form-correction">
                <label for="oldManufacturerName">Enter name of manufacturer you are going to update:</label><br>
                <input type="text" name="oldManufacturerName" id="oldManufacturerName" placeholder="Enter old manufacturer's name"  class="form-control"/><br>
                <label for="newManufacturerName">Enter new name of manufacturer:</label><br>
                <input type="text" name="newManufacturerName" id="newManufacturerName" placeholder="Enter new manufacturer's name"  class="form-control"/><br>
                <button type="submit" name="submit" class="btn btn-primary">Update</button>
            </div>
        </form>
        <% List<Manufacturer> manufacturers = (List<Manufacturer>) request.getAttribute("manufacturersList");
        %>
        <ul class="list-of">
            <% for(Manufacturer m : manufacturers) {
            %>
            <li class="list"><strong>ID: </strong><%out.println(m.getId());%></li>
            <li class="list"><strong>Name: </strong><%out.println(m.getName());%></li>
            <li class="list last-list">=========================================</li>
            <% }
            %>
        </ul>
    </body>
</html>
