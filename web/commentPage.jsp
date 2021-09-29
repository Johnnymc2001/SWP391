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
    </head>
    <body>
        <section>
            <form action="createBlog" method="POST">
                <div>
                    <textarea name="content">Comment</textarea>
                    <input type="submit" value="comment">
                </div>
            </form>
            <div>
                <c:set var="dtoCMList" value="${requestScope.COMMENT_LIST}"/>
                <c:forEach var="dtoCM" items="dtoCMList">
                    <h4>${dtoCM.studentID}</h4>
                    <p>${dtoCM.content}</p>
                </c:forEach>
            </div>
        </section>
    </body>
</html>
