<%-- 
    Document   : user-view
    Created on : Mar 18, 2023, 10:30:46 AM
    Author     : YSF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User Details</h1>
        User ID: ${sessionScope.user.userid}<br/>
        User Name: ${sessionScope.user.first} ${requestScope.user.last}<br/>
        Email: ${sessionScope.user.email}<br/>
        City: ${sessionScope.user.address.city}<br/>
    </body>
</html>
