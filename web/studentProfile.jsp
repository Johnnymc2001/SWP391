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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <c:set var="user" value="${sessionScope.USER}"/>
        <c:set var="account" value="${requestScope.ACCOUNT}"/>

        <c:if test="${ empty account }">
            <h1>account empty</h1>
        </c:if>

        <c:if test="${user.accountID==account.accountID}">
            <h1>${account.username}</h1> 
            <div>
                <form action="profile" method="POST">
                    <input type ="hidden" name ="userID" value="${account.accountID}" >
                    username<input type ="text" name ="username" value="${account.username}" > </br>
                    full name<input type ="text" name ="fullname" value="${account.fullname}" ></br>
                    address<input type ="text" name ="address" value="${account.address}" ></br>
                    password<input type ="password" name ="password" value="${account.password}" ></br>
                    email<input type ="text" name ="email" value="${account.email}" ></br>
                    phone<input type ="text" name ="phone" value="${account.phone}" ></br>
                    birthdate<input type="date" id="birthdate" name ="birthdate" value="${account.birthday}"></br>
                    <input type="submit" name ="btAction" value="UpdateProfile">
                </form>
            </div>   
        </c:if>

        <c:if test="${user.accountID!=account.accountID}">
            <div>
                <p>${account.username}</p> 
                <p>${account.fullname}</p> 
                <p>${account.address}</p>                    
                <p>${account.username}</p> 
                <p>${account.username}</p>                    
            </div>
        </c:if>

        <c:forEach var="blog" items="${requestScope.CURBLOG}">          
            <a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.thumbnail}" alt=""></a>
            <h6><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></h6>
            <p>${blog.postDate}</p>
        </c:forEach>     

    </body>
</html>
