<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<div class="header-left">
    <!-- site logo -->
    <div class="site-logo">
        <a href="home"><img src="UI/Icon/FPTLogo.jpg" alt="logo"></a>
        <a href="home">FPT Academy</a>
    </div>
    <!-- navigate options -->
    <div class="collapse navbar-collapse options-btn">

        <a href="home">
            <button class="btn
                    <c:if test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], 'home')}">
                        active-btn
                    </c:if>
                    <c:if test="${!fn:contains(requestScope['javax.servlet.forward.request_uri'], 'home')}">
                        btn
                    </c:if>
                    ">
                Home
            </button>
        </a>
        <a href="search?txtSearchType=popular">
            <button class="btn
                    <c:if test="${(fn:contains(requestScope['javax.servlet.forward.request_uri'], 'search') && param.txtSearchType == 'popular')}">
                        active-btn
                    </c:if>
                    <c:if test="${!(fn:contains(requestScope['javax.servlet.forward.request_uri'], 'search') && param.txtSearchType == 'popular')}">
                        btn
                    </c:if>">
                Popular
            </button>
        </a>
        <a href="search?txtSearchType=recent">
            <button class="
                    <c:if test="${(fn:contains(requestScope['javax.servlet.forward.request_uri'], 'search') && param.txtSearchType == 'recent')}">
                        active-btn
                    </c:if>
                    <c:if test="${!(fn:contains(requestScope['javax.servlet.forward.request_uri'], 'search') && param.txtSearchType == 'recent')}">
                        btn
                    </c:if>
                    ">
                Recent
            </button>
        </a>
        <a href="about">
            <button class="
                    <c:if test="${(fn:contains(requestScope['javax.servlet.forward.request_uri'], 'aboutUs.jsp'))}">
                        active-btn
                    </c:if>
                    <c:if test="${!(fn:contains(requestScope['javax.servlet.forward.request_uri'], 'aboutUs.jsp'))}">
                        btn
                    </c:if>"
                    >
                About
            </button>
        </a>
    </div>
</div>
<div class="header-right">
    <div class="search-btn">
        <form action="search" method="POST">
            <input type="hidden" name="txtSearchValue">
            <button type="submit" class="search-button"><i class="fas fa-search"></i></button>
        </form>
    </div>

    <c:if test="${not empty user}">

        <button type="button" class="menu-button dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
            ${user.fullname}
        </button>
        <c:if test="${user.role == 'Student'}">
            <ul class="dropdown-menu dropdown-menu-end">
                <!-- this is user img -->
                <div class="avatar">
                    <img src="UI/Icon/profile-icon.png" alt="avatar">
                    <p>${user.fullname}</p>
                </div>
                <!-- personal menu -->
                <div class="personal-menu">
                    <a href="profile"><li>Profile</li></a>
                    <a href="create"><li>Create Blog</li></a>
                    <a href="blogList"><li>Blog List</li></a>
                    <a href="logout"><li>Log out</li></a>
                </div>
            </c:if>
            <c:if test="${user.role == 'Mentor'}">
                <ul class="dropdown-menu dropdown-menu-end">
                    <!-- this is user img -->
                    <div class="avatar">
                        <img src="UI/Icon/maleteacher-icon.png" alt="avatar">
                        <p>${user.fullname}</p>
                    </div>
                    <!-- personal menu -->
                    <div class="personal-menu">
                        <a href="profile"><li>Profile</li></a>
                        <a href="create"><li>Create Blog</li></a>
                        <a href="blogPendingList"><li>Pending Blog</li></a>
                        <a href="blogList"><li>Blog List</li></a>
                        <a href="logout"><li>Log out</li></a>
                    </div>
                </c:if>
                <c:if test="${user.role == 'Admin'}">
                    <ul class="dropdown-menu dropdown-menu-end">
                        <!-- this is user img -->
                        <div class="avatar">
                            <img src="UI/Icon/maleteacher-icon.png" alt="avatar">
                            <p>${user.fullname}</p>
                        </div>
                        <!-- personal menu -->
                        <div class="personal-menu">
                            <a href="admin"><li>Dashboard</li></a>
                            <a href="logout"><li>Log out</li></a>
                        </div>
                    </c:if>
                    <!-- public menu -->
                    <div class="public-menu">
                        <a href="home"><li>Home</li></a>
                        <a href="search?txtSearchType=recent"><li>Search</li></a>
                        <a href="about"><li>About</li></a>
                    </div>
                </ul>
                <div class="notify">
                    <button type="button" class="notify-button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-bell"></i>
                    </button>
                    <ul class="notify-box dropdown-menu dropdown-menu-end">
                        <iframe class="notify-window" src="notification" frameborder="0"></iframe>
                    </ul>
                </div>
            </c:if>
            <c:if test="${empty user}">
                <a href="loginPage">Login</a>
            </c:if>
            </div>
