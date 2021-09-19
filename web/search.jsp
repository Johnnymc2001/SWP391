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

    <c:if test="${empty param.txtSearchValue}">
        <c:set var="param.txtSearchValue" value=""/>
    </c:if>
    
    <form action="search" border="1">
        <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/>
        <input type="submit" name="Search"/>
    </form>

    <c:set var="list" value="${requestScope.SEARCH_RESULT}"/>
    <c:if test="${not empty list}">
        <table>
            <tr>
                <td>
                    ID
                </td>
                <td>
                    Title
                </td>
                <td>
                    Content
                </td>
            </tr>
            <c:forEach var="dto" items="${list}"  varStatus="count">
                <tr>
                    <td>
                        ${dto.blogID}
                    </td>
                    <td>
                        ${dto.title}
                    </td>
                    <td>
                        ${dto.content}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty list}">List is Empty!</c:if>

</html>
