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
        <link rel="stylesheet" href="UI/CSS/searchPageStyle.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
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
        <header class="navbar navbar-expand-lg" id="header-default">
            <jsp:include page="navbar.jsp" />  
        </header>
        <!-- END OF NAVBAR -->

        <div id="search-bar" class="search-bar">
            <div class="box">
                <form action="search" method="GET" class="search-form">
                    <input name="txtSearchValue" value="${param.txtSearchValue}" type="text" id="search-input" class="search-input"
                           >
                    <label class="search-label" for="search-input">Search</label>
                    <button type="submit"><i class="fas fa-search"></i></button>
                </form>
            </div>
        </div>

        <!-- Swiper -->
        <div class="card-slider">
            <div class="swiper">
                <div class="swiper-wrapper">
                    <c:forEach var="dto" items="${requestScope.CAT_LIST}">
                        <div class="swiper-slide">
                            <a class="category-card" href="search?txtSearchCategory=${dto.categoryID}">
                                <img src="UI/Icon/selfmademan.jpg" alt="">
                                <p>${dto.categoryName}</p>
                            </a>
                        </div>   
                    </c:forEach>
                </div>
                <!-- Add Pagination -->
                <div class="swiper-pagination"></div>
            </div>
        </div>

        <div id="search-result-container" class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <c:set var="blogList" value="${requestScope.BLOG_LIST}"/>
                    <c:forEach var="blog" items="${blogList}">
                        <div class="post-box">
                            <div class="author-inform">
                                <ul>
                                    <li><a href="">${blog.getAccount().getFullname()}</a></li>
                                    <li>${blog.postDate}</li>
                                    <li>Comments (${blog.getAllComments().size()})</li>
                                </ul>
                            </div>
                            <h1 class="blog-title"><a href="">${blog.title}</a></h1>
                            <div class="img-link">
                                <a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.thumbnail}" alt=""></a>
                            </div>
                            <div class="blog-detail">
                                <a href="blog?txtBlogID=${blog.blogID}">Go to Post</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <nav id="pagination-box" aria-label="Page navigation">
            <ul class="pagination justify-content-center">
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
