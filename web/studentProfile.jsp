<%-- 
    Document   : studentProfile
    Created on : Oct 24, 2021, 11:03:26 AM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <c:set var="user" value="${sessionScope.USER}"/>
        <c:set var="account" value="${requestScope.ACCOUNT}"/>

        <c:if test="${ empty account }">
            <h1>account empty</h1>
        </c:if>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <c:if test="${user.accountID==account.accountID}">
                        <h1>${account.username}</h1> 
                        <div>
                            <form action="profile" method="POST">
                                <input type ="hidden" name ="userID" value="${account.accountID}" >
                                Username <input type ="text" name ="username" value="${account.username}" > </br>
                                Full Name <input type ="text" name ="fullname" value="${account.fullname}" ></br>
                                Address <input type ="text" name ="address" value="${account.address}" ></br>
                                Password <input type ="password" name ="password" value="${account.password}" ></br>
                                Email <input type ="text" name ="email" value="${account.email}" ></br>
                                Phone <input type ="text" name ="phone" value="${account.phone}" ></br>
                                Birthdate <input type="date" id="birthdate" name ="birthdate" value="${account.birthday}"></br>
                                <input type="submit" name ="btAction" value="UpdateProfile">
                            </form>
                        </div>   
                    </c:if>

                    <c:if test="${user.accountID!=account.accountID}">
                        <div>
                            <!--   No Need to show Username                      
                            Username <br/>
                            <p>${account.username}</p> 
                            -->
                            
                            Full Name<br/>
                            <p>${account.fullname}</p> 
                            Address<br/>
                            <p>${account.address}</p>                                      
                        </div>
                    </c:if>
                </div>

                <div class="col-md-4">
                    <c:forEach var="blog" items="${requestScope.CURBLOG}">          
                        <a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.thumbnail}" alt=""></a>
                        <h6><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></h6>
                        <p>${blog.postDate}</p>
                    </c:forEach>   
                </div>
            </div>
        </div>



    </body>
</html>
