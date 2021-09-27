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
                                AccountID : ${account.accountID}
                                <input type="hidden" name="accountid" value="${account.accountID}"></input>
                            </div>
                            <div class="col-sm-6">
                                <input type="submit" name="submitAction" value="Update"></button>
                                <c:if test="${account.status == 'AVAILABLE'}">
                                    <input type="submit" name="submitAction" value="Disable"></button>
                                </c:if>

                                <c:if test="${account.status == 'UNAVAILABLE'}">
                                    <input type="submit" name="submitAction" value="Enable"></button>
                                </c:if>
                                ${requestScope.MESSAGE}
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                Username<br/>${requestScope.ERROR_USERNAME}
                                <input type="text" name="username" id="username" value="${account.username}" required/>
                            </div>
                            <div class="col-sm-6">
                                Password<br/>${requestScope.ERROR_PASSWORD}
                                <input type="password" name="password" id="password" value="${account.password}" onfocus="togglePasswordShow(this, 'text')" onblur="togglePasswordShow(this, 'password')" required/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                Fullname<br/>${requestScope.ERROR_FULLNAME}
                                <input type="text" name="fullname" id="fullname" value="${account.fullname}" required></input>
                            </div>
                            <div class="col-sm-6">
                                Birthday<br/>
                                <input type="date" name="birthday" id="birthday" value="${account.birthday}" required></input>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-12">
                                Address<br/>${requestScope.ERROR_ADDRESS}
                                <input type="text" name="address" id="address" value="${account.address}" required></input>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                Email<br/>${requestScope.ERROR_EMAIL}
                                <input type="text" name="email" id="email"value="${account.email}" required></input>
                            </div>
                            <div class="col-sm-6">
                                Phone<br/>${requestScope.ERROR_PHONE}
                                <input type="text" name="phone" id="phone" value="${account.phone}" required></input>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                Role<br/>
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
                                Category<br/>
                                <select name="category" id="categorySelect" hidden>
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
