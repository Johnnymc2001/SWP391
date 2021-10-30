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
        <title>Home Page</title>
        <link rel="icon" href="UI/Icon/Ficon.png" type="image/icon type">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Carattere&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="UI/CSS/homePageStyle.css">
        <link rel="stylesheet" href="UI/CSS/adminIframe.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--this is exernal CSS-->
        <link rel="stylesheet" href="UI/CSS/navbar.css">
        <!--this is font awsome-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>


    <!-- THIS IS NAVBAR -->
    <header class="navbar navbar-expand-lg" id="header-default">
        <jsp:directive.include file="navbar.jsp" /> 
    </header>
    <!-- END OF NAVBAR -->

    <body>

        <c:set var="user" value="${sessionScope.USER}"/>
        <div class="container award-container">
            <div>
                <button class="btn-action" onclick="ShowCreateAward()" >Create an Award</button>
                <div id="CreateAward" class="d-none">
                    <form action="awardCreate" method="POST">
                        <div class="enter-field">
                            <span>Award Name: </span>
                            <input type="textAward" value="" name="txtAwardName" maxlength="60" size="62" required/></br>
                        </div>
                        <div class="enter-field">
                            <span>Effective Days: </span>
                            <input type="textAward" value="" name="txtEffectiveDays" maxlength="5" size="5" required/>
                        </div>
                        <br>
                        <button class="btn-action" type="submit"  value="Create Award">Create Award</button>
                    </form>
                </div>
            </div>

            <div>

                <c:set var="awardDtoList" value="${requestScope.ALL_AWARD}"/>
                <c:set var="blogInfo" value="${requestScope.BLOG}"/>
                <c:set var="blogAwardList" value="${requestScope.BLOGAWARD}" />
                <c:if test="${not empty blogInfo}">
                    <form action="award?txtBlogID=${blogInfo.blogID}" method="POST">
                        <span>Blog: ${blogInfo.title}</span><br>
                        <input type="hidden" name="txtBlogID" value="${blogInfo.blogID}"/>
                        <select name="txtAwardID">
                            
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

                        <input type="submit" name="btnAction" value="Award Blog"/>
                    </form>
                </c:if>
            </div>

        </div>
        <c:if test="${not empty requestScope.BLOGAWARD}">

            <div class="">
                <h1>Blog's Award List</h1>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>No. </th>
                            <th>Title</th>
                            <th>Date</th>
                            <th>Award By</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <c:set var="mentorList" value="${requestScope.MENTORLIST}" />
                        <c:forEach var="blogAward" items="${blogAwardList}" varStatus="count">
                            <tr>
                                <td>${count.count}</td>
                                <c:forEach var="awardDto" items="${awardDtoList}">
                                    <c:if test="${awardDto.awardID==blogAward.awardID}">
                                        <td>${awardDto.awardName}</td>
                                    </c:if>
                                </c:forEach>
                                <td>${blogAward.date}</td>
                                <c:forEach var="mentorDto" items="${mentorList}">
                                    <c:if test="${mentorDto.accountID==blogAward.awardBy}">
                                        <td>${mentorDto.fullname}</td>
                                    </c:if>
                                </c:forEach>

                                <td><a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.thumbnail}" alt=""></a></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>


    </body>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
    </script>

    <script type="text/javascript" src="./UI/script/mentorAwardDetail.js"></script>



</html>
