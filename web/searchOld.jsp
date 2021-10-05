<%-- 
    Document   : searchPage
    Created on : Sep 22, 2021, 10:02:28 AM
    Author     : henry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Search Page</title>
        <!-- this is title icon -->
        <link rel="icon" href="UI/Icon/Ficon.png" type="image/icon type">
        <!-- this is bootstrap 4 -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <!-- this is external css file -->
        <link rel="stylesheet" href="UI/CSS/searchPageStyle.css">
        <!-- this is fontawsome icon -->
        <script src="https://kit.fontawesome.com/93823b4ff2.js" crossorigin="anonymous"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>

        <c:set var="user" value="${sessionScope.USER}"/>

        <c:if test="${empty param.txtSearchValue}">
            <c:set var="param.txtSearchValue" value=""/>
        </c:if>
        
        <c:set var="catList" value="${requestScope.CAT_LIST}"/>
        <c:set var="blogList" value="${requestScope.BLOG_LIST}"/>
        

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
                    <!--About us page-->
                    <a class="nav-item nav-link" href="aboutUs.html">About us</a>
                    <!-- If user aren't login then show the login link -->
                    <c:if test="${empty user}">
                        <a class="nav-item nav-link" href="loginPage">Login</a>
                    </c:if>
                    <!-- If user already login then show user link which navigate to user profile page on click -->
                    <c:if test="${not empty user}">
                        <div class="action">
                            <div class="profile-avatar">
                                <img src="UI/Icon/placeholder-avatar.png" alt="avatar">
                            </div>
                            <div class="menu">
                                <h3>${user.fullname}</h3>
                                <span>${user.role}</span>
                                <ul>
                                    <li><img src="UI/Icon/profile-icon.png" alt=""><a href="">Profile</a></li>
                                    <li><img src="UI/Icon/createblog-icon.png" alt=""><a href="">Create blog</a></li>
                                    <li><img src="UI/Icon/logout-icon.png" alt=""><a href="">Log out</a></li>
                                </ul>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </nav>

        <div class="upper-img">
            <img src="UI/Icon/hallwayfpt.jpg" alt="">
        </div>

        <div class="search-box col-md-5">
            <form action="search" method="POST">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect01">Category</label>
                    </div>
                    <select class="custom-select" id="inputGroupSelect01" name="txtSearchCategory">
                        <option>All</option>
                        <c:forEach var="cat" items="${catList}">
                            <c:if test="${cat.categoryID == param.txtSearchCategory}">
                                <option value="${cat.categoryID}" selected>${cat.categoryName}</option>
                            </c:if>
                            <c:if test="${cat.categoryID != param.txtSearchCategory}">
                                <option value="${cat.categoryID}">${cat.categoryName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-group mb-3">
                    <input type="text" placeholder="Search..." class="form-control"
                           aria-label="Search input with dropdown button" id="search-title" value="${param.txtSearchValue}" name="txtSearchValue">
                    <div class="input-group-append">
                        <button class="btn btn-info" type="submit">Search</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="header-bar"></div>

        <div class="container-fluid">           
            <c:if test="${not empty blogList}">
                <table>
                    <tr>
                        <td>
                            ID
                        </td>
                        <td>
                            Title
                        </td>
                        <td>
                            Content
                        </td>
                    </tr>
                    <c:forEach var="dto" items="${blogList}"  varStatus="count">
                        <tr>
                            <td>
                                ${dto.blogID}
                            </td>
                            <td>
                                ${dto.title}
                            </td>
                            <td>
                                ${dto.content}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty blogList}">List is Empty!</c:if>

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
