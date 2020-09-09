<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Internet Shop</title>
</head>
<body style="background: goldenrod; color: blue;">
<div style="text-align: center;">
    <span style="font-family: 'Droid Sans Mono Dotted',cursive; font-size: xx-large;">
       <a href="https://mate.academy/uk">Hello Mate!</a>
        <h4>Current time : ${time}</h4>
    </span>
</div>
<div>
    <span style="font-family: 'Droid Sans Mono Dotted',cursive; font-size: xx-large;">
        <a href="${pageContext.request.contextPath}/injectData">Inject test data into the DB!</a>
        <br>
        <a href="${pageContext.request.contextPath}/users/registration">Registration here</a>
        <br>
        <a href="${pageContext.request.contextPath}/users/all">All users here</a>
        <br>
        <a href="${pageContext.request.contextPath}/products/add">Add new product</a>
        <br>
        <a href="${pageContext.request.contextPath}/products/all">All products</a>
        <br>
        <a href="${pageContext.request.contextPath}/shopping-carts/products">Shopping cart</a>
    </span>
</div>
</body>
</html>
