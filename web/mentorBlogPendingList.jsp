<%-- 
    Document   : mentorBlogPendingList
    Created on : Sep 27, 2021, 9:31:52 PM
    Author     : JohnnyMC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:if test="${empty sessionScope.USER}">
        <c:redirect url="/"/>
    </c:if>
    
    <c:if test="${not empty sessionScope.USER}">
        <c:if test="${sessionScope.USER.role != 'Mentor'}">
            <c:redirect url="/"/>
        </c:if>
    </c:if>
    
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mentor BlogPendingList</title>
        <h1>Mentor BlogPendingList</h1>
    </head>
<body>
    <c:set var="blogList" value="${requestScope.PENDING_BLOG_LIST}"/>
    <c:if test="${not empty blogList}">
        ${requestScope.MESSAGE}

        <table>
            <tr>
                <td>Title</td>
                <td>User</td>
                <td>Action</td>
            </tr>
            <c:forEach var="dto" items="${blogList}">
                <tr>
                <form action="mentor/blogEdit">
                    <td>${dto.title}</td>
                    <td>${dto.studentID}</td>
                    <td> <div class="btn-group">
                            <button type="button" class="btn btn-primary">Action</button>
                            <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="sr-only">Action</span>
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="blogPendingDetail?blogid=${dto.blogID}">Modify</a>
                            </div>
                        </div>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:forEach var="i" begin="1" end="${requestScope.PAGE_COUNT}">
                <c:if test="${i == param.page || ( empty param.page && i == 1)}">
                    <li class="page-item active">
                        <a class="page-link" href="#" onclick="changePage(${i})">
                            ${i}
                        </a>
                    </li>
                </c:if>

                <c:if test="${i != param.page && (not empty param.page || i != 1)}">
                    <li class="page-item">
                        <a class="page-link"href="#" onclick="changePage(${i})">
                            ${i}
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </nav>
</c:if>

<c:if test="${empty blogList}">
    <h1>There is no pending blog!</h1>
</c:if>
<script type="text/javascript" src="UI/script/mentorBlogPendingList.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
