<%-- 
    Document   : edit
    Created on : Oct 3, 2021, 3:32:20 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- this is for summer note -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
        </script>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
        </script>

        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
    </head>
    <body>

        <form action="edit" method="POST" enctype='multipart/form-data'> 

            <c:set var="editBlog" value="${requestScope.BLOG_EDIT}"/>

            <input type="hidden" name ="txtBlogID" value="${editBlog.blogID}">

            Title: <br/><input type="text" value="${editBlog.title}" name="txtTitle" maxlength="60" size="62"/> <br/>
            <c:if test="${not empty ERROR_TITLE}" >
                <font color="red">
                ${requestScope.ERROR_TITLE}
                </font><br/>
            </c:if>


            <div class="user-write">
                <textarea id="summernote" name="txtContent">${editBlog.content}</textarea>
            </div>F


            <select name="categoryBox">
                <c:set var="dtoList" value="${requestScope.CATEGORY_LIST}"/>
                <c:forEach var="dto" items="${dtoList}" varStatus="counter">
                    <option value="${dto.categoryID}">${dto.categoryName}</option>
                </c:forEach>

                <c:if test="${not empty INVALID_USER}">
                    <font color="red">
                    ${requestScope.INVALID_USER}
                    </font><br/>
                </c:if>

                <input type="submit" value="Update" name="btAction"/>
                <input type="reset" value="Reset" /><br/>
                <a href="home">Return to Home</a>

                <script>
                    $('#summernote').summernote({
                        placeholder: 'Ready to share .... ',
                        tabsize: 2,
                        height: 300
                    });
                </script>

        </form> 
    </body>
</html>
