<%-- 
    Document   : homePage
    Created on : Sep 21, 2021, 1:16:50 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>Home Page</title>
    <link rel="icon" href="UI/Icon/Ficon.png" type="image/icon type">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Carattere&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="UI/CSS/homePageStyle.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<c:set var="user" value="${sessionScope.USER}"/>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="home">
            <img src="UI/Icon/FPTLogo.jpg" alt="FPTLogo">
            FPT Academy
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
         <div>
        <form action="create">
            <input type="submit" value="Create a Blog" />
        </form>
        </div>
        
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav ml-auto">
                <form action="search" class="form-inline" method="POST">
                    <div class="search-navbar">
                        <input class="form-control" type="search" placeholder="Search" aria-label="Search" value="">
                        <button class="btn btn-outline-success" type="submit" value="">Search</button>
                    </div>
                </form>
                <a class="nav-item nav-link" href="aboutUs.html">About us</a>
                <c:if test="${empty user}">
                    <a class="nav-item nav-link" href="loginPage">Login</a>
                </c:if>
                <c:if test="${not empty user}">
                    <c:if test="${user.role == 'Student'}">
                        <div class="action">
                            <div class="profile-avatar">
                                <img src="UI/Icon/placeholder-avatar.png" alt="avatar">
                            </div>
                            <div class="menu">
                                <h3>${user.fullname}</h3>
                                <span>${user.role}</span>
                                <ul>
                                    <li><img src="UI/Icon/profile-icon.png" alt=""><a href="student/dashboard">Profile</a></li>
                                    <li><img src="UI/Icon/createblog-icon.png" alt=""><a href="createBlogPage">Create Blog</a></li>
                                    <li><img src="UI/Icon/logout-icon.png" alt=""><a href="">Log out</a></li>
                                </ul>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${user.role == 'Mentor'}">
                        <div class="action">
                            <div class="profile-avatar">
                                <img src="UI/Icon/maleteacher-icon.png" alt="avatar">
                            </div>
                            <div class="menu">
                                <h3>${user.fullname}</h3>
                                <span>${user.role}</span>
                                <ul>
                                    <li><img src="UI/Icon/profile-icon.png" alt=""><a href="mentor/dashboard">Profile</a></li>
                                    <li><img src="UI/Icon/approved-icon.png" alt=""><a href="mentor/blogPendingList">Pending Blogs</a></li>
                                    <li><img src="UI/Icon/createblog-icon.png" alt="createBlogPage"><a href="">Create Blog</a></li>
                                    <li><img src="UI/Icon/logout-icon.png" alt=""><a href="">Log out</a></li>
                                </ul>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${user.role == 'Admin'}">
                        <div class="action">
                            <div class="profile-avatar">
                                <img src="UI/Icon/admin-icon.png" alt="avatar">
                            </div>
                            <div class="menu">
                                <h3>${user.fullname}</h3>
                                <span>${user.role}</span>
                                <ul>
                                    <li><img src="UI/Icon/profile-icon.png" alt=""><a href="">Profile</a></li>
                                    <li><img src="UI/Icon/accounts-icon.png" alt=""><a href="admin/dashboard">Accounts</a></li>
                                    <li><img src="UI/Icon/logout-icon.png" alt=""><a href="">Log out</a></li>
                                </ul>
                            </div>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</nav>

<div class="belownav">
    <img src="UI/Icon/FPTCampus.jpg" alt="FPTCampus">
    <div class="xavalo">
        <h1>Blog</h1>
        <h2>Academic Blog for FPT</h2>
    </div>
</div>

<div class="header-bar"></div>

<div class="container-fluid">
    <c:set var="catBlogMap" value="${requestScope.CAT_TO_BLOG_MAP}" />
    <c:set var="blogImageMap" value="${requestScope.BLOG_TO_IMAGE_MAP}" />

    <c:forEach var="index" items="${catBlogMap}">
        <div class="category-name">
            <h1>${index.key.categoryName}</h1>
        </div>
        <div class="row">
            <c:forEach var="dtoBL" items="${index.value}">
                <div class="blog-box col-sm-6 col-md-4 col-lg-3">
                    <div class="pic-box">
                        <c:url var="blogDetail" value="blog">
                            <c:param name="txtBlogID" value="${dtoBL.blogID}"/>
                            <c:param name="txtStudentID" value="${dtoBL.studentID}"/>
                        </c:url>
                        <a href="${blogDetail}">
                            <c:set var="image" value="${blogImageMap[dtoBL]}"/>
                            
                            <c:if test="${not empty image}">
                                <img src="data:image/png;base64, ${image}" alt="Pic" />
                            </c:if>
                            <c:if test="${empty image}">
                                <img src="UI/Icon/selfmademan.jpg" alt="Pic" />
                            </c:if>
                        </a>
                        <h5>${dtoBL.title}</h5>
                    </div>
                    <div class="small-description">
                        <h4>${dtoBL.studentID}</h4>
                        <h6>${dtoBL.approvedDate}</h6>
                    </div>
                </div>

            </c:forEach>
        </div>
    </c:forEach>
</div>













<%--<section class="">
       <c:set var="dtoBlogList" value="${requestScope.BLOG_LIST}" />
       <c:forEach var="dtoBL" items="${dtoBlogList}">
           find a way to add attachment
               <div>
 <c:if test="${dtoBL.hasAttachment == true}">
     <c:set var="dtoBlogList" value="${sessionScope.ATTACHMENT_LIST}" />
     
     
     <c:forEach var="dtoAL" items="${dtoBlogList}">
         <c:if test="${dtoBL.blogID == dtoAL.blogID}">
             <img>${dtoAL.data}</img>
              <img src="data:image/jpg;base64,${base64}" width="240" height="300"/>
         </c:if>
     </c:forEach>
 </c:if>
<h3>${dtoBL.title}</h3>
<p>${dtoBL.approvedDate}</p>
</div>
</c:forEach>
</section>--%>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous">
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
</script>
</body>

</html>
