<%-- 
    Document   : createNewAccount
    Created on : Jun 30, 2021, 4:28:35 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Blog</title>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/createBlogPageStyle.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <!-- this is external JS -->
        <script src="UI/script/blogDetail.js"></script>
        <!-- this is bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is for summer note -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
        </script>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
        </script>

        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
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
                    <a href="home"><button class="active-btn">Home</button></a>
                    <a href="search?txtSearchType=popular"><button class="btn">Popular</button></a>
                    <a href="search?txtSearchType=recent"><button class="btn">Recent</button></a>
                    <a href="aboutUs.html"><button class="btn">About</button></a>
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

                        <div class="create-blog-container container">
                            <form action="create" method="POST" enctype='multipart/form-data'>
                                <div class="user-pick">
                                    <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>

                                    <div class="title-area">
                                        <span>Title: </span>
                                        <br>
                                        <input type="text" value="${param.txtTitle}" name="txtTitle" maxlength="100" size="62" required/>
                                    </div> 
                                    <br>
                                    <font color="red">
                                    ${requestScope.ERROR_TITLE}
                                    </font>

                                    <div class="category-area">
                                        <c:set var="dtoList" value="${requestScope.CATEGORY_LIST}"/>
                                        <span>Category: </span>
                                        <select name="categoryBox">
                                            <c:forEach var="dto" items="${dtoList}" varStatus="counter">
                                                <option value="${dto.categoryID}">${dto.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <%-------------------------THUMBNAIL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  --%>
                                    <div class="thumbnail-area">
                                        <span>Thumbnail: </span>
                                        <br>
                                        <label for="attachment">Choose file</label>
                                        <!--<input type="file" id="attachment" name="fileAttachment">-->
                                        <input accept="image/*" type='file' id="attachment" name="fileAttachment" />
                                        <span id="file-name">None</span>
                                        <br>
                                        <img id="imgReview" src="#" alt="Your Thumbnail" />
                                    </div>
                                    <br>
                                    <font color="red">
                                    ${requestScope.ERROR_UPLOAD}
                                    </font><br/>
                                </div>

                                <div class="user-write">
                                    <textarea id="summernote" name="txtContent">${param.txtContent}</textarea>
                                </div>
                                <br/>
                                <font color="red">
                                ${requestScope.ERROR_CONTENT}
                                </font>
                                <br/>
                                <div class="user-footer">
                                    <button class="post" type="submit">Post</button>
                                    <button class="reset" type="reset">Reset</button>
                                    <!-- <input class="post" type="submit" value="Post" />
                                    <input class="reset" type="reset" value="Reset" /> -->
                                </div>
                                <a href="home">Return to Home</a>
                            </form>
                        </div>

                        <!-- FOOTER -->
                        <div class="web-footer">
                            <p>2021 Henry. FE by Henry</p>
                            <button onclick="goTop()">Back to top</button>
                        </div>

                        <!-- this is JS for summernote -->
                        <script>
                            const blogContent = `${blog.content}`;
                        </script>
                        <script type="text/javascript" src="./UI/script/summernote.js"></script>
                        <script type="text/javascript" src="./UI/script/createBlog.js"></script>

                        </body>
                        </html>
