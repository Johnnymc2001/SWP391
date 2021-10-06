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
        <h1>Admin AccountList</h1>
        <div class="container-fluid">
            <div class="col-sm-12">
                <form>
                    <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/>
                    <select name="txtAccountType">
                        <option>All</option>
                        <option <c:if test="${param.txtAccountType == 'Student'}">selected</c:if>>Student</option>
                        <option <c:if test="${param.txtAccountType == 'Mentor'}">selected</c:if>>Mentor</option>
                        <option <c:if test="${param.txtAccountType == 'Admin'}">selected</c:if>>Admin</option>
                        </select>
                        <input type="Submit"/>
                    </form>
                </div>
                <div class="col-sm-12">
                <c:set var="list" value="${requestScope.LIST}"/>

                <c:if test="${not empty list}">

                    <table>
                        <tr>
                            <td>Username</td>
                            <td>Fullname</td>
                            <td>Role</td>
                            <td>Status</td>
                        </tr>
                        <c:forEach var="dto" items="${list}">
                            <tr>
                                <td>${dto.username}</td>
                                <td>${dto.fullname}</td>
                                <td>${dto.role}</td>
                                <td>${dto.status}</td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary">Action</button>
                                        <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <span class="sr-only">Action</span>
                                        </button>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item" href="accountDetail?accountid=${dto.accountID}">Modify</a>
                                            <c:if test="${dto.status == 'AVAILABLE'}">
                                                <a class="dropdown-item" href="#" onclick="deactivateAccount(${dto.accountID})">Deactivate</a>
                                            </c:if>
                                            <c:if test="${dto.status == 'UNAVAILABLE'}">
                                                <a class="dropdown-item" href="#" onclick="activateAccount(${dto.accountID})">Enable</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </td>
                            </tr>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
