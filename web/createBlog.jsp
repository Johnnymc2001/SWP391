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
        <h1>Create New Account</h1>
        <form action="create" method="POST">
            Title (maximum 60 characters): <br/><input type="text" value="" name="title" maxlength="60" size="62"/> <br/>
            Content:<br/> <textarea name="content" rows="10" cols="50">Enter</textarea><br/>
            <select name="categoryBox">
            <c:set var="dtoList" value="${sessionScope.CATEGORY}" />
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
