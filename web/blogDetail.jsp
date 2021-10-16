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

    <c:set var="author" value="${requestScope.AUTHOR}"/>
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

                    <div class="parallax-window" data-parallax="scroll" data-image-src="UI/Icon/FPTCampus_2.jpg">
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 col-lg-8">
                                <div class="blog-title">
                                    <h1>${blog.title}</h1>
                                    <div class="blog-note">
                                        <ul>
                                            <li><a href="">${author.getFullname()}</a></li>
                                            <li>11/10/2001</li>
                                            <li>comment(${blog.getAllComments().size()})</li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="blog-content">
                                    <img class="blog-img" src="UI/Icon/selfmademan.jpg" alt="">
                                    <p class="blog-text">
                                        ${blog.content}
                                    </p>
                                    <div class="blog-vote">
                                        <div>
                                            <i class="fas fa-star fa-lg"></i>
                                            <span>13</span>
                                        </div>
                                        <div>
                                            <i class="fas fa-trophy fa-lg"></i>
                                            <span>5</span>
                                        </div>
                                    </div>
                                    
                                    <%-- ----------- HENRY SEND name="txtRate" value="Số sao được chọn hoặc null" ---------------------------   --%>
                                    <form action="rate" method="POST">
                                        <div class="user-vote">
                                            <div class="btn-group dropend">
                                                <button type="submit" class="btn dropdown-toggle" data-bs-toggle="dropdown"
                                                        aria-expanded="false">
                                                    Vote
                                                </button>
                                                <ul class="vote-menu dropdown-menu">
                                                    <li>
                                                        <i class="fas fa-star fa-lg"></i>
                                                        <span>5</span>
                                                    </li>
                                                    <li>
                                                        <i class="fas fa-star fa-lg"></i>
                                                        <span>4</span>
                                                    </li>
                                                    <li>
                                                        <i class="fas fa-star fa-lg"></i>
                                                        <span>3</span>
                                                    </li>
                                                    <li>
                                                        <i class="fas fa-star fa-lg"></i>
                                                        <span>2</span>
                                                    </li>
                                                    <li>
                                                        <i class="fas fa-star fa-lg"></i>
                                                        <span>1</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </div>
                            <div class="right-colum col-lg-4">
                                <div class="row justify-content-center">
                                    <div class="author-introduce col-md-6 col-lg-11">
                                        <h4>Author of this article</h4>
                                        <h2>${author.getFullname()}</h2>
                                        <div class="reward">
                                            <i class="fas fa-trophy fa-lg">
                                                <p>3</p>
                                            </i>
                                            <i class="fas fa-pen fa-lg">
                                                <p>5</p>
                                            </i>
                                            <i class="fas fa-star fa-lg">
                                                <p>7</p>
                                            </i>
                                        </div>
                                    </div>
                                    <div class="web-introduce col-md-6 col-lg-11">
                                        <div class="logo-title">
                                            <img src="UI/Icon/FPTLogo.jpg" alt="">
                                            <h3>FPT Academy</h3>
                                        </div>
                                        <p>Hello, We’re content writer who is fascinated by content academy. We help students access the
                                            articles with a wide range of knowledge.</p>
                                    </div>
                                </div>
                                <div class="explore-topics">
                                    <h3>Explore Topic</h3>
                                    <div class="topic-list">
                                        <ul>
                                            <c:forEach var="dto" items="${requestScope.CAT_LIST}">
                                                <li>
                                                    <a href="">${dto.categoryName}</a>
                                                    <a href="">(10)</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div class="register-invitation">
                                    <h3>Join us now</h3>
                                    <h6>Be one of us to reaches the world's knowledge</h6>
                                    <a href="registerPage"><button>Register to join</button></a>
                                    <p><a href="loginPage">Already have an account ?</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END OF BLOG CONTENT -->

                    <form action="edit" method="POST" >
                        <input type="hidden" name="txtBlogID" value="${blog.blogID}">
                        <input type="submit" name="btAction"   value="Edit">
                    </form>


                    <!-- THIS IS COMMENT SECTION -->
                    <div id="comment-section" class="comment-section container">
                        <h2>Comment</h2>
                        <div id="comment-post" class="comment-post">
                            <iframe id="comment-window" class="comment-window" src="comment?txtBlogID=${BLOG.blogID}" frameborder="0"></iframe>
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
