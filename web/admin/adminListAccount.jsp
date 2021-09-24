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
        <h1>Admin ListAccount</h1>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-4">
                    <c:set var="list" value="${requestScope.LIST}"/>
                    <table>
                        <tr>
                            <td>AccountID</td>
                            <td>Username</td>
                            <td>Fullname</td>
                            <td></td>
                        </tr>
                        <c:forEach var="dto" items="${list}">
                            <tr>
                                <td>${dto.accountID}</td>
                                <td>${dto.username}</td>
                                <td>${dto.fullname}</td>
                                <td><button value="${dto.accountID}">Edit</button></td>

                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="col-sm-8">
                    <iframe src="accountDetail?accountid=1" width="100%" height="600px" style="border:none;"></iframe>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="../UI/script/adminListAccount.js"></script>
    </body>
</html>
