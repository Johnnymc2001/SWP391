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
    <div class="header-bar"></div>

    <div class="container-fluid">
        <c:set var="dtoBD" value="${requestScope.BLOG_DETAIL}" />
        <h1>${dtoBD.title}</h1>
        <%--<c:if test="${user.role=='Mentor'}">--%>
        <form action="award">
            <input type="hidden" value="${dtoBD.blogID}" name="txtBlogID"/>
            <input type="submit" value="Give Blog Award" />
        </form>
        <%--</c:if>--%>
        <h4>by ${dtoBD.studentID}</h4>
        <h5>${dtoBD.approvedDate}</h5>
        <div>
            <img src="${dtoBD.getFirstImage()}" alt="blog-pic">
        </div>
        <c:if test="${ empty USER}">
            <font color="red">
            Please login to edit this blog !!
            </font><br/>
        </c:if>

        <c:if test="${not empty USER}">
            <form action="edit" method="POST"> 
                <input type="hidden" name ="txtBlogID" value="${dtoBD.blogID}">
                <input type="submit" value="Edit" name="btAction"/>
                <c:if test="${ empty USER}">
                    <font color="red">
                    Please login to edit this blog !!
                    </font><br/>
                </c:if>
            </form>     
        </c:if>





        <p>${dtoBD.content}</p>
    </div>
</div>


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
