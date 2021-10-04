<%-- 
    Document   : mentor
    Created on : Sep 27, 2021, 7:14:43 AM
    Author     : JohnnyMC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mentor BlogPendingEdit</h1>
        ${requestScope.MESSAGE}
        <c:set var="blog" value="${requestScope.BLOG}"/>
        <form>
            <input type="hidden" name="blogid" value="${blog.blogID}">
            ${requestScope.ERROR_TITLE}<br/>
            <input type="text" name="title" value="${blog.title}"><br/>
            ${requestScope.ERROR_TITLE}<br/>
            <input type="text" name="content" value="${blog.content}">
            <input type="submit" name="submitAction" value="Update"/>
            <input type="submit" name="submitAction" value="Approve"/>
            <input type="submit" name="submitAction" value="Disapprove"/>
        </form>

    </body>
</html>
