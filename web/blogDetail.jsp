<%-- 
    Document   : homePage
    Created on : Sep 21, 2021, 1:16:50 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Detail</title>
    <!-- this is bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
    </script>
    <!-- this is external css -->
    <link rel="stylesheet" href="UI/CSS/blogDetailPage.css">
    <!-- this is fontawsome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    <!-- this is external JS -->
    <script src="UI/script/blogDetail.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="UI/script/parallax.min.js"></script>
</head>

<c:set var="user" value="${sessionScope.USER}"/>

<body>

    <c:set var="author" value="${sessionScope.AUTHOR}"/>
    <c:set var="blog" value="${requestScope.BLOG}"/>
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
                    <!-- THIS IS AUTHOR AREA -->
                    <div class="container-fluid" data-parallax="scroll" data-image-src="UI/Icon/FPTCampus_2.jpg">
                        <div class="author-info-div row">
                            <div class="author-mobile d-flex col-md-12 col-lg-7">
                                <div class="text-introduce" data-tilt>
                                    <h4>Welcome</h4>
                                    <h1>${author.getFullname()}</h1>
                                    <div class="reward">
                                        <i class="fas fa-trophy fa-lg">
                                            <p>3</p>
                                        </i>
                                        <i class="middle-icon fas fa-pen fa-lg">
                                            <p>5</p>
                                        </i>
                                        <i class="fas fa-thumbs-up fa-lg">
                                            <p>7</p>
                                        </i>
                                    </div>
                                </div>
                            </div>
                            <div class="author-img-div d-none d-lg-flex col-md-5">
                                <div class="tilt-element" data-tilt data-tilt-glare data-tilt-max-glare="0.8">
                                    <img src="UI/Icon/Khoatd.png" alt="">
                                    <div class="tilt-text">
                                        <h3>${author.getFullname()}</h3>
                                        <i class="fas fa-trophy">
                                            <p>3</p>
                                        </i>
                                        <i class="middle-icon fas fa-pen">
                                            <p>5</p>
                                        </i>
                                        <i class="fas fa-thumbs-up">
                                            <p>7</p>
                                        </i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="blog-detail container-fluid">
                        <div class="row justify-content-center">
                            <div class="col-sm-8">
                                <div class="blog-title">
                                    <h1>${blog.title}</h1>
                                    <div class="blog-note">
                                        <ul>
                                            <li><a href="">${author.getFullname()}</a></li>
                                            <li>${blog.title}</li>
                                            <li>Comments (${blog.getAllComments().size()})</li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="blog-content">
                                    <img class="blog-img" src="UI/Icon/selfmademan.jpg" alt="">
                                    <p class="content-text">
                                      ${blog.content}
                                    </p>
                                    <div class="vote">
                                        <button class="vote-btn"><i class="fas fa-star"> 123</i></button>
                                        <button class="vote-btn"><i class="fas fa-trophy"> 3</i></button>
                                    </div>
                                </div>
                                <div class="vote-option">
                                    <button class="option-btn"><i class="fas fa-star">5</i></button>
                                    <button class="option-btn"><i class="fas fa-star">4</i></button>
                                    <button class="option-btn"><i class="fas fa-star">3</i></button>
                                    <button class="option-btn"><i class="fas fa-star">2</i></button>
                                    <button class="option-btn"><i class="fas fa-star">1</i></button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- THIS IS COMMENT SECTION -->
                    <div id="comment-section" class="comment-section container">
                        <h2>Comment</h2>
                        <div id="comment-post" class="comment-post">
                            <iframe id="comment-window" class="comment-window" src="commentPage" frameborder="0"></iframe>
                        </div>
                    </div>

                    <!-- FOOTER -->
                    <div class="web-footer">
                        <p>2021 Henry. FE by Henry</p>
                        <button onclick="goTop()">Back to top</button>
                    </div>

                    <!-- This is JS -->
                    <script type="text/javascript" src="UI/script/vanilla-tilt.js"></script>

                    
                   
    </body>

</html>
