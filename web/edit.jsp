<%-- 
    Document   : edit
    Created on : Oct 3, 2021, 3:32:20 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Blog Page</title>
        <!-- this is bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/createBlogPageStyle.css">
        <link rel="stylesheet" href="UI/CSS/blogDetailPage.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
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
                        <a href="create"><li>Create Blog</li></a>
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

        <div class="create-blog-container container-fluid">
            <div class="row">
                <div class="col-md-12 col-lg-8">
                    <form action="edit" method="POST" enctype='multipart/form-data'>
                        <div class="user-pick">
                            <c:set var="editBlog" value="${requestScope.BLOG_EDIT}"/>
                            <div class="thumbnail-area">
                                <span>Thumbnail: </span>
                                <br>
                                <label for="attachment">Choose file</label>
                                <!--<input type="file" id="attachment" name="fileAttachment">-->
                                <input accept="image/*" type='file' id="attachment" name="fileAttachment" />
                                <span id="file-name">${editBlog.thumbnail.toString()}</span>
                                <br>
                                <img id="imgReview" src="${editBlog.thumbnail}" alt="Your Thumbnail" />
                            </div>
                            <font color="red">
                            ${requestScope.ERROR_UPLOAD}
                            </font>
                            <input type="hidden" name ="txtBlogID" value="${editBlog.blogID}">
                            <div class="title-area">
                                <span>Title: </span>
                                <br>
                                <input type="text" value="${editBlog.title}" name="txtTitle" maxlength="60" size="62"/>
                            </div> 
                            <c:if test="${not empty ERROR_TITLE}" >
                                <font color="red">
                                ${requestScope.ERROR_TITLE}
                                </font><br/>
                            </c:if>
                            <div class="category-area">
                                <span>Category: </span>
                                <select name="categoryBox">
                                    <c:set var="dtoList" value="${requestScope.CATEGORY_LIST}"/>
                                    <c:forEach var="dto" items="${dtoList}" varStatus="counter">
                                        <option value="${dto.categoryID}">${dto.categoryName}</option>
                                    </c:forEach>
                                    `<c:if test="${not empty INVALID_USER}">
                                        <font color="red">
                                        ${requestScope.INVALID_USER}
                                        </font><br/>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <div class="user-write">
                            <textarea id="summernote" name="txtContent">${editBlog.content}</textarea>
                        </div>
                        <div class="user-footer">
                            <button class="btn-action" type="submit" value="Update" name="btAction">Update</button>
                            <button class="btn-action" type="reset" value="Reset" >Reset</button>
                        </div>
                        <a href="home">Return to Home</a>
                </div>
                <div class="right-colum col-lg-4">
                    <div class="row justify-content-center">
                        <div class="author-introduce col-md-6 col-lg-11">
                            <h4>Author of this article</h4>
                            <h2>${user.getFullname()}</h2>
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
                </div>
            </div>
        </div>

        <!-- FOOTER -->
        <div class="web-footer">
            <p>2021 Henry. FE by Henry</p>
            <button onclick="goTop()">Back to top</button>
        </div>

        <script>
            $('#summernote').summernote({
                placeholder: 'Ready to share .... ',
                tabsize: 2,
                height: 300
            });
        </script>
        <script type="text/javascript" src="./UI/script/createBlog.js"></script>
    </form> 
</body>
</html>
