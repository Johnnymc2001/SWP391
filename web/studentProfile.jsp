<c:if test="${user.accountID==account.accountID}">
    <h1>${account.username}</h1> 
    <div>
        <form action="profile" method="POST">
            <div class="row">
                <input type ="hidden" name ="userID" value="${account.accountID}" >
                <div class="enter-field col-sm-12 col-md-6">
                    <label>Username</label>
                    <input type ="text" name ="username" value="${account.username}" >
                </div>
                <div class="enter-field col-sm-12 col-md-6">
                    <label>Full name</label>
                    <input type ="text" name ="fullname" value="${account.fullname}" >
                </div>
                <div class="enter-field col-sm-12 col-md-6">
                    <label>Address</label>
                    <input type ="text" name ="address" value="${account.address}" >
                </div>
                <div class="enter-field col-sm-12 col-md-6">
                    <label>Password</label>
                    <input type ="password" name ="password" value="${account.password}" >
                </div>
                <div class="enter-field col-sm-12 col-md-6">
                    <label>Email</label>
                    <input type ="text" name ="email" value="${account.email}" >
                </div>
                <div class="enter-field col-sm-12 col-md-6">
                    <label>Phone</label>
                    <input type ="text" name ="phone" value="${account.phone}" >
                </div>
                <div class="enter-field col-sm-12 col-md-6">
                    <label>Birth</label>
                    <input type="date" id="birthdate" name ="birthdate" value="${account.birthday}"></br>
                </div>
            </div>
            <br>
            <button class="btn-action" type="submit" name ="btAction" value="UpdateProfile">Update Profile</button>
        </form>
    </div>   
</c:if>
<c:if test="${user.accountID!=account.accountID}">
    <div>
        <!--   No Need to show Username                      
        Username <br/>
        <p>${account.username}</p> 
        -->
        Full Name<br/>
        <p>${account.fullname}</p> 
        Address<br/>
        <p>${account.address}</p>                                      
    </div>
</c:if>
<div class="blog-list">
    <h1>Blog List</h1>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Title</th>
                <th>Date</th>
                <th>Thumbnail</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="blog" items="${requestScope.CURBLOG}">
            <tr>
                <td><a href="blog?txtBlogID=${blog.blogID}">${blog.title}</a></td>
                <td>${blog.postDate}</td>
                <td><a href="blog?txtBlogID=${blog.blogID}"><img src="${blog.thumbnail}" alt=""></a></td>
                <td><button class="btnView"><a href="blog?txtBlogID=${blog.blogID}">View</a></button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>