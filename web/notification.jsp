<%-- Document : notification Created on : Sep 29, 2021, 6:12:38 PM Author :
Sammy Guergachi
<sguergachi at gmail.com>
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Notify</title>
            <!-- this is bootstrap -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                  integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
            </script>
            <!-- this is external css -->
            <link rel="stylesheet" href="UI/CSS/notifyPageStyle.css">
            <!-- this is fontawsome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <body>

            <c:set var="notificationList" value="${requestScope.LIST_NOTIFICATION}" />
            <c:if test="${not empty notificationList}">
                <c:forEach var="dto" items="${notificationList}">
                <li onclick=" window.parent.location.href = `${dto.redirectUrl}`">
                    <a>
                        <i class="notify-icon fas fa-star fa-lg"></i>
                        <div class="notify-content">
                            <span class="title">${dto.content}</span><br>
                            <span class="date">${dto.date}</span>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </c:if>
        <c:if test="${empty notificationList}">
            <h2>There is no Notification</h2>
        </c:if>
    </body>


</html>
</sguergachi>
