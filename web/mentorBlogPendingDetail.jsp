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
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Edit Page</title>
    </head>
    <body>
        <c:set var="blog" value="${requestScope.BLOG}"/>
        <header class="navbar navbar-expand-lg" id="header-default">
            <jsp:directive.include file="navbar.jsp" /> 
        </header>
        <!-- END OF NAVBAR -->
        <div class="create-blog-container container-fluid"> 
            <form name="formPendingBlog" id="pendingBlog" action="blogPendingDetail" method="POST" accept-charset="utf-8">
                <input type="hidden" name="blogid" value="${blog.blogID}">
                <div class="row">
                    <div class="thumbnail-area">
                        <span>Thumbnail: </span>
                        <div>
                            <input type="hidden" value="${blog.thumbnail}" id="txtImageUrl" name="txtImageUrl"/>
                            <img class="d-block" id="imgReview" src="${blog.thumbnail}" alt="Student Thumbnail" />
                        </div>
                        <br>
                        <button type="button" class="btn-action d-none" id="deleteThumnailBtn" onclick="deleteThumbnail()">Default</button>
                        <button type="button" class="btn-action d-none" id="undoDeleteBtn" onclick="undoDeleteThumbnail()">Undo</button>
                        <!--<button type="submit"  name="submitAction" value="ChangeThumbnail">Change Image</button>-->                   
                    </div> 
                    <div class="title-area">
                        <span>Title: </span>
                        <br>
                        <h1 class="d-block" id="blog-title-h1">${blog.title}</h1>
                        <input class="d-none" id="blog-title-input" type="text" value="${blog.title}" autocomplete="off" name="title"/>
                    </div> 
                    <font color="orange">
                    ${requestScope.ERROR_TITLE}<br/>
                    </font>

                    <div class="col-lg-8">  
                        <div class="edit-button">
                            <button type="button" class="btn-action d-inline" id="editBtn" onclick="EnableEditAndSave()">Edit</button>
                            <button type="button" class="btn-action d-none" id="saveBtn" onclick="SaveEdit()">Save</button>
                            <button type="button" class="btn-action d-none" id="undoBtn" onclick="Undo()">Undo</button>
                        </div> 
                        <br>
                        <div class="blog-view d-block" id="blog-view">
                            ${blog.content}
                        </div>
                        <div id="user-write" class="user-write d-none">
                            <font color="orange">
                            ${requestScope.MESSAGE}
                            </font>
                            <textarea id="summernote" name="content"></textarea>
                        </div>
                    </div>
                    <div class="right-colum col-lg-4">
                        <div class="row justify-content-center">
                            <div class="user-footer">
                                <button class="btn-action d-none" id="updateBtn" type="submit" name="submitAction" value="Update">Update</button>
                                <button class="btn-action" id="approveBtn" type="button"  data-toggle="modal" data-target="#approveModal" data-backdrop="false">Approve</button>
                                <button class="btn-action deactive" id="disapproveBtn" type="button" data-toggle="modal" data-target="#dispproveModal" data-backdrop="false">Disapprove</button>
                            </div>
                            <div class="d-inline" id="note">
                                <h1>Note:</h1>
                                <textarea class="form-control" name="note" rows="10"></textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <%--         Boostrap Modal for update buttons         --%>

                <div class="modal fade" id="approveModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Approve this blog?
                            </div>
                            <div class="modal-footer">
                                <button  type="button" class="btn btn-secondary" data-dismiss="modal" onclick="Approve()">Yes</button>
                                <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="dispproveModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Disapprove this blog?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="Disapprove()">Yes</button>
                                <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--         End for Boostrap Modal for update buttons         --%> 

                <a href="blogPendingList">Return</a>
            </form>

            <!-- We need CSS for 3 of this button. -->

            <!-- FOOTER -->
            <div class="web-footer">
                <p>&copy; 2021 Henry. FE by Henry</p>
                <button onclick="goTop()">Back to top</button>
            </div>

        </div>
        <!--this is Js-->

        <script>
            const blogContent = `${blog.content}`;
        </script>
        <script type="text/javascript" src="./UI/script/summernote.js"></script>
        <script type="text/javascript" src="./UI/script/mentorBlogPendingDetail.js"></script>

    </body>
</html>
