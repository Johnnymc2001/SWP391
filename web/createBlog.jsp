<%-- 
    Document   : createNewAccount
    Created on : Jun 30, 2021, 4:28:35 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Create a Post</h1>
        <form action="create" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Title: <br/><input type="text" value="${param.title}" name="txtTitle" maxlength="60" size="62"/> <br/>
            <c:if test="${not empty errors.titleLengthErr}">
                <font color="red">
                    ${errors.titleLengthErr}
                </font><br/>
            </c:if>
            Content:<br/> <textarea name="txtContent" rows="10" cols="50">Enter</textarea><br/>
            <c:if test="${not empty errors.contentLengthErr}">
                <font color="red">
                    ${errors.contentLengthErr}
                </font><br/>
            </c:if>
            
            <select name="categoryBox">
            <c:set var="dtoList" value="${sessionScope.CATEGORY}"/>
            <c:forEach var="dto" items="${dtoList}" varStatus="counter">
                <c:if test="${not empty dto && dto.qty!=0}">
                    <option>${dto.categoryName}</option>
                </c:if>
            </c:forEach>
                    
            <input type="submit" value="Post" name="btnAction" />
            <input type="reset" value="Reset" /><br/>
            <a href="Home">Return to Home</a>
        </form>
    </body>
</html>
