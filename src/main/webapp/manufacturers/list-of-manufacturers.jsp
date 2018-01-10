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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
    <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-1"></div>
        <ul class="list-of border col-lg-4 col-md-6 col-sm-6 col-xs-10">
        <% for(Manufacturer m : manufacturers) {
        %>
            <li class="list "><strong>ID: </strong><%out.println(m.getId());%><br>
                <strong>Name: </strong><%out.println(m.getName());%>
            </li>
        <% }
        %>
        </ul>
        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-1"></div>
    </div>
    <div class="row text-center margin-top">
        <a href="../index.jsp" class="btn btn-success btn-lg active">Main menu</a>
    </div>
</body>
</html>
