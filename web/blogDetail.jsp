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
                                    <h1>I'm Tran Dang Khoa</h1>
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
                                        <h3>Tran Dang Khoa</h3>
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
                                    <h1>This is a very long blog title</h1>
                                    <div class="blog-note">
                                        <ul>
                                            <li><a href="">Author name</a></li>
                                            <li>11/10/2001</li>
                                            <li>comment(0)</li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="blog-content">
                                    <img class="blog-img" src="UI/Icon/selfmademan.jpg" alt="">
                                    <p class="content-text">
                                        If you're looking for random paragraphs, you've come to the right place. When a random word or a
                                        random sentence isn't quite enough, the next logical step is to find a random paragraph. We
                                        created
                                        the Random Paragraph Generator with you in mind. The process is quite simple. Choose the number
                                        of
                                        random paragraphs you'd like to see and click the button. Your chosen number of paragraphs will
                                        instantly appear.
                                        <br>
                                        While it may not be obvious to everyone, there are a number of reasons creating random
                                        paragraphs
                                        can be useful. A few examples of how some people use this generator are listed in the following
                                        paragraphs.
                                        <br>
                                        Creative Writing
                                        <br>
                                        Generating random paragraphs can be an excellent way for writers to get their creative flow
                                        going at
                                        the beginning of the day. The writer has no idea what topic the random paragraph will be about
                                        when
                                        it appears. This forces the writer to use creativity to complete one of three common writing
                                        challenges. The writer can use the paragraph as the first one of a short story and build upon
                                        it. A
                                        second option is to use the random paragraph somewhere in a short story they create. The third
                                        option is to have the random paragraph be the ending paragraph in a short story. No matter which
                                        of
                                        these challenges is undertaken, the writer is forced to use creativity to incorporate the
                                        paragraph
                                        into their writing.
                                        <br>
                                        Tackle Writers' Block
                                        <br>
                                        A random paragraph can also be an excellent way for a writer to tackle writers' block. Writing
                                        block
                                        can often happen due to being stuck with a current project that the writer is trying to
                                        complete. By
                                        inserting a completely random paragraph from which to begin, it can take down some of the issues
                                        that may have been causing the writers' block in the first place.
                                        <br>
                                        Beginning Writing Routine
                                        <br>
                                        Another productive way to use this tool to begin a daily writing routine. One way is to generate
                                        a
                                        random paragraph with the intention to try to rewrite it while still keeping the original
                                        meaning.
                                        The purpose here is to just get the writing started so that when the writer goes onto their
                                        day's
                                        writing projects, words are already flowing from their fingers.
                                        <br>
                                        Writing Challenge
                                        <br>
                                        Another writing challenge can be to take the individual sentences in the random paragraph and
                                        incorporate a single sentence from that into a new paragraph to create a short story. Unlike the
                                        random sentence generator, the sentences from the random paragraph will have some connection to
                                        one
                                        another so it will be a bit different. You also won't know exactly how many sentences will
                                        appear in
                                        the random paragraph.
                                        <br>
                                        Programmers
                                        <br>
                                        It's not only writers who can benefit from this free online tool. If you're a programmer who's
                                        working on a project where blocks of text are needed, this tool can be a great way to get that.
                                        It's
                                        a good way to test your programming and that the tool being created is working well.
                                        <br>
                                        Above are a few examples of how the random paragraph generator can be beneficial. The best way
                                        to
                                        see if this random paragraph picker will be useful for your intended purposes is to give it a
                                        try.
                                        Generate a number of paragraphs to see if they are beneficial to your current project.
                                        <br>
                                        If you do find this paragraph tool useful, please do us a favor and let us know how you're using
                                        it.
                                        <br>
                                        It's greatly beneficial for us to know the different ways this tool is being used so we can
                                        improve
                                        <br>
                                        it with updates. This is especially true since there are times when the generators we create get
                                        used in completely unanticipated ways from when we initially created them. If you have the time,
                                        please send us a quick note on what you'd like to see changed or added to make it better in the
                                        future.
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
