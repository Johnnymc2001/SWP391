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
        <title>JSP Page</title>
    </head>
    <body>

        <form action="edit" method="POST" enctype='multipart/form-data'> 

            <c:set var="editBlog" value="${requestScope.BLOG_EDIT}"/>

            <input type="hidden" name ="txtBlogID" value="${editBlog.blogID}">

            Title: <br/><input type="text" value="${editBlog.title}" name="txtTitle" maxlength="60" size="62"/> <br/>
            <c:if test="${not empty ERROR_TITLE}" >
                <font color="red">
                ${requestScope.ERROR_TITLE}
                </font><br/>
            </c:if>

            Content <br/><input type="text" value="${editBlog.content}" name="txtContent" maxlength="60" size="62"/> <br/>
            <c:if test="${not empty ERROR_CONTENT}" >
                <font color="red">
                ${requestScope.ERROR_CONTENT}
                </font><br/>
            </c:if>

            <select name="categoryBox">
                <c:set var="dtoList" value="${requestScope.CATEGORY_LIST}"/>
                <c:forEach var="dto" items="${dtoList}" varStatus="counter">
                    <option value="${dto.categoryID}">${dto.categoryName}</option>
                </c:forEach>

                <c:if test="${not empty INVALID_USER}">
                    <font color="red">
                    ${requestScope.INVALID_USER}
                    </font><br/>
                </c:if>

                <input type="submit" value="Update" name="btAction"/>
                <input type="reset" value="Reset" /><br/>
                <a href="home">Return to Home</a>
        </form> 
    </body>
</html>
