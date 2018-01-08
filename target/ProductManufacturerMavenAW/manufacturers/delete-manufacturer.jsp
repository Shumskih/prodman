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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Styles -->
    <link rel="stylesheet" href="../css/styles.css">

    <title>Delete Manufacturer</title>
</head>
<body>
    <div class="header-h1">
        <h1 class="text-center">Delete Manufacturer:</h1>
    </div>
    <form action="delete-manufacturer" method="post">
        <div class="form-group form-correction">
            <label for="manufacturer">Enter name of manufacturer:</label><br>
            <input type="text" name="manufacturer" id="manufacturer" placeholder="Enter name of manufacturer here"  class="form-control"/><br>
            <button type="submit" name="submit" class="btn btn-primary">Delete</button>
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
