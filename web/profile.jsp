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
        <link rel="stylesheet" href="UI/CSS/profilePageStyle.css">
        <link rel="stylesheet" href="UI/CSS/adminIframe.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
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
        <div class="container-fluid">
            <div class="row">
                <div class="d-none d-md-block col-md-3">
                    <ul class="option-table list-group">
                        <div class="user-avatar">
                            <h2>Student</h2>
                        </div>
                        <div class="user-option">
                            <a href="blogPendingList">
                                <li class="list-group-item active">Profile</li>
                            </a>
                        </div>
                        <div class="public-option">
                            <a href="home">
                                <li>Home</li>
                            </a>
                            <a href="search">
                                <li>Search</li>
                            </a>
                            <a href="">
                                <li>Contact</li>
                            </a>
                        </div>
                        <a href="logout">
                            <li>Log out</li>
                        </a>
                    </ul>
                </div>
                <div class="d-sm-none d-md-block d-lg-none col-md-1">

                </div>
                
                <%--  ---------       PROFILE       ----------  --%>
                <div class="col-md-8 profile-container">
                    <c:if test="${user.accountID==account.accountID}">
                        <h1>${account.username}</h1> 
                        <div>
                            <form action="profile" method="POST">
                                <div class="row">
                                    <input type ="hidden" name ="userID" value="${account.accountID}" readonly>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Username</label>
                                        <input type ="text" name ="username" value="${account.username}" readonly>
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Full name</label>
                                        <input type ="text" class="txt-edit" name ="fullname" value="${account.fullname}" readonly=true>
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Address</label>
                                        <input type ="text" class="txt-edit" name="address" value="${account.address}" readonly="true">
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Password</label>
                                        <input type ="password" class="txt-edit" name ="password" value="${account.password}" readonly="true">
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Email</label>
                                        <input type ="text"  name ="email" value="${account.email}" readonly>
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Phone</label>
                                        <input type ="text" class="txt-edit" name ="phone" value="${account.phone}" readonly="true">
                                    </div>
                                    <div class="enter-field col-sm-12 col-md-6">
                                        <label>Birth</label>
                                        <input type="date" class="txt-edit" id="birthdate" name ="birthdate" value="${account.birthday}" readonly="true"></br>
                                    </div>
                                </div>
                                <div class="edit-button">
                                    <button type="button" class="btn-action d-inline" id="editBtn" onclick="EnableEditAndSave()">Edit Profile</button>
                                    <button type="button" class="btn-action d-none" id="saveBtn" type="submit" name ="btAction" value="UpdateProfile" onclick="SaveEdit()">Save</button>
                                    <button type="button" class="btn-action d-none" id="undoBtn" onclick="Cancel()">Cancel</button>
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
                                <p>${account.email}</p> 
                            </div>
                            <div class="enter-field col-sm-12 col-md-6">
                                <label>Birth</label>
                                <p>${account.email}</p><br>  
                            </div>
                        </div>
                    </c:if>
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
                                <c:forEach var="blog" items="${requestScope.CURBLOG}" varStatus="count">
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
                </div>
            </div>
        </div>
        <script type="text/javascript" src="./UI/script/profile.js"></script>
    </body>
</html>
