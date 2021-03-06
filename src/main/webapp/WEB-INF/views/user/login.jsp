<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<style>
    body {
        margin-top: 20px;
        margin-left: 50px;
    }
</style>
<body style="font-family: Bahnschrift,cursive; font-size: large; background-image: linear-gradient(#4a4a4a, #000000); color: #f68f13;">
<h1>Login page</h1>
<h3 style="color: red">${errorMsg}</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="login">Login :</label><br>
    <input id="login" type="text" required="required" name="login"><br>
    <br>
    <label for="pass">Password :</label><br>
    <input id="pass" type="password" required="required" name="pwd"><br>
    <br>
    <button class="btn btn-success" type="submit">Login</button>
</form>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
<h3>If you do not have an account <a href="${pageContext.request.contextPath}/user/registration">Registration</a> </h3>
</body>
</html>
