<%-- 
    Document   : studentProfile
    Created on : Oct 24, 2021, 11:03:26 AM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <!-- this is bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->

        <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="UI/CSS/profilePageStyle.css">
        <link rel="stylesheet" href="UI/CSS/adminIframe.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <title>Profile Page</title>
    </head>
    <body>

        <c:set var="user" value="${sessionScope.USER}"/>
        <c:set var="account" value="${requestScope.ACCOUNT}"/>

        <!-- THIS IS NAVBAR -->
        <header class="navbar navbar-expand-lg" id="header-default">
            <jsp:directive.include file="navbar.jsp" /> 
        </header>
        <!-- END OF NAVBAR -->

        <c:if test="${ empty account }">
            <h1>account empty</h1>
        </c:if>
        <div class="container">
            <div class="row">
                <%--  ---------       PROFILE       ----------  --%>
                <div class="profile-container">
                    <c:if test="${user.accountID==account.accountID}">
                        <h1>${account.username}</h1> 
                        <div>
                            <form id="userProfile" action="profile" method="POST">
                                <div class="row">
                                    <input type ="hidden" name ="userID" value="${account.accountID}" readonly>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Username</label>
                                        <input type ="text" name ="username" value="${account.username}" autocomplete="off" readonly>
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Email</label>
                                        <input type ="text"  name ="email" value="${account.email}" autocomplete="off" readonly>
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Full name</label>
                                        <input type ="text" class="txt-edit" name ="fullname" value="${account.fullname}" autocomplete="off" readonly=true required>
                                        <c:if test="${not empty FULL_NAME_ERROR}">
                                            <font color="red">
                                            ${FULL_NAME_ERROR}<br/>
                                            </font>
                                        </c:if>
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Address</label>
                                        <input type ="text" class="txt-edit" name="address" value="${account.address}" autocomplete="off" readonly="true" required>
                                        <c:if test="${not empty ADDRESS_ERROR}">
                                            <font color="red">
                                            ${ADDRESS_ERROR}<br/>
                                            </font>
                                        </c:if>
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Phone</label>
                                        <input type ="text" class="txt-edit" name ="phone" value="${account.phone}" autocomplete="off" readonly="true" required>
                                        <c:if test="${not empty PHONE_ERROR}">
                                            <font color="red">
                                            ${PHONE_ERROR}<br/>
                                            </font>
                                        </c:if>
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Birth</label>
                                        <input type="date" class="txt-edit" id="birthdate" name ="birthdate" value="${account.birthday}" autocomplete="off" max="9999-12-31" readonly="true" required>
                                        <c:if test="${not empty BIRTHDATE_ERROR}">
                                            <font color="red">
                                            ${BIRTHDATE_ERROR}<br/>
                                            </font>
                                        </c:if>
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6 d-none" id="passwordTextBox1">
                                        <label >New Password</label>
                                        <input type ="password" class="txt-edit" name="password" value="" autocomplete="off" readonly="true">
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6 d-none" id="passwordTextBox2">
                                        <label>Confirm Password</label>
                                        <input type ="password" class="txt-edit" name="confirmPassword" value="" autocomplete="off" readonly="true">
                                    </div>
                                    <input type="hidden" name="btnAction" autocomplete="off" value="UpdateProfile">
                                </div>
                                <div class="edit-button">
                                    <c:if test="${not empty PASSWORD_ERROR}">
                                        <font>
                                        ${PASSWORD_ERROR}<br/>
                                        </font>
                                    </c:if>
                                    <c:if test="${not empty FULL_NAME_ERROR || not empty PASSWORD_ERROR || not empty BIRTHDATE_ERROR || not empty PHONE_ERROR || not empty ADDRESS_ERROR}">                 
                                        <p style="color:red;">
                                            Update Fail !!<br/></p>
                                    </c:if>
                                    <c:if test="${not empty UPDATE_SUCCESS}">
                                        <p style="color:green;">
                                        ${UPDATE_SUCCESS}<br/>
                                        </p>
                                    </c:if>
                                    <button type="button" class="btn-action d-inline" id="editBtn" onclick="EnableEditAndSaveProfile()">Edit Profile</button>
                                    <button type="button"class="btn-action d-none" id="saveBtn"  data-toggle="modal" data-target="#saveModal" data-backdrop="false">Save</button>
                                    <button type="button" class="btn-action d-none" id="undoBtn" data-toggle="modal" data-target="#cancelModal" data-backdrop="false">Cancel</button>

                                    <%--         Boostrap Modal         --%>

                                    <div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure you want to save your profile?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="SaveEditProfile()">Yes</button>
                                                    <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure you want to cancel?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="CancelProfile()">Yes</button>
                                                    <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div> 
                            </form>
                        </div>   
                    </c:if>
                    <c:if test="${user.accountID!=account.accountID}">
                        <div class="row">
                            <div class="enter-field col-sm-12 col-md-6">
                                <label>Full name</label>
                                <p>${account.fullname}</p>
                            </div>
                            <div class="enter-field col-sm-12 col-md-6">
                                <label>Email</label>
                                <p>${account.email}</p>  
                            </div>     
                            <div class="enter-field col-sm-12 col-md-6">
                                <label>Phone</label>
                                <p>${account.phone}</p> 
                            </div>
                            <div class="enter-field col-sm-12 col-md-6">
                                <label>Birth</label>
                                <p>${account.birthday}</p><br>  
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${(account.role == 'Student' || account.role == 'Mentor') && not empty requestScope.BLOGLIST}">
                        <div class="blog-list">
                            <h1>Blog List</h1>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>No. </th>
                                        <th>Title</th>
                                        <th>Date</th>
                                        <th>Thumbnail</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="blog" items="${requestScope.BLOGLIST}" varStatus="count">
                                        <tr>
                                            <td>${count.count}</td>
                                            <td><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></td>
                                            <td>${blog.postDate}</td>
                                            <td><a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.thumbnail}" alt=""></a></td>
                                            <td><button class="btnView"><a href="blog?txtBlogID=${blog.blogID}">View</a></button></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="./UI/script/profile.js"></script>
    </body>
</html>
