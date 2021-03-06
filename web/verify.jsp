<%-- 
    Document   : studentProfile
    Created on : Oct 24, 2021, 11:03:26 AM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <!-- this is bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>
    <body>
        <header class="navbar navbar-expand-lg" id="header-default">
            <jsp:directive.include file="navbar.jsp" /> 
        </header>

        <div class="container mt-5">
            <h1 class="">Verify Your Account</h1>
            <c:set var="type" value="${requestScope.TYPE}"/>
            <div class="mb-5">
                <c:if test="${type == 'SUCCESS'}">
                    <div class="alert alert-success">
                        Your account has been verified! You will be redirected to
                        homepage in 3 seconds!<br />
                        <a href="/" class="alert-link">Click here</a> if it's take too
                        long
                    </div>
                    <script>
                        setTimeout(function () {
                            window.location.href = "./"
                        }, 3000);
                    </script>
                </c:if>

                <c:if test="${type == 'INVALID'}">
                    <div class="alert alert-warning">
                        Your link is invalid! Please enter your email below to get a new
                        one!
                    </div>  
                </c:if>

                <c:if test="${type == 'EMAIL_ALREADY_VERIFIED'}">
                    <div class="alert alert-warning">
                        This email is already verified!
                    </div>  
                </c:if>

                <c:if test="${type == 'EMAIL_NOT_EXISTED'}">
                    <div class="alert alert-danger">
                        Email doesn't exist in our system, perhaps you want to <a href="./loginPage" class="alert-link">register</a>?
                    </div>
                </c:if>

                <c:if test="${type == 'EMAIL_COOLDOWN'}">
                    <div class="alert alert-danger">
                        You can get a new verification link after ${requestScope.TIMELEFT}!
                    </div>
                </c:if>

                <c:if test="${type == 'EMAIL_INPUT_MISSING'}">
                    <div class="alert alert-danger">
                        Email is not valid!
                    </div>
                </c:if>
            </div>

            <c:if test="${type != 'SUCCESS'}">
                <div class="bg-light">
                    <form action="verify">
                        <div class="form-group">
                            <label class="mb-1" for="emailInput">Email address</label>
                            <input
                                autocomplete="off"
                                name="email"
                                type="email"
                                class="form-control"
                                id="emailInput"
                                placeholder="name@example.com"
                                value="${param.email}"
                                required
                                />
                        </div>
                        <input type="submit" class="btn btn-warning btn-lg btn-block mt-3" name="action" autocomplete="off" value="Send Verify Link"/>  
                    </form>
                </div>
            </c:if>
        </div>
    </body>
</html>
