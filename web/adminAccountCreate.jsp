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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">     <link rel="stylesheet" href="/UI/CSS/adminDetailPageStyle.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account [Admin]</title>
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
        <c:set var="catList" value="${requestScope.CATEGORY_LIST}"/>
        <c:set var="roleList" value="${requestScope.ROLE_LIST}"/>

        <h1>Admin CreateAccount</h1>
        <div class="container-fluid">
            <form action="accountCreate" method="POST">
                <div class="row">
                    <div class="enter-field col-md-6">
                        Username${requestScope.ERROR_USERNAME}
                        <input type="text" name="username" id="username" value="${param.username}" required/>
                    </div>
                    <div class="enter-field col-md-6">
                        Fullname${requestScope.ERROR_FULLNAME}
                        <input type="text" name="fullname" id="fullname" value="${param.fullname}" required/>
                    </div>
                    <div class="enter-field col-md-6">
                        Password${requestScope.ERROR_PASSWORD}
                        <input type="password" name="password" id="password" value="${param.password}" required/>
                    </div>
                    <div class="enter-field col-md-6">
                        Confirm Password${requestScope.ERROR_CONFIRM_PASSWORD}
                        <input type="password" name="confirm_password" id="confirm_password" value="${param.confirm_password}" required/>
                    </div>
                    <div class="enter-field col-md-6">
                        Address${requestScope.ERROR_ADDRESS}
                        <input type="text" name="address" id="address" value="${param.address}" required/>
                    </div>
                    <div class="enter-field col-md-6">
                        Birthday
                        <input type="date" name="birthday" id="birthday" value="${param.birthday}" required/>
                    </div>
                    <div class="enter-field col-md-6">
                        Email${requestScope.ERROR_EMAIL}
                        <input type="text" name="email" id="email"value="${param.email}" required/>
                    </div>
                    <div class="enter-field col-md-6">
                        Phone${requestScope.ERROR_PHONE}
                        <input type="text" name="phone" id="phone" value="${param.phone}" required/>
                    </div>
                    <div class="enter-field col-md-6">
                        <span>Role</span>
                        ${requestScope.ERROR_ROLE}
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
                    </div>
                    <div class="enter-field col-md-6">
                        <span>Category</span>
                        ${requestScope.ERROR_CATEGORY}
                        <select name="category" id="categorySelect" disabled>
                            <c:forEach var="item" items="${catList}">
                                <option value="${item.categoryID}"
                                        <c:if test="${account.categoryID eq item.categoryID}">
                                            selected
                                        </c:if>>
                                    ${item.categoryName}</option>
                                </c:forEach>
                        </select>
                    </div>
                    <div class="footer-create-admin">
                        <button class="btn-action" type="submit" name="submitAction" value="Create">Create</button>
                    </div>
            </form>
        </div>
        <script type="text/javascript" src="./UI/script/adminAccountCreate.js"></script>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
