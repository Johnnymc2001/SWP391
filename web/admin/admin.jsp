<%-- 
    Document   : admin
    Created on : Sep 22, 2021, 10:16:58 AM
    Author     : JohnnyMC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <!-- this is bootstrap 5-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
        <!-- this is external css -->
        <link rel="stylesheet" href="../UI/CSS/adminPage.css">
        <!-- this is fontawsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <!-- this is script -->
        <script src="../UI/script/admin.js"></script>
    </head>
    <body>
    <c:set var="user" value="${sessionScope.USER}"/>
    <header class="navbar navbar-expand-lg" id="header-default">
        <div class="header-left">
            <!-- site logo -->
            <div class="site-logo">
                <a href="../home"><img src="../UI/Icon/FPTLogo.jpg" alt="logo"></a>
                <a href="../home">FPT Academy</a>
            </div>
            <!-- navigate options -->
            <div class="collapse navbar-collapse options-btn">
                <a href="../home"><button class="active-btn">Home</button></a>
                <a href="../search?txtSearchType=popular"><button class="">Popular</button></a>
                <a href="../search?txtSearchType=recent"><button class="">Recent</button></a>
                <a href="../aboutUs.html"><button class="">About</button></a>
            </div>
        </div>
        <div class="header-right">
            <div class="search-btn">
                <form action="search" method="POST">
                    <input type="hidden" name="txtSearchValue">
                    <button type="submit" class="search-button"><i class="fas fa-search"></i></button>
                </form>
            </div>
            <button type="button" class="menu-button dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                ${user.fullname}
            </button>
            <ul class="dropdown-menu dropdown-menu-end">
                <!-- this is user img -->
                <div class="avatar">
                    <img src="../UI/Icon/maleteacher-icon.png" alt="avatar">
                    <p>${user.fullname}</p>
                </div>
                <!-- personal menu -->
                <div class="personal-menu">
                    <a href="admin/dashboard"><li>Dashboard</li></a>
                    <a href="admin/accountList"><li>Manage Accounts</li></a>
                    <a href="logout"><li>Log out</li></a>
                </div>
                <!-- public menu -->
                <div class="public-menu">
                    <a href="home"><li>Home</li></a>
                    <a href="search"><li>Search</li></a>
                    <a href="about"><li>About</li></a>
                    <a href="contact"><li>Contact</li></a>
                </div>
            </ul>
            <div class="notify">
                <button type="button" class="notify-button" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="fas fa-bell"></i>
                    <span class="num">1</span>
                </button>
                <ul class="notify-box dropdown-menu dropdown-menu-end">
                    <iframe class="notify-window" src="notification" frameborder="0"></iframe>
                </ul>
            </div>
        </div>
    </header>
    <!-- END OF NAVBAR -->

    <div class="container-fluid">
        <div class="row"> 
            <div class="d-sm-block d-md-none">
                <div class="user-option-mobile row">
                    <li class="col list-group active " onclick="activePage(this)">Accounts List</li>
                    <li class="col list-group" onclick="activePage(this)">Manage Category</li>
                    <li class="col list-group" onclick="activePage(this)">New Account</li>
                </div>
            </div>
            <div class="d-none d-md-block d-lg-block col-md-3">
                <ul class="option-table list-group">
                    <div class="user-avatar">
                        <h2>Admin</h2>
                    </div>
                    <div class="user-option">
                        <li class="list-group active" onclick="activePage(this)">Accounts List</li>
                        <li class="list-group" onclick="activePage(this)">Manage Category</li>
                        <li class="list-group" onclick="activePage(this)">New Account</li>
                    </div>
                    <div class="public-option">
                        <a href="">
                            <li>Home</li>
                        </a>
                        <a href="">
                            <li>Search</li>
                        </a>
                        <a href="">
                            <li>Contact</li>
                        </a>
                    </div>
                    <a href="">
                        <li>Log out</li>
                    </a>
                </ul>
            </div>
            <div class="d-sm-none d-md-block d-lg-none col-md-1">

            </div>
            <div class="col-md-8">
                <iframe id="iframe-window" src="accountList"></iframe>
            </div>
        </div>
    </div>

</body>
</html>
