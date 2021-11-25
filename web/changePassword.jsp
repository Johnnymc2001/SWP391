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
            <h1 class="">Change Your Password</h1>
            <c:set var="type" value="${requestScope.TYPE}"/>
            <div class="mb-5">
                <c:if test="${type == 'SUCCESS'}">
                    <div class="alert alert-success">
                        Your password has been changed, you will be redirect to home page in 3 seconds!
                        <script>
                            setTimeout(function () {
                                window.location.href = "./"
                            }, 3000);
                        </script>
                    </div>
                </c:if>

                <c:if test="${type == 'PASSWORD_INVALID'}">
                    <div class="alert alert-danger">
                        Password must be from 8 to 20 characters!
                    </div>
                </c:if>

                <c:if test="${type == 'PASSWORD_NOT_MATCH'}">
                    <div class="alert alert-danger">
                        Password and confirm password is not match!
                    </div>
                </c:if>

                <c:if test="${type == 'CODE_INVALID'}">
                    <div class="alert alert-danger">
                        Your link is not valid! Please get a new link!
                    </div>
                </c:if>

            </div>

            <c:if test="${type != 'SUCCESS' && type != 'CODE_INVALID'}">
                <div class="bg-light">
                    <form action="forgotPassword" method="POST">
                        <div class="form-group">
                            <label class="mb-1" for="emailInput">Verify Code</label>
                            <input
                                autocomplete="off"
                                name="code"
                                type="text"
                                class="form-control"
                                id="emailInput"
                                value="${param.code}"
                                readonly
                                required
                                />
                        </div>
                        <div class="form-group">
                            <label class="mb-1" for="emailInput">Password</label>
                            <input
                                autocomplete="off"
                                name="password"
                                type="password"
                                class="form-control"
                                id="passwordInput"
                                title="Password must be 8 to 20 characters!"
                                pattern=".{8,20}"
                                required
                                />
                        </div>
                        <div class="form-group">
                            <label class="mb-1" for="emailInput">Confirm Password</label>
                            <input
                                autocomplete="off"
                                name="confirmPassword"
                                type="password"
                                class="form-control"
                                id="passwordInput"
                                pattern=".{8,20}"
                                title="Password must be 8 to 20 characters!"
                                required
                                />
                        </div>
                        <input autocomplete="off" type="submit" class="btn btn-warning btn-lg btn-block mt-3" name="action" value="Change Password"/>  
                    </form>
                </div>
            </c:if>
        </div>
    </body>
</html>
