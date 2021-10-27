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
            <!--<button class="btn-action d-none" type="submit" name ="btAction" value="UpdateProfile">Update Profile</button>-->
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

<script type="text/javascript" src="./UI/script/studentProfile.js"></script>

