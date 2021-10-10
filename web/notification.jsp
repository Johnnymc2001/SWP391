<%-- Document : notification Created on : Sep 29, 2021, 6:12:38 PM Author :
Sammy Guergachi
<sguergachi at gmail.com>
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib
    uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <link rel="stylesheet" href="UI/CSS/NewnotifyPage.css">
            <!-- this is fontawsome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <body>
            <img src="UI/Icon/FPTLogo.jpg" alt="FPTLogo" class="Ficon" />

            <c:set var="noti" value="${requestScope.LIST_NOTIFICATION}" />
            <c:if test="${not empty noti}">
                <div class="new">
                    <h4>Recent</h4>
                    <li>
                        <a href="">
                            <i class="notify-icon fas fa-star fa-lg"></i>
                            <div class="notify-content">
                                <span class="title">Awarded Blog Title</span><br>
                                <span class="date">11/10/2001</span>
                            </div>
                            <div class="notify-status">
                                <i class="fas fa-circle fa-xs"></i>
                            </div>
                        </a>
                    </li>
                </div>
                <div class="old">
                    <h4>Before</h4>
                    <li>
                        <a href="">
                            <i class="notify-icon fas fa-comments fa-lg"></i>
                            <div class="notify-content">
                                <span class="title">Blog Title get commented</span><br>
                                <span class="date">11/10/2001</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="notify-icon fas fa-edit fa-lg"></i>
                            <div class="notify-content">
                                <span class="title">Blog Title get edited</span><br>
                                <span class="date">11/10/2001</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="notify-icon fas fa-thumbs-up fa-lg"></i>
                            <div class="notify-content">
                                <span class="title">Approved Blog Title</span><br>
                                <span class="date">11/10/2001</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="notify-icon fas fa-thumbs-down fa-lg"></i>
                            <div class="notify-content">
                                <span class="title">Disapproved Blog Title</span><br>
                                <span class="date">11/10/2001</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="notify-icon fas fa-thumbs-down fa-lg"></i>
                            <div class="notify-content">
                                <span class="title">Disapproved Blog Title</span><br>
                                <span class="date">11/10/2001</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="notify-icon fas fa-thumbs-down fa-lg"></i>
                            <div class="notify-content">
                                <span class="title">Disapproved Blog Title</span><br>
                                <span class="date">11/10/2001</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="notify-icon fas fa-thumbs-down fa-lg"></i>
                            <div class="notify-content">
                                <span class="title">Disapproved Blog Title</span><br>
                                <span class="date">11/10/2001</span>
                            </div>
                        </a>
                    </li>
                </div>
            </c:if>
            <c:if test="${ empty noti}">
                <h2>There is no Notification</h2>
            </c:if>
        </body>
    </html>
</sguergachi>
