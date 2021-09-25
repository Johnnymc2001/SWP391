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
        <h1>Admin CategoryManage</h1>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6">
                    <c:set var="list" value="${requestScope.LIST}"/>
                    ${requestScope.MESSAGE}
                    <table>
                        <tr>
                            <td>Category ID</td>
                            <td>Category Name</td>
                            <td>Posts</td>
                        </tr>
                        <c:forEach var="dto" items="${list}">
                            <tr>
                            <form action="categoryManage" method="POST">
                                <td><input type="text" name="categoryid" value="${dto.key.categoryID}" required/></td>
                                <td> <input type="text" name="categoryname" value="${dto.key.categoryName}" required/></td>
                                <td>${dto.value}</td>
                                <td><input type="submit" name="submitAction" value="Edit"/></td>
                                <td>  <c:if test="${dto.value eq 0}">
                                        <input type="submit" name="submitAction" value="Delete"/>
                                    </c:if>
                                </td>
                            </form>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <div class="col-sm-6">
                    Add new Category
                    <form action="categoryManage" method="GET">
                        <input type="text" name="categoryid"/>
                        <input type="text" name="categoryname"/>
                        <td><input type="submit" name="submitAction" value="Add"/></td>
                    </form>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="../UI/script/adminListAccount.js"></script>
    </body>
</html>
