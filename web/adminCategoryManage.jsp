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
        <c:if test="${empty sessionScope.USER}">
            <c:redirect url="/"/>
        </c:if>

        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${sessionScope.USER.role != 'Admin'}">
                <c:redirect url="/"/>
            </c:if>
        </c:if>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category Manage</title>
        <!-- this is bootstrap 5-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="./UI/CSS/adminIframe.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>
    <body>
        <div class="container-fluid">
            <div class="col-sm-12 category-manage">
                <h1>Category Manage</h1>
                <c:set var="list" value="${requestScope.LIST}"/>
                ${requestScope.MESSAGE}
                <table class="table">
                    <thead>
                        <tr>
                            <td>Category ID</td>
                            <td>Category Name</td>
                            <td>Posts</td>
                            <td colspan="2">Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${list}">
                            <tr>
                        <form action="adminCategoryManage" method="POST">
                            <td><input type="text" name="categoryid" value="${dto.key.categoryID}" autocomplete="off" required readonly/></td>
                            <td> <input type="text" name="categoryname" value="${dto.key.categoryName}" autocomplete="off" required/></td>
                            <td>${dto.value}</td>
                            <td><button class="btn-action" type="submit" name="submitAction" value="Edit">Edit</button></td>
                            <td>  
                                <c:if test="${dto.value eq 0}">
                                    <button class="btn-action deactive" type="submit" name="submitAction" value="Delete">Delete</button>
                                </c:if>
                            </td>
                        </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-sm-12 add-category">
                <h1>Add new Category</h1>
                <table class="table table-info">
                    <thead>
                        <tr>
                            <td>Category ID</td>
                            <td>Category Name</td>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                    <form action="adminCategoryManage" method="GET">
                        <td><input type="text" name="categoryid" autocomplete="off"/></td>
                        <td><input type="text" name="categoryname" autocomplete="off"/></td>
                        <td><button class="btn-action" type="submit" name="submitAction" value="Add">Add</button></td>
                    </form>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <script type="text/javascript" src="./UI/script/adminListAccount.js"></script>
    </body>
</html>
