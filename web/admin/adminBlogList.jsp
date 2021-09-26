<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : admin
    Created on : Sep 22, 2021, 10:16:58 AM
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
        <h1>Admin ListAccount</h1>
        <div class="container-fluid">

            <div class="col-sm-12">
                <c:set var="list" value="${requestScope.LIST}"/>
                <table>
                    <tr>
                        <!--<td>AccountID</td>-->
                        <td>Blog Title</td>
                        <td>Content</td>
                        <td>User</td>
                    </tr>
                    <c:forEach var="item" items="${list}">
                        <tr>
                            <!--<td>${item.key.title}</td>-->
                            <td>${item.key.content}</td>
                            <td>${item.value.username}</td>
                            <td><button value="${item.key.blogID}">Edit</button></td>

                        </tr>
                    </c:forEach>
                </table>
            </div>

            <select name="maxPageItem" onchange="changeMaxPageItem(this)">
                <option>5</option>
                <option>10</option>
            </select>
        </div>
        <script type="text/javascript" src="../UI/script/adminBlogList.js"></script>
    </body>
</html>
