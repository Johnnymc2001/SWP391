<%-- 
    Document   : edit
    Created on : Oct 3, 2021, 3:32:20 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form action="edit" method="POST" enctype='multipart/form-data'> 

            <c:set var="editBlog" value="${requestScope.BLOG_EDIT}"/>

            Title: <br/><input type="text" value="${editBlog.title}" name="txtTitle" maxlength="60" size="62"/> <br/>
            <c:if test="${not empty ERROR_TITLE}" 
                  <font color="red">
                ${requestScope.ERROR_TITLE}
                </font><br/>
            </c:if>

            Title: <br/><input type="text" value="${editBlog.content}" name="txtContent" maxlength="60" size="62"/> <br/>
            <c:if test="${not empty ERROR_CONTENT}" 
                  <font color="red">
                ${requestScope.ERROR_CONTENT}
                </font><br/>
            </c:if>

            <select name="categoryBox">
                <c:set var="dtoList" value="${requestScope.CATEGORY_LIST}"/>
                <c:forEach var="dto" items="${dtoList}" varStatus="counter">
                    <option value="${dto.categoryID}">${dto.categoryName}</option>
                </c:forEach>

                <input type="submit" value="Update" name="btAction"/>
                <input type="reset" value="Reset" /><br/>
                <a href="home">Return to Home</a>
        </form> 
    </body>
</html>
