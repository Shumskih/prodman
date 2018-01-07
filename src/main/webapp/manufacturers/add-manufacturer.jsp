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

    <title>Add New Manufacturer</title>
</head>
<body>
<div class="header-h1">
    <h1 class="text-center">Add New Manufacturer:</h1>
</div>
<form action="add-manufacturer" method="post">
    <div class="form-group form-correction">
        <label for="manufacturer">Enter name of manufacturer:</label><br>
        <input type="text" name="manufacturer" id="manufacturer" placeholder="Enter name of manufacturer here"  class="form-control"/><br>
        <button type="submit" name="submit" class="btn btn-primary">Save</button>
    </div>
</form>

</body>
</html>
