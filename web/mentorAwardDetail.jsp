<%-- 
    Document   : mentor Award Detail
    Created on : Oct 21, 2021, 1:16:50 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Award Page</title>
        <!--this is page icon-->
        <link rel="icon" href="UI/Icon/Ficon.png" type="image/icon type">
        <!--this is bs-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!--this is ggle font-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Carattere&display=swap" rel="stylesheet">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--this is exernal CSS-->
        <link rel="stylesheet" href="UI/CSS/homePageStyle.css">
        <link rel="stylesheet" href="UI/CSS/adminIframe.css">
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!--this is font awsome-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>

    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <!-- THIS IS NAVBAR -->
        <header class="navbar navbar-expand-lg" id="header-default">
            <jsp:directive.include file="navbar.jsp" /> 
        </header>
        <!-- END OF NAVBAR -->

        <c:set var="blogInfo" value="${requestScope.BLOG}"/>
        <div class="container-fluid award-container">
            <div class="title">
                <c:set var="awardDtoList" value="${requestScope.ALL_AWARD}"/>
                <c:set var="blogAwardList" value="${requestScope.BLOGAWARD}" />
                <c:if test="${not empty blogInfo}">
                    <form action="award?txtBlogID=${blogInfo.blogID}" method="POST">
                        <h1>Blog: ${blogInfo.title}</h1>
                        <input type="hidden" name="txtBlogID" value="${blogInfo.blogID}"/>
                        <select class="select-award" name="txtAwardID">
                            <c:forEach var="dto" items="${awardDtoList}">
                                <c:set var="contain" value="${false}"/>
                                <c:forEach var="blogAward" items="${blogAwardList}">
                                    <c:if test="${blogAward.awardID==dto.awardID}"> 
                                        <c:set var="contain" value="${true}"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${contain!=true}">
                                    <option value="${dto.awardID}">${dto.awardName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <input class="btn-action" type="submit" name="btnAction" value="Award Blog"></input>
                    </form>
                </c:if>
            </div>
                
                <div><a href="blog?txtBlogID=${blogInfo.blogID}">Go back to blog</a></div>
                
            <div class="create-award col-md-6">
                <c:set var="awardNameExist" value="${requestScope.ERROR_AWARD_NAME}"/>
                <c:if test="${not empty awardNameExist}">
                    <font color="red">
                    <p>${awardNameExist}</p>
                    </font>
                </c:if>
                <button id="award-btn" class="btn-action" onclick="ShowCreateAward()" >Create an Award</button>
                <div id="CreateAward" class="d-none">
                    <form action="award?txtBlogID=${blogInfo.blogID}" method="POST">
                        <div class="enter-field">
                            <span>Award Name: </span>
                            <input type="textAward" value="" name="txtAwardName" maxlength="60" size="62" required/></br>
                        </div>
                        <div class="enter-field">
                            <span>Effective Days: </span>
                            <input type="textAward" value="" name="txtEffectiveDays" maxlength="5" size="5" required/>
                        </div>
                        <br>
                        <input class="btn-action" type="submit" name="btnAction" value="Create Award"></input>
                    </form>
                </div>
            </div>

            <div class="award-table">
                <c:if test="${not empty requestScope.BLOGAWARD}">
                    <h3>Blog's Award List</h3>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>No. </th>
                                <th>Title</th>
                                <th>Date</th>
                                <th>Award By</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="mentorList" value="${requestScope.MENTORLIST}" />
                            <c:forEach var="blogAward" items="${blogAwardList}" varStatus="count">
                            <form action="award?txtBlogID=${blogInfo.blogID}" method="POST">
                                <tr>
                                <input type="hidden" name="txtAwardListID" value="${blogAward.awardListID}"/>
                                <td>${count.count}</td>
                                <c:forEach var="awardDto" items="${awardDtoList}">
                                    <c:if test="${awardDto.awardID==blogAward.awardID}">
                                        <td class="award-name">${awardDto.awardName}</td>
                                    </c:if>
                                </c:forEach>
                                <td>${blogAward.date}</td>
                                <c:forEach var="mentorDto" items="${mentorList}">
                                    <c:if test="${mentorDto.accountID==blogAward.awardBy}">
                                        <td>${mentorDto.fullname}</td>
                                    </c:if>
                                </c:forEach>

                                <td><input class="btn-action" type="submit" name="btnAction" value="Remove Award"></input></td>

                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </body>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous">
    </script>
    <script type="text/javascript" src="./UI/script/mentorAwardDetail.js"></script>



</html>
