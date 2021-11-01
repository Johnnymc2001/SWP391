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
    <link rel="stylesheet" href="UI/CSS/navbar.css">
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
        <jsp:directive.include file="navbar.jsp" /> 
    </header>
    <!-- END OF NAVBAR -->

    <div class="parallax-window" data-parallax="scroll" data-image-src="${blog.thumbnail}">
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-lg-8">
                <div class="blog-title">
                    <h1>${blog.title}</h1>
                    <c:if test="${blog.studentID == user.accountID}">
                        <c:if test="${blog.status != 'PENDING'}">
                            <form action="edit" method="POST" >
                                <input type="hidden" name="txtBlogID" value="${blog.blogID}">
                                <button class="edit-btn" type="submit" name="btAction" value="Edit">Edit</button>
                            </form>
                        </c:if>
                    </c:if>
                    <c:if test="${blog.status == 'PENDING'}">
                        <h2>[PENDING]</h2>
                    </c:if>
                    <div class="blog-note">
                        <ul>

                            <li><a href="profile?userID=${author.accountID}">${author.getFullname()}</a></li>
                            <li>11/10/2001</li>
                            <li>comment(${blog.getAllComments().size()})</li>
                        </ul>
                    </div>
                </div>
                <div class="blog-content">
                    <p class="blog-text">
                        ${blog.content}
                    </p>
                    <c:if test="${blog.status == 'APPROVED'}">
                    <div class="blog-vote">
                        <%-- ----------- Khu Vuc Rating 52 ---------------------------   --%>
                        <div class="user-vote">
                            <div class="btn-group dropend">

                                <button type="submit" class="btn btnRate" data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                    <i class="fas fa-star fa-lg">${blog.getAverageRating()}</i> 
                                </button>
                                <c:if test="${not empty user}">
                                    <ul class="vote-menu dropdown-menu">
                                        <li onclick="rate(${blog.blogID}, 5)">
                                            <i class="fas fa-star fa-lg"></i>
                                            <span>5</span>
                                        </li>
                                        <li onclick="rate(${blog.blogID}, 4)">
                                            <i class="fas fa-star fa-lg"></i>
                                            <span>4</span>
                                        </li>
                                        <li onclick="rate(${blog.blogID}, 3)">
                                            <i class="fas fa-star fa-lg"></i>
                                            <span>3</span>
                                        </li>
                                        <li onclick="rate(${blog.blogID}, 2)">
                                            <i class="fas fa-star fa-lg"></i>
                                            <span>2</span>
                                        </li>
                                        <li onclick="rate(${blog.blogID}, 1)">
                                            <i class="fas fa-star fa-lg"></i>
                                            <span>1</span>
                                        </li>
                                    </ul>
                                </c:if>
                                <c:if test="${empty user}">
                                    <div class="vote-register dropdown-menu register-invitation">
                                        <br>
                                        <h3>Join us now</h3>
                                        <h6>Be one of us to reaches the world's knowledge</h6>
                                        <a href="registerPage"><button>Register to join</button></a>
                                        <p><a href="loginPage">Already have an account ?</a></p>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <%-- ----------- Rating 63 ---------------------------   --%>   
                        <div>
                            <form action="award?txtBlogID=${blog.blogID}" method="POST">
                                <c:set var="role" value="Mentor"/>
                                <c:if test="${user.role eq role}">
                                    <button class="award-btn" type="submit" name="btnAction"><i class="fas fa-trophy fa-lg">${requestScope.BLOGAWARDS}</i></button>
                                    </c:if>
                            </form>
                            <c:if test="${not (user.role  eq role)}">
                                <button class="award-btn"><i class="fas fa-trophy fa-lg">${requestScope.BLOGAWARDS}</i></button>
                                </c:if>
                        </div>
                    </div>
                    </c:if>


                    <div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                Your vote has been sent
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right-colum col-lg-4">
                <div class="row justify-content-center">
                    <div class="author-introduce col-md-6 col-lg-11">
                        <h4>Author of this article</h4>
                        <h2>${author.getFullname()}</h2>
                        <div class="reward">
                            <i class="fas fa-trophy fa-lg">
                                <p>${requestScope.BLOGAWARDS}</p>
                            </i>
                            <i class="fas fa-pen fa-lg">
                                <p>${requestScope.COMMENTQTY}</p>
                            </i>
                            <i class="fas fa-star fa-lg">
                                <p>${blog.getAverageRating()}</p>
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
                <c:if test="${empty user}">
                    <div class="register-invitation">
                        <h3>Join us now</h3>
                        <h6>Be one of us to reaches the world's knowledge</h6>
                        <a href="registerPage"><button>Register to join</button></a>
                        <p><a href="loginPage">Already have an account ?</a></p>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <!-- END OF BLOG CONTENT -->




    <!-- THIS IS COMMENT SECTION -->
    <c:if test="${blog.status == 'APPROVED'}">
    <div id="comment-section" class="comment-section container">
        <h2>Comment</h2>
        <div id="comment-post" class="comment-post">
            <iframe id="comment-window" class="comment-window" src="comment?txtBlogID=${BLOG.blogID}" frameborder="0"></iframe>
        </div>
    </div>
    </c:if>
    <!-- FOOTER -->
    <div class="web-footer">
        <p>2021 Henry. FE by Henry</p>
        <button onclick="goTop()">Back to top</button>
    </div>

    <!-- This is JS -->
    <script type="text/javascript" src="UI/script/vanilla-tilt.js"></script>



</body>

</html>
