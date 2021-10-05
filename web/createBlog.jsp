<%-- 
    Document   : createNewAccount
    Created on : Jun 30, 2021, 4:28:35 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <link rel="icon" href="UI/Icon/Ficon.png" type="image/icon type">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Carattere&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="UI/CSS/createBlogPageStyle.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script> </head>
    <body>
        

        <form action="create" method="POST" enctype='multipart/form-data'>
            <div>
                <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
                Title: <br/><input type="text" value="${param.txtTitle}" name="txtTitle" maxlength="60" size="62"/> <br/>
                
                <select name="categoryBox">
                    <c:set var="dtoList" value="${requestScope.CATEGORY_LIST}"/>
                    <c:forEach var="dto" items="${dtoList}" varStatus="counter">
                        <option value="${dto.categoryID}">${dto.categoryName}</option>
                    </c:forEach>
                </select>
                    
                <font color="red">
                ${requestScope.ERROR_TITLE}
                </font><br/>

                Thumbnail: <br/><input type="file" id="attachment" name="fileAttachment">
                <font color="red">
                ${requestScope.ERROR_UPLOAD}
                </font><br/>
            </div>
            <br/>
            <textarea id="summernote" name="txtContent"></textarea>
            <script>
                $('#summernote').summernote({
                    placeholder: 'Hello Bootstrap 4',
                    tabsize: 2,
                    height: 300
                });
            </script>

            <br/>
            <font color="red">
            ${requestScope.ERROR_CONTENT}
            </font><br/>

            <input type="submit" value="Post"/>
            <input type="reset" value="Reset" /><br/>
            <a href="home">Return to Home</a>
        </form>
    </body>
</html>
