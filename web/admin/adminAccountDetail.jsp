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
        <c:set var="account" value="${requestScope.ACCOUNT}"/>
        <c:set var="catList" value="${requestScope.CATEGORY_LIST}"/>
        <c:set var="roleList" value="${requestScope.ROLE_LIST}"/>

        <h1>Admin DetailAccount</h1>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3">
                    <!--<img src="https://www.tenforums.com/geek/gars/images/2/types/thumb_15951118880user.png"/>-->
                    Empty Image
                </div>

                <div class="col-sm-9">
                    <div class="row">
                        <div class="col-sm-6">
                        </div>
<!--                        <div class="col-sm-6">
                            <button id="editButton" onclick="toggle(this)">Edit</button>
                        </div>-->
                    </div>
                    <form action="accountDetail" method="POST">

                        <div class="row">
                            <div class="col-sm-6">
                                AccountID
                                <input type="text" name="accountid" value="${account.accountID}"></input>
                            </div>
                            <div class="col-sm-6">
                                <input type="submit" name="submitAction" value="Update"></button>
                                <c:if test="${account.status == 'AVAILABLE'}">
                                    <input type="submit" name="submitAction" value="Disable"></button>
                                </c:if>

                                <c:if test="${account.status == 'UNAVAILABLE'}">
                                    <input type="submit" name="submitAction" value="Enable"></button>
                                </c:if>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                Username
                                <input type="text" name="username" value="${account.username}"></input>
                            </div>
                            <div class="col-sm-6">
                                Password
                                <input type="password" name="password" value="${account.password}"></input>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                Fullname
                                <input type="text" name="fullname" value="${account.fullname}"></input>
                            </div>
                            <div class="col-sm-6">
                                Birthday
                                <input type="date" name="birthday" value="${account.birthday}"></input>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-12">
                                Address
                                <input type="text" name="address" value="${account.address}"></input>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                Email
                                <input type="email" name="email" value="${account.email}"></input>
                            </div>
                            <div class="col-sm-6">
                                Phone
                                <input type="text" name="phone" value="${account.phone}"></input>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                Role
                                <select name="role" id="roleSelect" onchange="changeRole(this);">
                                    <c:forEach var="item" items="${roleList}">
                                        <option value="${item}" 
                                                <c:if test="${account.role == item}">
                                                    selected
                                                </c:if>>
                                            ${item}
                                        </option>
                                    </c:forEach>
                                </select>
                                <!--<input type="text" name="role" value="${account.role}"></input>-->
                            </div>
                            <div class="col-sm-6">
                                Category
                                <select name="category" id="categorySelect">
                                    <c:forEach var="item" items="${catList}">
                                        <option value="${item.categoryID}"
                                                <c:if test="${account.categoryID eq item.categoryID}">
                                                    selected
                                                </c:if>>
                                            ${item.categoryName}</option>
                                        </c:forEach>
                                </select>
                                <!--<input type="text" name="categoryID" value="${account.categoryID}"></input>-->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <h1>STATUS : 
                                    <c:if test="${account.status == 'AVAILABLE'}">
                                        <font color="green">AVAILABLE</font>
                                    </c:if>

                                    <c:if test="${account.status == 'UNAVAILABLE'}">
                                        <font color="red">UNAVAILABLE</font>
                                    </c:if>
                                </h1>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="../UI/script/adminAccountDetail.js"></script>
</body>
</html>
