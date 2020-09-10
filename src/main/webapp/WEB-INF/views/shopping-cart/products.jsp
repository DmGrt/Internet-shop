<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>All Products</title>
</head>
<body style="font-family: 'Droid Sans Mono Dotted',cursive; font-size: x-large; color: #3131b7; background: #d69246">
<h2>All products in shopping cart</h2>
<table border="1">
    <tr>
        <th>Product</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/shopping-cart/products/delete?id=${product.id}">Delete</a>
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
<a href="${pageContext.request.contextPath}/">Back to main page</a>
</body>
</html>
