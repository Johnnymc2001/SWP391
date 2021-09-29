<%-- 
    Document   : notification
    Created on : Sep 29, 2021, 6:12:38 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Notification</title>
    </head>
    <body>
     

        <c:set var="noti"  value="${requestScope.LIST_NOTIFICATION}" />
           <c:if test="${not empty noti}">
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>

                    <th>Content</th>
                </tr>

                <c:forEach items="${noti}"  var="dto" varStatus="counter">

                    <tr>
                        <td> ${counter.count}</td>
                        <td> ${dto.content}</td>

                    </tr>  
                  </c:forEach>
                </thead>
            </table>
                  </c:if>      
        <c:if test="${ empty noti}">
              <h2>
                There is no Notification
            </h2>
        </c:if>
        </body>
    </html>
