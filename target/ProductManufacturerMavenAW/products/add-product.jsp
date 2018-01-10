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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
            <label for="product" class="label-header"><strong>Enter name of product:</strong></label><br>
                <input type="text" name="product" id="product" placeholder="Enter name of product here"  class="form-control" required/><br>
            <label for="price" class="label-header"><strong>Enter price of product:</strong></label><br>
                <input type="text" name="price" id="price" class="form-control" required/><br>
            <label for="productManufacturer" class="label-header"><strong>Select manufacturer:</strong></label><br>

            <% List<Manufacturer> manufacturers = (List<Manufacturer>) request.getAttribute("manufacturersList");
            %>
            <select class="form-control" name="productManufacturer" id="productManufacturer">
                <% try {
                    for(Manufacturer m : manufacturers) {
                %>
                <option><%out.println(m.getName());%></option>
                <% }
                } catch (NullPointerException e){
                %>
                <option></option>
                <option>No manufactures. Create manufacture first.</option>
                <%
                }
                %>

            </select><br>
            <button type="submit" name="submit" class="btn btn-primary">Save</button>
            <a href="../index.jsp" class="btn btn-danger active">Cancel</a>
        </div>
    </form>
</body>
</html>
