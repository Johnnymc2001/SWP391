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
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav ml-auto">
                <form action="search" class="form-inline" method="POST">
                    <div class="search-navbar">
                        <input class="form-control" type="search" placeholder="Search" aria-label="Search" value="">
                        <button class="btn btn-outline-success" type="submit" value="">Search</button>
                    </div>
                </form>
                <a class="nav-item nav-link" href="aboutUs.html">About us</a>
                <a class="nav-item nav-link" href="login.jsp">Login</a>
            </div>
        </div>
    </nav>

    <div>
        <div class="xavalo">
            <img src="UI/Icon/FPTCampus.jpg" alt="FPTCampus">
            <h1>Blog</h1>
            <h2>Academic Blog for FPT</h2>
        </div>
    </div>

    <div class="header-bar"></div>

    <div class="container-fluid">
        <c:set var="dtoCategoryList" value="${requestScope.CATEGORY_LIST}" />
        <c:set var="dtoBlogList" value="${requestScope.BLOG_LIST}" />
        <c:forEach var="dtoCL" items="${dtoCategoryList}">
            <div class="category-name">
                <h1>${dtoCL.categoryName}</h1>
            </div>
            <div class="row">
                <c:forEach var="dtoBL" items="${dtoBlogList}">
                    <c:if test="${dtoBL.categoryID eq dtoCL.categoryID}">
                        <div class="blog-box col-sm-6 col-md-4 col-lg-3">
                            <div class="pic-box">
                                <img src="UI/Icon/selfmademan.jpg" alt="blog-pic">
                                <h5>${dtoBL.title}</h5>
                            </div>
                            <div class="small-description">
                                <h4>${dtoBL.studentID}</h4>
                                <h6>${dtoBL.approvedDate}</h6>
                            </div>
                        </div>
                    </c:if>
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
