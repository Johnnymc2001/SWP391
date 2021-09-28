<%-- 
    Document   : mentor
    Created on : Sep 27, 2021, 7:14:43 AM
    Author     : JohnnyMC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Admin Dashboard</h1>
        <div class="container-fluid">
            <div class="col-sm-12">
                <a href="blogPending">Manage Blog Pending</a>
                <iframe src="blogPending" width="100%" height="600px" style="border:none;"></iframe>
            </div>

            <div class="col-sm-12">
                <a href="awardList">Manage Award</a>
                <iframe src="awardList" width="100%" height="400px" style="border:none;"></iframe>
            </div>
            
        </div>

    </body>
</html>
