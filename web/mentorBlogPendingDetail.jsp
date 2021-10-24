<%-- 
    Document   : mentor
    Created on : Sep 27, 2021, 7:14:43 AM
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
    <c:set var="user" value="${sessionScope.USER}"/>
    <head>
        <!-- this is bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="UI/CSS/createBlogPageStyle.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <!-- this is external JS -->
        <!--this is for summernote-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
        <script type="text/javascript" src="./UI/script/mentorBlogPendingDetail.js"></script>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Edit Page</title>
    </head>
    <body>
        <c:set var="blog" value="${requestScope.BLOG}"/>
        <header class="navbar navbar-expand-lg" id="header-default">
            <jsp:directive.include file="navbar.jsp" /> 
        </header>
        <!-- END OF NAVBAR -->
        <div class="view-blog-container">
            <div class="blog-detail"></div>
        </div>
        <div class="edit-button">
            <button class="btn-action" id="editBtn" onclick="EnableEditAndSave();">Edit</button>
            <button class="btn-action" id="saveBtn" onclick="SaveEdit();" style="display: none;">Save</button>
            <button class="btn-action" id="undoBtn" onclick="Undo();" style="display: none;">Undo</button>
        </div>

        <div class="create-blog-container container">
            <form action="blogPendingDetail" method="POST" accept-charset="utf-8">
                <input type="hidden" name="blogid" value="${blog.blogID}">

                <div class="title-area">
                    <span>Title: </span>
                    <br>
                    <input type="text" value="${blog.title}" name="title"/>
                </div> 
                <font color="orange">
                ${requestScope.ERROR_TITLE}<br/>
                </font>
                <div class="thumbnail-area">
                    <span>Thumbnail: </span>
                    <button type="button" class="btn-action" id="deleteThumnailBtn" onclick="deleteThumbnail()">Default</button>
                    <button type="button" class="btn-action" id="undoDeleteBtn" onclick="undoDeleteThumbnail()">Undo</button>
                    <br/>
                    <input type="hidden" value="${blog.thumbnail}" id="txtImageUrl" name="txtImageUrl"/>
                    <img id="imgReview" src="${blog.thumbnail}" alt="Student Thumbnail" />
                    <!--<button type="submit"  name="submitAction" value="ChangeThumbnail">Change Image</button>-->

                </div>

                <div class="blog-view" id="blog-view">
                    ${blog.content}
                </div>

                <div class="user-write">
                    <font color="orange">
                    ${requestScope.MESSAGE}
                    </font>
                    <textarea id="summernote" name="content"></textarea>
                </div>

                <div class="user-footer">
                    <button class="btn-action" id="updateBtn" type="submit" name="submitAction" value="Update" style="display: none;">Update</button>
                    <textarea class="form-control" name="note" rows="7"></textarea>
                    <button class="btn-action" id="approveBtn" type="submit" name="submitAction" value="Approve">Approve</button>
                    <button class="btn-action" id="disapproveBtn" type="submit" name="submitAction" value="Disapprove">Disapprove</button>
                </div>
                <a href="blogPendingList">Return</a>
            </form>

            <!-- We need CSS for 3 of this button. -->

        </div>
        <script>
            const blogContent = `${blog.content}`;
        </script>
        <script type="text/javascript" src="./UI/script/summernote.js"></script>
        <script type="text/javascript" src="./UI/script/mentorBlogPendingDetail.js"></script>

    </body>
</html>
