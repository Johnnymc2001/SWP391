<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<html>

<head>
    <title>Register</title>
    <link rel="icon" href="UI/Icon/Ficon.png" type="image/icon type">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="UI/CSS/registerPageStyle.css">
</head>

<body>
      <c:set var="error" value="${requestScope.ERROR}"/>
      
    

    <div class="bckimg">
        <img src="UI/Icon/selfmademan.jpg" alt="">
    </div>

    <div class="login_table">
        <form class="loginform" name="login" action="register" method="POST">
            <h1>Register</h1>
            <div class="enter-field">
                <input type="text" id="username" placeholder="Username">
                <c:if test="${not empty error.userNameLengthError}">
                    <br/>
                <font color="red">
                ${error.userNameLengthError}<br/>
                </font>
            </c:if>
            </div>
            <div class="enter-field">
                <input type="text" id="fullname" placeholder="Fullname">
                 <c:if test="${not empty error.fullNameLengthError}">
<br/>
                <font color="red">
                ${error.fullNameLengthError}<br/>
                </font>
            </c:if>
            </div>
            <div class="enter-field">
                <input type="text" id="address" placeholder="Address">
            </div>
            <div class="enter-field">
                <input type="text" id="phone" placeholder="Phone number">
            </div>
            <div class="enter-field">
                <input type="text" id="address" placeholder="Address">
            </div>
            <div class="enter-field">
                <input type="date" id="birthdate">
            </div>
            <div class="enter-field">
                <input type="password" id="password" placeholder="Password">
                
                  <c:if test="${not empty error.passwordLengthError}">
<br/>
                <font color="red">
                ${error.passwordLengthError}<br/>
                </font>
            </c:if>
                
            </div>
            <div class="enter-field">
                <input type="password" id="confirm_password" placeholder="Confirm password">
                
                  <c:if test="${not empty error.confirmNotMatched}">
<br/>
                <font color="red">
                ${error.confirmNotMatched}<br/>
                </font>
            </c:if>
                
            </div>
            <div class="button">
                <button type="submit" class="btn btn-outline-info">Register</button>
            </div>
            <h6><a href="login.html">Already have an account ?</a></h6>
        </form>
    </div>



    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
    </script>
</body>

</html>