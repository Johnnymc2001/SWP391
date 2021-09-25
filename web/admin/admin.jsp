<%-- 
    Document   : admin
    Created on : Sep 22, 2021, 10:16:58 AM
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
                    <a href="listAccount">Manage Accounts</a>
                    <iframe src="listAccount" width="100%" height="400px" style="border:none;"></iframe>
                </div>

                <div class="col-sm-12">
                    <a href="categoryManage">Manage Categories</a>
                    <iframe src="categoryManage" width="100%" height="400px" style="border:none;"></iframe>
                </div>
        </div>

    </body>
</html>
