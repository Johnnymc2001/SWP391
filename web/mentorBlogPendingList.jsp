<%-- 
    Document   : mentorBlogPendingList
    Created on : Sep 27, 2021, 9:31:52 PM
    Author     : JohnnyMC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:if test="${empty sessionScope.USER}">
        <c:redirect url="/"/>
    </c:if>

    <c:if test="${not empty sessionScope.USER}">
        <c:if test="${sessionScope.USER.role != 'Mentor'}">
            <c:redirect url="/"/>
        </c:if>
    </c:if>
    <c:set var="user" value="${sessionScope.USER}"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mentor BlogPendingList</title>
        <!-- this is bootstrap 5-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/mentorStyle.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>
    <body>
        <c:set var="blogList" value="${requestScope.PENDING_BLOG_LIST}"/>
        <header class="navbar navbar-expand-lg" id="header-default">
            <div class="header-left">
                <!-- site logo -->
                <div class="site-logo">
                    <a href="home"><img src="UI/Icon/FPTLogo.jpg" alt="logo"></a>
                    <a href="home">FPT Academy</a>
                </div>
                <!-- navigate options -->
                <div class="collapse navbar-collapse options-btn">
                    <a href="home"><button class="active-btn">Home</button></a>
                    <a href="search?txtSearchType=popular"><button class="">Popular</button></a>
                    <a href="search?txtSearchType=recent"><button class="">Recent</button></a>
                    <a href="aboutUs.html"><button class="">About</button></a>
                </div>
            </div>
            <div class="header-right">
                <div class="search-btn">
                    <form action="search" method="POST">
                        <input type="hidden" name="txtSearchValue">
                        <button type="submit" class="search-button"><i class="fas fa-search"></i></button>
                    </form>
                </div>
                <button type="button" class="menu-button dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    ${user.fullname}
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <!-- this is user img -->
                    <div class="avatar">
                        <img src="UI/Icon/maleteacher-icon.png" alt="avatar">
                        <p>${user.fullname}</p>
                    </div>
                    <!-- personal menu -->
                    <div class="personal-menu">
                        <a href="mentorDashboard"><li>Profile</li></a>
                        <a href="blogPendingList"><li>Pending Blog</li></a>
                        <a href="logout"><li>Log out</li></a>
                    </div>
                    <!-- public menu -->
                    <div class="public-menu">
                        <a href="home"><li>Home</li></a>
                        <a href="search"><li>Search</li></a>
                        <a href="about"><li>About</li></a>
                        <a href="contact"><li>Contact</li></a>
                    </div>
                </ul>
                <div class="notify">
                    <button type="button" class="notify-button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-bell"></i>
                        <span class="num">1</span>
                    </button>
                    <ul class="notify-box dropdown-menu dropdown-menu-end">
                        <iframe class="notify-window" src="notification" frameborder="0"></iframe>
                    </ul>
                </div>
            </div>
        </header>
        <!-- END OF NAVBAR -->

        <div class="container-fluid">
            <div class="row">
                <div class="d-none d-md-block col-md-3">
                    <ul class="option-table list-group">
                        <div class="user-avatar">
                            <h2>Mentor</h2>
                        </div>
                        <div class="user-option">
                            <a href="blogPendingList">
                                <li class="list-group-item active">Pending Blogs</li>
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
                <c:if test="${not empty blogList}">
                    <div class="user-area col-md-8">
                        <h1>Pending Blogs List</h1>
                        ${requestScope.MESSAGE}
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Student ID</th>
                                    <th>Aciton</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${blogList}">
                                    <tr>
                                        <th>${dto.title}</th>
                                        <td>${dto.studentID}</td>
                                        <td>
                                            <button class="btn-action"><a href="blogPendingDetail?blogid=${dto.blogID}">Modify</a></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${requestScope.PAGE_COUNT}">
                                    <c:if test="${i == param.page || ( empty param.page && i == 1)}">
                                        <li class="page-item active">
                                            <a class="page-link" href="#" onclick="changePage(${i})">
                                                ${i}
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:if test="${i != param.page && (not empty param.page || i != 1)}">
                                        <li class="page-item">
                                            <a class="page-link"href="#" onclick="changePage(${i})">
                                                ${i}
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </c:if>
                <c:if test="${empty blogList}">
                    <div class="col-md-8">
                        <h1>There is no pending blog!</h1>
                    </div>
                </c:if>
            </div>
        </div>

        <script type="text/javascript" src="UI/script/mentorBlogPendingList.js"></script>

    </body>
</html>
