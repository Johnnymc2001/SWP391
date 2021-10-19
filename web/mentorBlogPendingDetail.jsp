<%-- 
    Document   : mentor
    Created on : Sep 27, 2021, 7:14:43 AM
    Author     : JohnnyMC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:if test="${empty sessionScope.USER}">
        <c:redirect url="/"/>
    </c:if>

    <c:if test="${not empty sessionScope.USER}">
        <c:if test="${sessionScope.USER.role != 'Mentor'}">
            <c:redirect url="/"/>
        </c:if>
    </c:if>
    <c:set var="user" value="${sessionScope.USER}"/>
    <head>
        <!-- this is bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/createBlogPageStyle.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <!-- this is external JS -->
        <!--this is for summernote-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
        <script type="text/javascript" src="../UI/script/mentorBlogPendingDetail.js"></script>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Edit Page</title>
    </head>
    <body>
        <c:set var="blog" value="${requestScope.BLOG}"/>
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
                        <a href="blogPendingList"><li>Pending Blog</li></a>
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

        <div class="create-blog-container container">
            <form action="blogPendingDetail" method="POST" accept-charset="utf-8">
                <input type="hidden" name="blogid" value="${blog.blogID}">
                ${requestScope.ERROR_TITLE}<br/>
                <div class="title-area">
                    <span>Title: </span>
                    <br>
                    <input type="text" value="${blog.title}" name="title"/>
                </div> 
                ${requestScope.ERROR_TITLE}<br/>
                <div class="user-write">
                    <font color="orange">
                    ${requestScope.MESSAGE}
                    </font>
                    <textarea id="summernote" name="content"></textarea>
                </div>
                <script>
                    $('#summernote').summernote({
                        placeholder: "",
                        tabsize: 2,
                        height: 400,
                        maximumImageFileSize: 1000 * 1024, // 2mb
                        callbacks: {
                            onImageUpload: function (image) {
                                uploadImage(image[0]);
                            }
                        }
                    });
                    function uploadImage(image) {
                        var data = new FormData();
                        data.append("image", image);
                        $.ajax({
                            url: './imageUpload',
                            cache: false,
                            contentType: false,
                            processData: false,
                            data: data,
                            type: "post",
                            success: function (json) {
                                if (json.status === "OK") {
                                    var image = $('<img>').attr('src', json.imageUrl);
                                    $('#summernote').summernote("insertNode", image[0]);
                                } else {
                                    console.log(json);
                                }

                            },
                            error: function (data) {
                                console.log(data);
                            }
                        });
                    }
                    $('#summernote').summernote('code', `${blog.content}`);
                </script>
                <div class="user-footer">
                    <button class="btn-action" type="submit" name="submitAction" value="Update">Update</button>
                    <button class="btn-action" type="submit" name="submitAction" value="Approve">Approve</button>
                    <button class="btn-action" type="submit" name="submitAction" value="Disapprove">Disapprove</button>
                </div>
                <a href="blogPendingList">Return</a>
            </form>
        </div>

    </body>
</html>
