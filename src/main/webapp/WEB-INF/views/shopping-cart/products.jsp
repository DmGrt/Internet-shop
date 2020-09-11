<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>All Products</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<style>
    body {
        margin-top: 20px;
        margin-left: 50px;
    }
</style>
<body style="font-family: Bahnschrift,cursive; font-size: large;
background-image: linear-gradient(#4a4a4a, #000000); color: #f68f13;">
<h2>All products in shopping cart</h2>
<table class="table" style="width: 50%">
    <thead class="thead-light">
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th> </th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr style="color: ghostwhite">
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a style="color: #f68f13"
                   href="${pageContext.request.contextPath}/shopping-cart/products/delete?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
Total :<br>
Number of products - ${numberOfProducts}<br>
Price - ${cartPrice}<br>
<br>
<form method="get" action="${pageContext.request.contextPath}/order/create">
    <button type="submit">Confirm order</button>
</form>
<br>
<br>
<a style="color: #f68f13" href="${pageContext.request.contextPath}/">Back to main page</a>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>
