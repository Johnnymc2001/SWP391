<%-- 
    Document   : commentPage
    Created on : Sep 29, 2021, 10:53:31 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comment Section</title>
        <!-- this is bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="./UI/CSS/commentPage.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>
    <body>

        ${requestScope.MESSAGE}
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${not empty user}">
            <c:if test="${user.role == 'Mentor' || user.role == 'Student' || user.role == 'Admin'}">
                <form class="comment-input" action="comment" method="post">
                    <input type="hidden" name="txtBlogID" value="${param.txtBlogID}"
                    <div class="avatar">
                        <img src="UI/Icon/avatar-login.png" alt="">
                    </div>
                    <div class="enter-field">
                        <input class="user-comment" type="text" name="content" id="txtComment">
                    </div>
                    <button class="send-btn" type="submit"><i class="fas fa-paper-plane fa-lg"></i></button>
                </form>
            </c:if>
        </c:if>
        <c:set var="dtoCmMAP" value="${requestScope.COMMENT_MAP}"/>
        <div class="comment-post">
            <c:forEach var="dtoCm" items="${dtoCmMAP}">
                <div class="header-comment">
                    <div class="avatar">
                        <img src="UI/Icon/avatar-login.png" alt="">
                    </div>
                    <div class="comment-field">
                        <h5>${dtoCm.value.fullname}</h5>
                        <p>${dtoCm.key.content}</p>
                    </div>
                    <div>
                        <button class="admin-cmt-btn"><i class="fas fa-times fa-sm"></i></button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
