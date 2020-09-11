<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Internet Shop</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
</head>
<style>
    body {
        margin-top: 20px;
        margin-left: 50px;
    }
</style>
<body style="background-image: linear-gradient(#4a4a4a, #000000); color: #ea9409;">
<div style="text-align: center;">
    <span style="font-family: Bahnschrift,cursive; font-size: x-large;">
       <a href="https://mate.academy/uk">Hello Mate!</a>
        <h4>Current time : ${time}</h4>
    </span>
</div>
<div>
    <div class="spinner-grow text-muted"></div>
    <div class="spinner-grow text-primary"></div>
    <div class="spinner-grow text-success"></div>
    <div class="spinner-grow text-info"></div>
    <div class="spinner-grow text-warning"></div>
    <div class="spinner-grow text-danger"></div>
    <div class="spinner-grow text-secondary"></div>
    <div class="spinner-grow text-light"></div>
</div>
<div>
    <span style="font-family: Bahnschrift,cursive; font-size: x-large;">
        <a style="color: #f68f13" href="${pageContext.request.contextPath}/injectData">Inject test data into the DB!</a>
        <br>
        <a style="color: #f68f13" href="${pageContext.request.contextPath}/user/registration">Registration here</a>
        <br>
        <a style="color: #f68f13" href="${pageContext.request.contextPath}/user/all">All users here (for admin)</a>
        <br>
        <a style="color: #f68f13" href="${pageContext.request.contextPath}/product/add">Add new product (for admin)</a>
        <br>
        <a style="color: #f68f13" href="${pageContext.request.contextPath}/product/all">All products</a>
        <br>
        <a style="color: #f68f13" href="${pageContext.request.contextPath}/shopping-cart/products">Shopping cart</a>
        <br>
        <a style="color: #f68f13" href="${pageContext.request.contextPath}/order/by-user">User orders</a>
        <br>
        <a style="color: #f68f13" href="${pageContext.request.contextPath}/order/all">All orders (for admin)</a>
        <br>
        <a style="color: #f68f13"
           href="${pageContext.request.contextPath}/product/get-for-manage">Product managing (for admin)</a>
    </span>
</div>
<div>
    <div class="spinner-grow text-muted"></div>
    <div class="spinner-grow text-primary"></div>
    <div class="spinner-grow text-success"></div>
    <div class="spinner-grow text-info"></div>
    <div class="spinner-grow text-warning"></div>
    <div class="spinner-grow text-danger"></div>
    <div class="spinner-grow text-secondary"></div>
    <div class="spinner-grow text-light"></div>
</div>
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
