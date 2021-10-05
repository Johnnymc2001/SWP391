<%-- 
    Document   : newsearchPage
    Created on : Oct 4, 2021, 5:44:57 PM
    Author     : henry
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <!-- this is bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/NewsearchPageStyle.css">
        <!-- this is script -->
        <script src="UI/script/searchPage.js"></script>
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <!-- this is swiperjs -->
        <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
    </head>

    <body>

        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${empty param.txtSearchValue}">
            <c:set var="param.txtSearchValue" value=""/>
        </c:if>
        <!-- THIS IS NAVBAR -->
        <div class="container-fluid">
            <header id="header-default">
                <div class="row justify-content-end">
                    <div class="site-logo col-lg-8">
                        <a href="home"><img src="UI/Icon/FPTLogo.jpg" alt="">FPT Academy</a>
                    </div>
                    <div class="drop-menu col-lg-2">
                        <c:if test="${not empty user}">
                            <a href="search"><button class="search-button"><i class="fas fa-search"></i></button></a>
                            <button type="button" class="menu-button dropdown-toggle" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                ${user.fullname}
                            </button>
                            <c:if test="${user.role == 'Student'}">
                                <ul class="dropdown-menu dropdown-menu-end">
                                    <div class="menu-wrapper">
                                        <!-- this is user img -->
                                        <div class="avatar">
                                            <img src="UI/Icon/profile-icon.png" alt="avatar">
                                            <p>${user.fullname}</p>
                                        </div>
                                        <!-- personal menu -->
                                        <div class="personal-menu">
                                            <a href="student/dashboard"><li>Profile</li></a>
                                            <a href="createPage"><li>Create Blog</li></a>
                                            <a href="logout"><li>Log out</li></a>
                                        </div>
                                    </c:if>
                                    <c:if test="${user.role == 'Mentor'}">
                                        <ul class="dropdown-menu dropdown-menu-end">
                                            <div class="menu-wrapper">
                                                <!-- this is user img -->
                                                <div class="avatar">
                                                    <img src="UI/Icon/maleteacher-icon.png" alt="avatar">
                                                    <p>${user.fullname}</p>
                                                </div>
                                                <!-- personal menu -->
                                                <div class="personal-menu">
                                                    <a href="mentor/dashboard"><li>Profile</li></a>
                                                    <a href="mentor/blogPendingList"><li>Pending Blog</li></a>
                                                    <a href="logout"><li>Log out</li></a>
                                                </div>
                                            </c:if>
                                            <c:if test="${user.role == 'Admin'}">
                                                <ul class="dropdown-menu dropdown-menu-end">
                                                    <div class="menu-wrapper">
                                                        <!-- this is user img -->
                                                        <div class="avatar">
                                                            <img src="UI/Icon/maleteacher-icon.png" alt="avatar">
                                                            <p>${user.fullname}</p>
                                                        </div>
                                                        <!-- personal menu -->
                                                        <div class="personal-menu">
                                                            <a href="admin/dashboard"><li>Dashboard</li></a>
                                                            <a href="admin/accountList"><li>Manage Accounts</li></a>
                                                            <a href="logout"><li>Log out</li></a>
                                                        </div>
                                                    </c:if>
                                                    <!-- public menu -->
                                                    <div class="public-menu">
                                                        <a href="home"><li>Home</li></a>
                                                        <a href="search"><li>Search</li></a>
                                                        <a href="about"><li>About</li></a>
                                                        <a href="contact"><li>Contact</li></a>
                                                    </div>
                                                </div>
                                            </ul>
                                        </c:if>
                                        <c:if test="${empty user}">
                                            <a href="loginPage">Login</a>
                                        </c:if>
                                    </div>
                            </div>
                            </header>
                    </div>

                    <div id="search-bar" class="search-bar">
                        <div class="box">
                            <form action="search" method="GET" class="search-form">
                                <input name="txtSearchValue" value="${param.txtSearchValue}" type="text" id="search-input" class="search-input"
                                       required="">
                                <label class="search-label" for="search-input">Search</label>
                                <button type="submit"><i class="fas fa-search"></i></button>
                            </form>
                        </div>
                    </div>

                    <!-- Swiper -->
                    <div class="card-slider">
                        <div class="swiper">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <a class="category-card" href="">
                                        <img src="UI/Icon/selfmademan.jpg" alt="">
                                        <p>Category name</p>
                                    </a>
                                </div>
                                <div class="swiper-slide">
                                    <a class="category-card" href="">
                                        <img src="UI/Icon/FPTCampus.jpg" alt="">
                                        <p>Category name</p>
                                    </a>
                                </div>
                                <div class="swiper-slide">
                                    <a class="category-card" href="">
                                        <img src="UI/Icon/hallwayfpt.jpg" alt="">
                                        <p>Category name</p>
                                    </a>
                                </div>
                                <div class="swiper-slide">
                                    <a class="category-card" href="">
                                        <img src="UI/Icon/FPTCampus.jpg" alt="">
                                        <p>Category name</p>
                                    </a>
                                </div>
                                <div class="swiper-slide">
                                    <a class="category-card" href="">
                                        <img src="UI/Icon/selfmademan.jpg" alt="">
                                        <p>Category name</p>
                                    </a>
                                </div>
                                <div class="swiper-slide">
                                    <a class="category-card" href="">
                                        <img src="UI/Icon/selfmademan.jpg" alt="">
                                        <p>Category name</p>
                                    </a>
                                </div>
                            </div>
                            <!-- Add Pagination -->
                            <div class="swiper-pagination"></div>
                        </div>
                    </div>

                    <div id="search-result-container" class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <c:set var="blogs" value="${requestScope.BLOG_LIST}"/>
                                <c:forEach var="dto" items="${blogs}">
                                    <div class="post-box">
                                        <div class="author-inform">
                                            <ul>
                                                <li><a href="">Author name</a></li>
                                                <li>11/10/2001</li>
                                                <li>comment(10)</li>
                                            </ul>
                                        </div>
                                        <h1 class="blog-title"><a href="">${dto.title}</a></h1>
                                        <div class="img-link">
                                            <a href=""><img src="UI/Icon/selfmademan.jpg" alt=""></a>
                                        </div>
                                        <p>${dto.content}
                                        </p>
                                        <div class="blog-detail">
                                            <a href="">link to blog detail</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <nav id="pagination-box" aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <!-- previous button -->
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <!-- end previousr button -->

                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>

                            <!-- next button -->
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                            <!-- end next button -->
                        </ul>
                    </nav>

                    <!-- FOOTER -->
                    <div class="web-footer">
                        <p>2021 Henry. FE by Henry</p>
                        <button onclick="goTop()">Back to top</button>
                    </div>

                    <!-- this is js for swiper -->
                    <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
                    <script type="module">
                        var swiper = new Swiper('.swiper', {
                        slidesPerView: 1,
                        spaceBetween: 10,
                        // init: false,
                        pagination: {
                        el: '.swiper-pagination',
                        clickable: true,
                        },
                        breakpoints: {
                        640: {
                        slidesPerView: 1,
                        spaceBetween: 30,
                        },
                        768: {
                        slidesPerView: 2,
                        spaceBetween: 30,
                        },
                        968: {
                        slidesPerView: 3,
                        spaceBetween: 30,
                        },
                        1024: {
                        slidesPerView: 4,
                        spaceBetween: 30,
                        },
                        }
                        });
                    </script>
                    </body>
                    </html>
