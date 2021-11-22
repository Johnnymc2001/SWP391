<%-- 
    Document   : NewloginPage
    Created on : Oct 5, 2021, 6:17:58 PM
    Author     : henry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <!-- this is bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/loginPageStyle.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <!-- this is external JS -->
    </head>
    <body>
        <div class="container-fluid">
            <img class="wave" src="UI/Icon/wave.png" alt="">
            <div class="page-row row align-items-center">
                <div class="col-lg-6 d-none d-lg-block">
                    <img class="phone-pic" src="UI/Icon/phone-pic.png" alt="">
                </div>
                <div id="login-table" class="login-table                     
                     <c:if test="${empty requestScope.PAGE || requestScope.PAGE == 'LOGIN'}">
                         activated 
                     </c:if>
                     <c:if test="${not empty requestScope.PAGE && requestScope.PAGE == 'REGISTER'}">
                         deactivated 
                     </c:if> col-lg-6">

                    <%--                    LOGIN                         --%>
                    <img class="avatar" src="UI/Icon/avatar-login.png" alt="">
                    <h1>Login</h1>
                    <form class="login-form" action="login" method="POST">
                        <div class="enter-field">
                            <input name="username" id="username" type="text" required>
                            <label class="username-label" for="username"><i class="fas fa-user"></i> Username</label>
                        </div>
                        <div class="enter-field">
                            <input name="password" id="password" type="password" required>
                            <label class="password-label" for="password"><i class="fas fa-lock"></i> Password</label>
                            <i id="close-eye" class="close-eye d-block fas fa-eye-slash" onclick="showPassword()"></i>
                            <i id="open-eye" class="open-eye d-none fas fa-eye" onclick="hidePassword()"></i>
                        </div>
                        <font color="red">
                        ${LOGIN_NULL}<br/>
                        </font>
                        <font color="red">
                        ${LOGIN_ERROR}<br/>
                        </font>
                        <font color="red">
                        ${LOGIN_FAIL}<br/>
                        </font>
                        <div class="footer-link">
                            <div class="row">
                                <div class="remember-me col-12 col-sm-6">
                                    <input id="checkbox" type="checkbox" name="chkRemember" value="true">
                                    <label for="checkbox">Remember me</label>
                                </div>
                                <div class="forgot-link col-12 col-sm-6">
                                    <a href="forgotPassword">Forgot password?</a>
                                </div>
                            </div>
                        </div>
                        <button class="login-btn" type="submit">Login</button><br>
                    </form>
                    <button class="goto-register" onclick="goToRegister()">Don't have an account ?</button>
                    
                    <div><button class="goto-register"><a href="home">Go to Home Page</a></button></div>
                </div>
                        

                <%--            END OF LOGIN             --%>


                <c:set var="error" value="${requestScope.ERROR}"/>
                <div id="register-table" class="register-table
                     <c:if test="${empty requestScope.PAGE || requestScope.PAGE == 'LOGIN'}">
                         deactivated 
                     </c:if>
                     <c:if test="${not empty requestScope.PAGE && requestScope.PAGE == 'REGISTER'}">
                         activated 
                     </c:if>
                     col-lg-6">
                    <img class="avatar" src="UI/Icon/avatar-login.png" alt="">
                    <h1>Register</h1>
                    <form class="register-form" action="register" method="POST">
                        <div class="row">
                            <div class="enter-field col-md-6">
                                <input name="username" id="username-register" value="${param.username}" type="text" required>
                                <label class="username-label" for="username-register"><i class="fas fa-user"></i> Username</label>
                                <c:if test="${not empty error.userNameLengthError}">
                                    <br/>
                                    <font color="red">
                                    ${error.userNameLengthError}<br/>
                                    </font>
                                </c:if>
                                <c:if test="${not empty error.userNameExisted}">
                                    <br/>
                                    <font color="red">
                                    ${error.userNameExisted}<br/>
                                    </font>
                                </c:if>
                            </div>
                            <div class="enter-field col-md-6">
                                <input name="fullname" id="fullname" value="${param.fullname}" type="text" required>
                                <label class="fullname-label" for="fullname"><i class="fas fa-file-signature"></i>
                                    Fullname</label>
                                    <c:if test="${not empty error.fullNameLengthError}">
                                    <br/>
                                    <font color="red">
                                    ${error.fullNameLengthError}<br/>
                                    </font>
                                </c:if>
                            </div>
                            <div class="enter-field col-md-6">
                                <input name="address" id="address" value="${param.address}" type="text" required>
                                <label class="address-label" for="address"><i class="fas fa-map-marked-alt"></i>
                                    Address</label>
                            </div>
                            <div class="enter-field col-md-6">
                                <input name="birthdate" id="birthdate" value="${param.birthdate}" type="text" max="9999-12-31" onfocus="(this.type = 'date')"
                                       onblur="if (!this.value) this.type = 'text'" required>
                                <label class="birthdate-label" for="birthdate"><i class="fas fa-calendar-alt"></i>Birthdate</label>
                                <c:set var="birthError" value="${requestScope.ERROR_BIRTHDATE}"/>
                                <c:if test="${not empty birthError}">
                                    <br/>
                                    <font color="red">
                                    ${birthError}<br/>
                                    </font>
                                </c:if>
                            </div>
                            <div class="enter-field col-md-6">
                                <input name="email" id="email" value="${param.email}" type="text" required>
                                <label class="email-label" for="email"><i class="fas fa-envelope"></i> Email</label>
                                <c:set var="emailError" value="${requestScope.ERROR_EMAIL}"/>
                                <c:if test="${not empty error.emailErrorFormat}">
                                    <br/>
                                    <font color="red">
                                    ${error.emailErrorFormat}<br/>
                                    </font>
                                </c:if>
                                <c:if test="${not empty emailError}">
                                    <br/>
                                    <font color="red">
                                    ${emailError}<br/>
                                    </font>
                                </c:if>
                            </div>
                            <div class="enter-field col-md-6">
                                <input name="phone" id="phone" value="${param.phone}"  type="text" required>
                                <label class="phone-label" for="phone"><i class="fas fa-phone-alt"></i> Phone</label>
                                <c:if test="${not empty error.phoneErrorFormat}">
                                    <br/>
                                    <font color="red">
                                    ${error.phoneErrorFormat}<br/>
                                    </font>
                                </c:if>
                            </div>

                            <div class="enter-field col-md-6">
                                <input name="password" id="password-register" type="password" required>
                                <label class="password-label" for="password-register"><i class="fas fa-lock"></i> Password</label>
                                <c:if test="${not empty error.passwordLengthError}">
                                    <br/>
                                    <font color="red">
                                    ${error.passwordLengthError}<br/>
                                    </font>
                                </c:if>
                                <i id="reclose-eye" class="close-eye d-block fas fa-eye-slash"
                                   onclick="showrePassword()"></i>
                                <i id="reopen-eye" class="open-eye d-none fas fa-eye" onclick="hiderePassword()"></i>
                            </div>
                            <div class="enter-field col-md-6">
                                <input name="confirm-password" id="confirm-password" type="password" required>
                                <label class="confirm-password-label" for="confirm-password"><i class="fas fa-lock"></i>Confirm Password</label>
                                <c:if test="${not empty error.confirmNotMatched}">
                                    <br/>
                                    <font color="red">
                                    ${error.confirmNotMatched}<br/>
                                    </font>
                                </c:if>
                                <i id="coclose-eye" class="close-eye d-block fas fa-eye-slash"
                                   onclick="showcoPassword()"></i>
                                <i id="coopen-eye" class="open-eye d-none fas fa-eye" onclick="hidecoPassword()"></i>
                            </div>
                        </div>
                        <button class="register-btn" type="submit">Register</button><br>
                    </form>
                    <button class="return-login" onclick="goToLogin()">Already have an account ?</button>
                </div>
            </div>
        </div>
        <script src="UI/script/loginPage.js"></script>
    </body>
</html>
