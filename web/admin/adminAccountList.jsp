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
        <title>Admin page</title>
        <!-- this is bootstrap 5-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="../UI/CSS/adminIframe.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>
    <body>
        <h1>Admin AccountList</h1>
        <form class="search-account-form">
            <input class="search-account-textbox" type="text" name="txtSearchValue" value="${param.txtSearchValue}"/>
            <select class="role-select" name="txtAccountType">
                <option>All</option>
                <option <c:if test="${param.txtAccountType == 'Student'}">selected</c:if>>Student</option>
                <option <c:if test="${param.txtAccountType == 'Mentor'}">selected</c:if>>Mentor</option>
                <option <c:if test="${param.txtAccountType == 'Admin'}">selected</c:if>>Admin</option>
                </select>
                <button class="btn-action submit" type="Submit">Submit</button>
            </form>
        <c:set var="list" value="${requestScope.LIST}"/>
        <c:if test="${not empty list}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Fullname</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <c:forEach var="dto" items="${list}">
                    <tbody>
                        <tr>
                            <td>${dto.username}</td>
                            <td>${dto.fullname}</td>
                            <td>${dto.role}</td>
                            <td>${dto.status}</td>
                            <td>
                                <a href="accountDetail?accountid=${dto.accountID}"><button class="btn-action">Modify</button></a>
                                <c:if test="${dto.status == 'AVAILABLE'}">
                                    <a href="#" onclick="deactivateAccount(${dto.accountID})"><button class="btn-action deactive">Deactivate</button></a>
                                </c:if>
                                <c:if test="${dto.status == 'UNAVAILABLE'}">
                                    <a href="#" onclick="activateAccount(${dto.accountID})"><button class="btn-action enable">Enable</button></a>
                                </c:if>
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${requestScope.PAGE_COUNT}">
                    <c:if test="${i == param.page || ( empty param.page && i == 1)}">
                        <li class="page-item active">
                            <a class="page-link" href="#" onclick="changePage(${i})">
                                ${i}
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${i != param.page && (not empty param.page || i != 1)}">
                        <li class="page-item">
                            <a class="page-link"href="#" onclick="changePage(${i})">
                                ${i}
                            </a>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </nav>
    </c:if>
    <c:if test="${empty list}"><h2>Here used to be some accounts, but now it's not :<</h2></c:if>


    <!--                        <select name="maxPageItem" onchange="changeMaxPageItem(this)">
                                <option>5</option>
                                <option>10</option>
                            </select>-->
</div>
<script type="text/javascript" src="../UI/script/adminAccountList.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

</body>
</html>
