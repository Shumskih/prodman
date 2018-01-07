<%@ page import="model.Manufacturer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>List Of Manufacturers</title>
</head>
<body>
    <div class="header-h1">
        <h1 class="text-center">List Of Manufacturers:</h1>
    </div>
    <% List<Manufacturer> manufacturers = (List<Manufacturer>) request.getAttribute("manufacturersList");
    %>
    <div class="row  text-center">
        <ul class="list-of">
        <% for(Manufacturer m : manufacturers) {
        %>
            <li class="list text-left"><strong>ID: </strong><%out.println(m.getId());%></li>
            <li class="list  text-left"><strong>Name: </strong><%out.println(m.getName());%></li>
            <li class="list last-list  text-left">=========================================</li>
        <% }
        %>
        </ul>
    </div>
</body>
</html>
