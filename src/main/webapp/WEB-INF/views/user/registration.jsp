<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous">
    </script>
</head>
<body style="font-family: 'Droid Sans Mono Dotted',cursive; font-size: x-large; color: #030392; background: #d69246">
<h3>Hi! Fill in the forms for registration!</h3>
<h3 style="color: red">${message}</h3>
<form method="post" action="${pageContext.request.contextPath}/user/registration">
    <label for="name">Name :</label><br>
    <input id="name" type="text" required="required" name="name" value="${name}"><br>
    <br>
    <label for="login">Login :</label><br>
    <input id="login" type="text" required="required" name="login" value="${login}"><br>
    <br>
    <label for="pass">Password :</label><br>
    <input id="pass" type="password" required="required" name="pwd"><br>
    <br>
    <label for="repPass">Confirm your password :</label><br>
    <input id="repPass" type="password" required="required" name="pwd-confirm"><br>
    <br>
    <button type="submit">Register</button>
</form>
</body>
</html>
