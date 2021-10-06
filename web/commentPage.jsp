<%-- 
    Document   : commentPage
    Created on : Sep 29, 2021, 10:53:31 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comment Section</title>
    </head>
    <body>


        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${sessionScope.USER.role != 'Mentor' || sessionScope.USER.role != 'Student'}">
                <form action="comment" method="POST">
                    <div>
                        <textarea name="content" required="">${param.content}</textarea>
                        <font color="red">
                        ${requestScope.ERROR_COMMENT}
                        </font><br/>
                        <input type="submit" value="comment">
                    </div>
                </form>
            </c:if>
        </c:if>
        <section>

            <div>
                <c:set var="dtoCmMAP" value="${requestScope.COMMENT_MAP}"/>
                <c:forEach var="dtoCm" items="${dtoCmMAP}">
                    <h4>${dtoCm.value.fullname}</h4>
                    <p>${dtoCm.key.content}</p>
                </c:forEach>
            </div>
        </section>
    </body>
</html>
