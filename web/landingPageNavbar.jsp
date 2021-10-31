<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="home"><img src="UI/Icon/FPTLogo.jpg" alt="">FPT Academy</a>
        <button class="navbar-toggler" type="button">
            <span class="navbar-toggler-icon">
                <div class="menu-toggler"></div>
            </span>
        </button>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="about">Introduce</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="about">About</a>
                </li>
                <li class="nav-item">
                <c:if test="${empty user}">
                    <a class="nav-link btn-link" aria-current="page" href="registerPage">
                        <button class="btn btn-register">Join us</button>
                    </a>
                </c:if>
                <c:if test="${not empty user}">
                    <a class="nav-link user-name" aria-current="page" href="profile">${user.fullname}</a>
                </c:if>
                </li>
            </ul>
        </div>
        <div class="left-nav">
            <ul class="-left-navbar-nav">
                <li class="left-nav-item">
                    <a class="navbar-brand" href="home"><img src="UI/Icon/FPTLogo.jpg" alt="">FPT Academy</a>
                </li>
                <li class="left-nav-item">
                    <a class="nav-link" aria-current="page" href="home">Home</a>
                </li>
                <li class="left-nav-item">
                    <a class="nav-link" aria-current="page" href="about">Introduce</a>
                </li>
                <li class="left-nav-item">
                    <a class="nav-link" aria-current="page" href="about">About</a>
                </li>
                <li class="left-nav-item">
                <c:if test="${empty user}">
                    <a class="nav-link btn-link" aria-current="page" href="registerPage">
                        <button class="btn btn-register">Join us</button>
                    </a>
                </c:if>
                <c:if test="${not empty user}">
                    <a class="nav-link user-name" aria-current="page" href="profile">${user.fullname}</a>
                </c:if>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- ENF OF NAVBAR -->