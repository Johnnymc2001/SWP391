<%-- 
    Document   : home
    Created on : Oct 2, 2021, 5:08:48 PM
    Author     : henry
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <!-- this is bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/homePageStyle.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <!-- this is script -->
        <script src="UI/script/homePage.js"></script>
        <!-- this is swiperjs -->
        <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
    </head>
    <body>

        <c:set var="user" value="${sessionScope.USER}"/>
        <!-- THIS IS NAVBAR -->
        <header class="navbar navbar-expand-lg" id="header-default">
            <div class="header-left">
                <!-- site logo -->
                <div class="site-logo">
                    <a href="home"><img src="UI/Icon/FPTLogo.jpg" alt="logo"></a>
                    <a href="home">FPT Academy</a>
                </div>
                <!-- navigate options -->
                <div class="collapse navbar-collapse options-btn">
                    <a href="home"><button class="btn active-btn">Home</button></a>
                    <a href="search?txtSearchType=popular"><button class="btn">Popular</button></a>
                    <a href="search?txtSearchType=recent"><button class="btn">Recent</button></a>
                    <a href="aboutUs.html"><button class="btn">About</button></a>
                </div>
            </div>
            <div class="header-right">
                <div class="search-btn">
                    <form action="search" method="POST">
                        <input type="hidden" name="txtSearchType" value="recent">
                        <button type="submit" class="search-button"><i class="fas fa-search"></i></button>
                    </form>
                </div>
                <c:if test="${not empty user}">
                    <button type="button" class="menu-button dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        ${user.fullname}
                    </button>
                    <c:if test="${user.role == 'Student'}">
                        <ul class="dropdown-menu dropdown-menu-end">
                            <!-- this is user img -->
                            <div class="avatar">
                                <img src="UI/Icon/profile-icon.png" alt="avatar">
                                <p>${user.fullname}</p>
                            </div>
                            <!-- personal menu -->
                            <div class="personal-menu">
                                <a href="studentDashboard"><li>Profile</li></a>
                                <a href="create"><li>Create Blog</li></a>
                                <a href="logout"><li>Log out</li></a>
                            </div>
                        </c:if>
                        <c:if test="${user.role == 'Mentor'}">
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
                            </c:if>
                            <c:if test="${user.role == 'Admin'}">
                                <ul class="dropdown-menu dropdown-menu-end">
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
                        </c:if>
                        <c:if test="${empty user}">
                            <a href="loginPage">Login</a>
                        </c:if>
                        </div>
                        </header>
                        <!-- END OF NAVBAR -->

                        <!-- THIS IS BODY -->
                        <!-- THIS IS BODY CONTAINER 1 -->
                        <div id="body-container-1" class="container">
                            <div class="row">
                                <div class="col-lg-8">
                                    <div class="landing-blog">
                                        <a href=""><img src="UI/Icon/selfmademan.jpg" alt="landing-blog"></a>
                                        <div class="landing-content">
                                            <h2>
                                                <a href="">This is blog's title to go to blog detail</a>
                                            </h2>
                                            <p>
                                                <a href="">Author name</a>
                                                <span>11/10/2001</span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div id="news" class="news popular-activated">
                                        <div class="button-box">
                                            <button id="popular-button" class="popular-button" onclick="popular()">Popular</button>
                                            <button id="recent-button" class="recent-button" onclick="recent()">Recent</button>
                                        </div>
                                        <div class="popular-box">
                                            <c:forEach var="blog" items="${requestScope.MOST_AWARD_AND_RATE}">
                                                <div class="box-content">
                                                    <div class="content-img">
                                                        <a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.getFirstImage()}" alt=""></a>
                                                    </div>
                                                    <div class="content-text">
                                                        <h6><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></h6>
                                                        <p>${blog.postDate}</p>
                                                    </div>
                                                </div>
                                            </c:forEach>

                                        </div>
                                        <div class="recent-box">
                                            <c:forEach var="blog" items="${requestScope.MOST_RECENT}">
                                                <div class="box-content">
                                                    <div class="content-img">
                                                        <a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.getFirstImage()}" alt=""></a>
                                                    </div>
                                                    <div class="content-text">
                                                        <h6><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></h6>
                                                        <p>${blog.postDate}</p>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- THIS IS BODY CONTAINER 2 -->
                        <div id="body-container-2" class="container-fluid">
                            <div class="row">
                                <div class="col-md-6 col-lg-4">
                                    <h1>Mentor's Pick</h1>
                                    <c:set var="blogMR_FIRST" value="${requestScope.MOST_AWARD_FIRST}"/>

                                    <div class="content-left">
                                        <div class="content-img">
                                            <a href="blog?txtBlogID=${blogMR_FIRST.blogID}"><img src="${blogMR_FIRST.getFirstImage()}" alt=""></a>
                                        </div>
                                        <div class="content-text">
                                            <p>
                                                <a href="">${blogMR_FIRST.getAccount().getFullname()}</a>
                                                <span>${blogMR_FIRST.postDate}</span>
                                            </p>
                                            <h5>
                                                <a href="blog?txtBlogID=${blogMR_FIRST.blogID}">${blogMR_FIRST.title}</a>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-4">
                                    <div class="content-right">
                                        <c:forEach var="blog" items="${requestScope.MOST_AWARD_AND_RATE}">
                                            <div class="box-content">
                                                <div class="content-img">
                                                    <a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.getFirstImage()}" alt=""></a>
                                                </div>
                                                <div class="content-text">
                                                    <h6><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></h6>
                                                    <p>${blog.postDate}</p>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="right-bd-2 d-none d-lg-block d-xl-blog col-lg-4">
                                    <div class="introduce">
                                        <div class="title">
                                            <img src="UI/Icon/FPTLogo.jpg" alt="">
                                            <h3>FPT Academy</h3>
                                        </div>
                                        <p>Hello, We’re content writer who is fascinated by content academy. We help students access the
                                            articles with a wide range of knowledge.</p>
                                    </div>
                                    <div class="reward-blog">
                                        <h3>Top Reward</h3>
                                        <c:forEach var="blog" items="${requestScope.MOST_AWARD_AND_RATE}">
                                            <div class="box-content">
                                                <div class="content-img">
                                                    <a href=""><img src="${blog.getFirstImage()}" alt=""></a>
                                                </div>
                                                <div class="content-text">
                                                    <h6><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></h6>
                                                    <p>${blog.postDate}</p>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- THIS IS BODY CONTAINER 3 -->
                        <div id="body-container-3" class="container-fluid">
                            <div class="row">
                                <div class="left-blog col-lg-9">
                                    <h1>Trending</h1>
                                    <div class="upper-content">
                                        <div class="row">
                                            <c:set var="blogMC_FIRST" value="${requestScope.MOST_COMMENT_FIRST}"/>
                                            <div class="box-content col-md-6">
                                                <div class="content-img">
                                                    <a href="blog?txtBlogID=${blogMC_FIRST.blogID}"><img src="${blogMC_FIRST.getFirstImage()}" alt=""></a>
                                                </div>
                                                <div class="content-text">
                                                    <p>
                                                        <a href="">${blogMC_FIRST.getAccount().getFullname()}</a>
                                                        <span>${blogMC_FIRST.postDate}</span>
                                                    </p>
                                                    <h5>
                                                        <a href="blog?txtBlogID=${blogMC_FIRST.blogID}">${blogMC_FIRST.title}</a>
                                                    </h5>
                                                </div>
                                            </div>
                                            <c:set var="blogMC_SECOND" value="${requestScope.MOST_COMMENT_SECOND}"/>

                                            <div class="box-content col-md-6">
                                                <div class="content-img">
                                                    <a href="blog?txtBlogID=${blogMC_SECOND.blogID}"><img src="${blogMC_SECOND.getFirstImage()}" alt=""></a>
                                                </div>
                                                <div class="content-text">
                                                    <p>
                                                        <a href="">${blogMC_SECOND.getAccount().getFullname()}</a>
                                                        <span>${blogMC_SECOND.postDate}</span>
                                                    </p>
                                                    <h5>
                                                        <a href="blog?txtBlogID=${blogMC_SECOND.blogID}">${blogMC_SECOND.title}</a>
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="lower-content">
                                        <div class="row">
                                            <c:forEach var="blog" items="${requestScope.MOST_COMMENT_ROW_1}">
                                                <div class="box-content col-lg-6">
                                                    <div class="content-img">
                                                        <a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.getFirstImage()}" alt=""></a>
                                                    </div>
                                                    <div class="content-text">
                                                        <h6><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></h6>
                                                        <p>${blog.postDate}</p>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="row">
                                            <c:forEach var="blog" items="${requestScope.MOST_COMMENT_ROW_2}">
                                                <div class="box-content col-lg-6">
                                                    <div class="content-img">
                                                        <a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.getFirstImage()}" alt=""></a>
                                                    </div>
                                                    <div class="content-text">
                                                        <h6><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></h6>
                                                        <p>${blog.postDate}</p>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="category-table col-lg-3">
                                    <div class="explore-topics">
                                        <h3>Explore Topic</h3>
                                        <div class="topic-list">
                                            <ul>
                                                <li>
                                                    <a href="">Topic 1</a>
                                                    <a href="">(10)</a>
                                                </li>
                                                <li>
                                                    <a href="">Topic 1</a>
                                                    <a href="">(10)</a>
                                                </li>
                                                <li>
                                                    <a href="">Topic 1</a>
                                                    <a href="">(10)</a>
                                                </li>
                                                <li>
                                                    <a href="">Topic 1</a>
                                                    <a href="">(10)</a>
                                                </li>
                                                <li>
                                                    <a href="">Topic 1</a>
                                                    <a href="">(10)</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="register-invitation">
                                        <h3>Join us now</h3>
                                        <h6>Be one of us to reaches the world's knowledge</h6>
                                        <a href=""><button>Register to join</button></a>
                                        <p><a href="">Already have an account ?</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- THIS IS BODY CONTAINER 4 -->
                        <div id="body-container-4" class="container-fluid">
                            <h1>Everyone love</h1>
                            <div class="row">
                                <div class="col-lg-8">
                                    <div class="content-wrap">
                                        <div class="swiper swiper-list">
                                            <div class="swiper-wrapper">
                                                <c:forEach var="blog" items="${requestScope.MOST_COMMENT}">
                                                    <div class="swiper-slide">
                                                        <div class="box-content">
                                                            <div class="content-img">
                                                                <a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.getFirstImage()}" alt=""></a>
                                                            </div>
                                                            <div class="content-text">
                                                                <h3><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></h3>
                                                                <p>${blog.postDate}</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>                         
                                            </div>
                                            <!-- Add Pagination -->
                                            <div class="swiper-pagination"></div>
                                            <!-- Add Arrows -->
                                            <div class="swiper-button-next d-none d-md-block"></div>
                                            <div class="swiper-button-prev d-none d-md-block"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="tag-table">
                                        <h3>Interesting Tags</h3>
                                        <div class="swiper swiper-tag">
                                            <div class="swiper-wrapper">
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 1</button></div>
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 2</button></div>
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 3</button></div>
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 4</button></div>
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 5</button></div>
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 6</button></div>
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 7</button></div>
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 8</button></div>
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 9</button></div>
                                                <div class="swiper-slide"><button class="tag-btn" name="tag-btn">#Tag 10</button></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- FOOTER -->
                            <div class="web-footer">
                                <p>2021 Henry. FE by Henry</p>
                                <button onclick="goTop()">Back to top</button>
                            </div>

                            <!-- THIS IS JS FOR CAROUSEL -->
                            <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
                            <script type="module">
                                var swiperCard = new Swiper('.swiper-tag', {
                                loop: true,
                                effect: 'cards',
                                grabCursor: true,
                                autoplay: {
                                delay: 2500,
                                disableOnInteraction: false,
                                },
                                });
                            </script>
                            <script type="module">
                                var swiperList = new Swiper('.swiper-list', {
                                slidesPerView: 1,
                                loop: true,
                                pagination: {
                                el: '.swiper-pagination',
                                dynamicBullets: true,
                                },
                                navigation: {
                                nextEl: '.swiper-button-next',
                                prevEl: '.swiper-button-prev',
                                },
                                });
                            </script>


                            </body>
                            </html>
