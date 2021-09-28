<%-- 
    Document   : createNewAccount
    Created on : Jun 30, 2021, 4:28:35 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <link rel="icon" href="UI/Icon/Ficon.png" type="image/icon type">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Carattere&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="UI/CSS/createBlogPageStyle.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="home">
                <img src="UI/Icon/FPTLogo.jpg" alt="FPTLogo">
                FPT Academy
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav ml-auto">
                    <a class="nav-item nav-link" href="aboutUs.html">About us</a>
                    <a class="nav-item nav-link" href="loginPage">Login</a>
                </div>
            </div>
        </nav>
        <div>
            <div class="xavalo">
                <img src="UI/Icon/FPTCampus.jpg" alt="FPTCampus">
                <h1>Blog</h1>
                <h2>Academic Blog for FPT</h2>
            </div>
        </div>
        
        </br><form action="createBlog" method="POST" enctype='multipart/form-data'>
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Title: <br/><input type="text" value="${param.txtTitle}" name="txtTitle" maxlength="60" size="62"/> <br/>
                <font color="red">
                ${requestScope.ERROR_TITLE}
                </font><br/>
            Content:<br/> <textarea value="${param.txtContent}" name="txtContent" rows="10" cols="50">Enter</textarea><br/>
             <font color="red">
                ${requestScope.ERROR_CONTENT}
                </font><br/>
            <br/><input type="file" id="attachment" name="fileAttachment">

            <select name="categoryBox">
                <c:set var="dtoList" value="${requestScope.CATEGORY_LIST}"/>
                <c:forEach var="dto" items="${dtoList}" varStatus="counter">
                        <option value="${dto.categoryID}">${dto.categoryName}</option>
                </c:forEach>

                <input type="submit" value="Post"/>
                <input type="reset" value="Reset" /><br/>
                <a href="home">Return to Home</a>
        </form>
    </body>
</html>
