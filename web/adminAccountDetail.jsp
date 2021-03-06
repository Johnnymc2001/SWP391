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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">     <link rel="stylesheet" href="/UI/CSS/adminDetailPageStyle.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AccountDetail</title>
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
        <c:set var="account" value="${requestScope.ACCOUNT}"/>
        <c:set var="catList" value="${requestScope.CATEGORY_LIST}"/>
        <c:set var="roleList" value="${requestScope.ROLE_LIST}"/>

        <h1>Account Detail</h1>
        <div class="container-fluid">
            <c:if test="${empty account}">
                <h1>Something wrong!</h1>
            </c:if>
            <c:if test="${not empty account}">
                <div class="row">
                    <div class="col-sm-12">
                        <!--                    <div class="row">
                                                    <div class="col-sm-6">
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <button id="editButton" onclick="toggle(this)">Edit</button>
                                                    </div>
                                                </div>-->
                        <form action="adminAccountDetail" method="POST">
                            <div class="row">
                                <div class="col-sm-6">
                                    <span>Account ID: ${account.accountID}</span>
                                    <input type="hidden" name="accountid" value="${account.accountID}" autocomplete="off"/>
                                </div>
                                <div class="col-sm-6">
                                    <button class="btn-action" type="submit" name="submitAction" value="Update">Update</button>
                                    <c:if test="${account.status == 'AVAILABLE'}">
                                        <button class="btn-action deactive" type="submit" name="submitAction" value="Disable">Disable</button>
                                    </c:if>
                                    <c:if test="${account.status == 'UNAVAILABLE'}">
                                        <button class="btn-action" type="submit" name="submitAction" value="Enable">Enable</button>
                                    </c:if>
                                    <font color="red">
                                    ${requestScope.MESSAGE}
                                    </font>
                                </div>
                                <div class="enter-field col-sm-6">
                                    Username<br/>
                                    <input type="text" name="username" id="username" value="${account.username}" autocomplete="off" disabled/>
                                    <font color="red">
                                    </font>
                                </div>

                                <div class="enter-field col-sm-6">
                                    Fullname<br/>
                                    <input type="text" name="fullname" id="fullname" value="${account.fullname}" autocomplete="off" disabled></input>
                                    <font color="red">
                                    </font>
                                </div>
                                <div class="enter-field col-sm-6">
                                    Birthday<br/>
                                    <input type="date" name="birthday" id="birthday" value="${account.birthday}" autocomplete="off" disabled></input>
                                </div>

                                <div class="enter-field col-sm-6">
                                    Address<br/>
                                    <input type="text" name="address" id="address" value="${account.address}" autocomplete="off" disabled></input>
                                    <font color="red">
                                    </font>
                                </div>

                                <div class="enter-field col-sm-6">
                                    Email<br/>
                                    <input type="text" name="email" id="email"value="${account.email}" autocomplete="off" disabled></input>
                                    <font color="red">
                                    </font>
                                </div>
                                <div class="enter-field col-sm-6">
                                    Phone<br/>
                                    <input type="text" name="phone" id="phone" value="${account.phone}" autocomplete="off" disabled></input>
                                    <font color="red">
                                    </font>
                                </div>
                                <div class="enter-field col-sm-6">
                                    Role<br/>
                                    <select class="form-select" name="role" id="roleSelect" onchange="changeRole(this);">
                                        <c:forEach var="item" items="${roleList}">
                                            <option value="${item}" 
                                                    <c:if test="${account.role == item}">
                                                        selected
                                                    </c:if>>
                                                ${item}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <font color="red">
                                    ${requestScope.ERROR_ROLE}
                                    </font>
                                </div>
                                    <!--<input type="text" name="role" value="${account.role}"></input>-->
                                <div class="enter-field col-sm-6">
                                    Category<br/>
                                    <select name="category" id="categorySelect" disabled>
                                        <c:forEach var="item" items="${catList}">
                                            <option value="${item.categoryID}"
                                                    <c:if test="${account.categoryID eq item.categoryID}">
                                                        selected
                                                    </c:if>>
                                                ${item.categoryName}</option>
                                            </c:forEach>
                                    </select>
                                    <font color="red">
                                    ${requestScope.ERROR_CATEGORY}
                                    </font>
                                    <!--<input type="text" name="categoryID" value="${account.categoryID}"></input>-->
                                </div>
                            </div>
                            <div class="enter-field col-sm-12">
                                <h1>STATUS : 
                                    <c:if test="${account.status == 'AVAILABLE'}">
                                        <font id="available-text">AVAILABLE</font>
                                    </c:if>

                                    <c:if test="${account.status == 'UNAVAILABLE'}">
                                        <font id="unavailable-text">UNAVAILABLE</font>
                                    </c:if>

                                    <c:if test="${account.status == 'PENDING'}">
                                        <font id="pending-text">PENDING</font>
                                    </c:if>
                                </h1>
                            </div>
                    </div>
                    </form>
                </div>
            </div>
        </c:if>
    </div>

    <!--this is js-->
    <script type="text/javascript" src="./UI/script/adminAccountDetail.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</body>
</html>
