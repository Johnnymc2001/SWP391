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
        <form action="image" method="POST" enctype='multipart/form-data'>
            <br/><input type="file" name="file">
            <input type="submit"><br/>
        </form>

        <c:set var="list" value="${requestScope.IMAGE}"/>
        <table>
            TABLE GOES HERE
            <c:forEach var="base64" items="${list}">
                <tr>
                    <td>IMAGE</td>    
                    <td><img src="data:image/jpg;base64,${base64}" width="240" height="300"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
