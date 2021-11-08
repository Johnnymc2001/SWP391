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

    <%-- <c:if test="${not empty sessionScope.USER}">
        <c:if test="${sessionScope.USER.role != 'Mentor' || sessionScope.USER.role != 'Student'}">
            <c:redirect url="/"/>
        </c:if>
    </c:if> --%>


    <c:set var="user" value="${sessionScope.USER}"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog List</title>
        <!-- this is bootstrap 5-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/mentorStyle.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        
        <c:if test="${empty sessionScope.USER}">
            <c:redirect url="/"/>
        </c:if>

        <c:if test="${not empty sessionScope.USER}">
                <c:if test="${sessionScope.USER.role != 'Mentor' && sessionScope.USER.role != 'Student'}">
                <c:redirect url="/"/>
            </c:if>
        </c:if>
        
    </head>
    <body>
        <c:set var="blogList" value="${requestScope.BLOG_LIST}"/>
        <header class="navbar navbar-expand-lg" id="header-default">
            <jsp:directive.include file="navbar.jsp" /> 
        </header>
        <!-- END OF NAVBAR -->

        <div class="container-fluid">
            <div class="row">
                <div class="d-none d-md-block col-md-3">
                    <ul class="option-table list-group">
                        <div class="user-avatar">
                            <h2>Blog List</h2>
                        </div>
                        <div class="user-option">
                            <a href="profile">
                                <li class="list-group-item">Profile</li>
                            </a>
                            <a href="blogList">
                                <li class="list-group-item active">Your Blogs</li>
                            </a>
                            <c:if test="${user.role == 'Mentor'}">
                                <a href="blogPendingList">
                                    <li class="list-group-item">Pending Blog</li>
                                </a>
                            </c:if>
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
                        <h1> Blogs List</h1>
                        ${requestScope.MESSAGE}
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>No. </th>
                                    <th>Title</th>
                                    <th>Status</th>
                                    <th>Aciton</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${blogList}" varStatus="count">
                                    <tr>
                                        <td>${count.count}</td>
                                        <th>${dto.title}</th>
                                        <td>
                                            <c:if test="${dto.status == 'APPROVED'}">
                                                <font style="color: green">
                                                ${dto.status}
                                                </font>
                                            </c:if>
                                            <c:if test="${dto.status == 'DISAPPROVED'}">
                                                <font style="color: red">
                                                ${dto.status}
                                                </font>
                                            </c:if>
                                            <c:if test="${dto.status == 'PENDING'}">
                                                <font style="color: orange">
                                                ${dto.status}
                                                </font>
                                            </c:if>
                                        </td>
                                        <td>
                                            <button class="btn-action"><a href="blog?txtBlogID=${dto.blogID}">View</a></button>
                                            <c:if test="${dto.status} == 'APPROVED' || ${dto.status} == 'DISAPPROVED'">
                                                <button class="btn-action"><a href="edit?txtBlogID=${dto.blogID}">Edit</a></button>
                                            </c:if>
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
                        <h1>There is no blog!</h1>
                    </div>
                </c:if>
            </div>
        </div>

        <script type="text/javascript" src="UI/script/blogList.js"></script>

    </body>
</html>
