<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous">
    </script>
</head>
<body style="font-family: 'Droid Sans Mono Dotted',cursive; font-size: x-large; color: #3131b7; background: #d69246">
<h3>Hi! Fill in the forms to add product</h3>
<form method="post" action="${pageContext.request.contextPath}/product/add">
    <label for="name">Name :</label><br>
    <input id="name" type="text" required="required" name="name"><br>
    <br>
    <label for="price">Price :</label><br>
    <input id="price" type="number" required="required" name="price"><br>
    <br>
    <button type="submit">Add product</button>
    <br>
    <br>
    <a href="${pageContext.request.contextPath}/">Back to main page</a>
</form>
</body>
</html>
