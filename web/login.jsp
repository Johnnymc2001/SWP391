<%-- 
    Document   : login
    Created on : Sep 22, 2021, 9:22:23 AM
    Author     : henry
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Login</title>
        <link rel="icon" href="UI/Icon/Ficon.png" type="image/icon type">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="UI/CSS/loginPageStyle.css">
    </head>

    <body>

        <c:set var="error" value="${requestScope.LOGIN_ERROR}"/>


        <div class="login_table">
            <form class="loginform" name="login" action="login" method="POST">
                <h1>Login</h1>
                <div class="enter-field">
                    <input type="text" id="username" placeholder="Username" name ="username">
                    <c:if test ="${not empty error.usernameNullError}">
                        <br/>
                        <font color="red">
                        ${error.usernameNullError}<br/>
                        </font>
                    </c:if>
                    <c:if test ="${not empty error.usernameNotExist}">
                        <br/>
                        <font color="red">
                        ${error.usernameNotExist}<br/>
                        </font>
                    </c:if>


                </div>
                <div class="enter-field">
                    <input type="password" id="password" placeholder="Password" name="password">
                    
                        <c:if test ="${not empty error.usernameNullError}">
                        <br/>
                        <font color="red">
                        ${error.usernameNullError}<br/>
                        </font>
                    </c:if>
                    <c:if test ="${not empty error.usernameNotExist}">
                        <br/>
                        <font color="red">
                        ${error.usernameNotExist}<br/>
                        </font>
                    </c:if>
                        
                      <c:if test ="${not empty LOGIN_FAIL}">
                        <br/>
                        <font color="red">
                        ${LOGIN_FAIL}<br/>
                        </font>
                    </c:if>  
                        
                    
                </div>
                <div class="button">
                    <button type="submit" class="btn btn-outline-info">Login</button>
                </div>
                <div class="form_footer">
                    <div class="checkbox_rmb">
                        <input type="checkbox" id="rememberme">
                        <label for="rememberme">
                            <span class="label-text">Remember me</span>
                        </label>
                    </div>
                    <div class="forgotpassword">
                        <a href="resetPassword.html">Forgot password ?</a>
                    </div>
                </div>
            </form>
            <form action="registerPage">
                <div class="ortherschoice">
                    <h3>-- Or --</h3>
                    <p class="choices_btn">
                        <button type="submit" class="btn btn-outline-info">Sign up with @gmail.com</button>
                    </p>
                </div>
            </form>
        </div>



        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
                integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous">
        </script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
                integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
        </script>
    </body>

</html>
