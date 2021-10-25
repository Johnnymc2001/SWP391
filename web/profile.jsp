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
        <link rel="stylesheet" href="UI/CSS/profilePageStyle.css">
        <link rel="stylesheet" href="UI/CSS/adminIframe.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>
    <body>

        <c:set var="user" value="${sessionScope.USER}"/>
        <c:set var="account" value="${requestScope.ACCOUNT}"/>

        <!-- THIS IS NAVBAR -->
        <header class="navbar navbar-expand-lg" id="header-default">
            <jsp:directive.include file="navbar.jsp" /> 
        </header>
        <!-- END OF NAVBAR -->

        <c:if test="${ empty account }">
            <h1>account empty</h1>
        </c:if>
        <div class="container-fluid">
            <div class="row">
                <div class="d-none d-md-block col-md-3">
                    <ul class="option-table list-group">
                        <div class="user-avatar">
                            <h2>Student</h2>
                        </div>
                        <div class="user-option">
                            <a href="blogPendingList">
                                <li class="list-group-item active">Profile</li>
                            </a>
                        </div>
                        <div class="public-option">
                            <a href="home">
                                <li>Home</li>
                            </a>
                            <a href="search">
                                <li>Search</li>
                            </a>
                            <a href="">
                                <li>Contact</li>
                            </a>
                        </div>
                        <a href="logout">
                            <li>Log out</li>
                        </a>
                    </ul>
                </div>
                <div class="d-sm-none d-md-block d-lg-none col-md-1">

                </div>
                <div class="col-md-8 profile-container">
                    <c:if test="${user.role == 'Student'}">
                        <jsp:directive.include file="studentProfile.jsp" /> 
                    </c:if>
                </div>
            </div>
        </div>



    </body>
</html>
