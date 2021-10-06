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
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
        <script type="text/javascript" src="../UI/script/mentorBlogPendingDetail.js"></script>

        <script src=""></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mentor BlogPendingEdit</h1>
        ${requestScope.MESSAGE}
        <c:set var="blog" value="${requestScope.BLOG}"/>
        <form action="blogPendingDetail" method="POST">
            Title
            <input type="hidden" name="blogid" value="${blog.blogID}">
            ${requestScope.ERROR_TITLE}<br/>
            <input type="text" name="title" value="${blog.title}"><br/>
            ${requestScope.ERROR_TITLE}<br/>
            <textarea id="summernote" name="content"></textarea>
            <script>
                $('#summernote').summernote({
                    placeholder: "",
                    tabsize: 2,
                    height: 100
                });
                $('#summernote').summernote('code', `${blog.content}`);
            </script>
            <input type="submit" name="submitAction" value="Update"/>
            <input type="submit" name="submitAction" value="Approve"/>
            <input type="submit" name="submitAction" value="Disapprove"/>
        </form>

    </body>
</html>
