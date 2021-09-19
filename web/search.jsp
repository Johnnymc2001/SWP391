<%-- 
    Document   : search
    Created on : Sep 19, 2021, 8:43:11 AM
    Author     : JohnnyMC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
    </body>
    
    <form action="search">
        <input type="text" name="txtSearchValue"/>
        <input type="submit" name="Search"/>
    </form>
    
    <c:set var="list" value="${requestScope.SEARCH_RESULT}"/>
    <c:forEach items="${list}" varStatus="count">
        AAA
    </c:forEach>
</html>
