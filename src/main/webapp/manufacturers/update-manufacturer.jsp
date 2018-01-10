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

        <title>Update Manufacturer</title>
    </head>
    <body>
        <div class="header-h1">
            <h1 class="text-center">Update Manufacturer:</h1>
        </div>
        <form action="update-manufacturer" method="post">
            <div class="form-group form-correction">
                <label for="oldManufacturerName" class="label-header">Enter name of manufacturer you are going to update:</label><br>
                <input type="text" name="oldManufacturerName" id="oldManufacturerName" placeholder="Enter old manufacturer's name"  class="form-control" required/><br>
                <label for="newManufacturerName" class="label-header">Enter new name of manufacturer:</label><br>
                <input type="text" name="newManufacturerName" id="newManufacturerName" placeholder="Enter new manufacturer's name"  class="form-control" required/><br>
                <button type="submit" name="submit" class="btn btn-primary active">Update</button>
                <a href="../index.jsp" class="btn btn-danger active">Cancel</a>
            </div>
        </form>
        <h3 class="text-center margin-top">List Of Manufactures:</h3>
        <% List<Manufacturer> manufacturers = (List<Manufacturer>) request.getAttribute("manufacturersList");
        %>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-3 col-xs-1"></div>
        <ul class="list-of border col-lg-4 col-md-6 col-sm-6 col-xs-10">
            <% try {
                for(Manufacturer m : manufacturers) {
            %>
            <li class="list"><strong>ID: </strong><%out.println(m.getId());%><br>
                            <strong>Name: </strong><%out.println(m.getName());%>
            </li>
            <% }
            } catch (NullPointerException e) {
            %>
            <li class="list">No manufactures.</li>
            <%
            }
            %>
        </ul>
            <div class="col-lg-4 col-md-4 col-sm-3 col-xs-1"></div>
        </div>
    </body>
</html>
