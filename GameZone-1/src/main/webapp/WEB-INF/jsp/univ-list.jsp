<%-- 
    Document   : univ-list
    Created on : Apr 6, 2023, 5:36:15 AM
    Author     : ritvikparamkusham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>University List</title>
    </head>
    <body>
        <h1>University List</h1>
        <div class="portal-leave"> 
                <c:forEach items="${univList}" var="e">
                    <div class = "dash-info-rev">

                        <table>
                            <tr>
                                <td>University ID:</td>
                                <td class="pad-td"><c:out value="${e.getUnivId()}"/></td>
                            </tr>
                            <tr>
                                <td>University Name:</td>
                                <td class="pad-td"><c:out value="${e.getUnivName()}"/></td>
                            </tr>
                            
                        </table>
                    </div>
                </c:forEach>
            </div>
    </body>
</html>
