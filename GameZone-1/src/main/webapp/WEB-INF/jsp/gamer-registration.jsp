<%-- 
    Document   : user-registraion
    Created on : Apr 6, 2023, 12:24:57 AM
    Author     : ritvikparamkusham
--%>

<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Gamer Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        <h1>Gamer Registration</h1>

        <form onsubmit="return validateForm()" method="post" action="/GameZone/gamer/home.htm">
          
            <label for="gamerName">Gamer Name:</label>
            <input type="text" id="gamerName" name="gamerName" required>
            <br><br>
            <label for="universityId">University:</label>
            <select name="univId">
                <c:forEach var="e" items="${univList}">
                    <option value="${e.getUnivId()}">${e.getUnivName()}</option>
                </c:forEach>
            </select>
            <br><br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <br><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br><br>
            <input type="submit" value="Register">
            <a href="admin/adduniv.htm" target="contents">Register Univ</a><br>
        </form>

        <script>
            function validateForm() {
                var gamerId = document.getElementById("gamerId").value;
                var gamerName = document.getElementById("gamerName").value;
                var university = document.getElementById("university").value;
                var email = document.getElementById("email").value;
                var password = document.getElementById("password").value;
                var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // regular expression for email validation

                if (isNaN(gamerId) || gamerId < 1) {
                    alert("Gamer ID must be a positive integer.");
                    return false;
                }

                if (gamerName.length < 3) {
                    alert("Gamer Name must be at least 3 characters long.");
                    return false;
                }

                if (university.length < 3) {
                    alert("University name must be at least 3 characters long.");
                    return false;
                }

                if (!re.test(email)) {
                    alert("Invalid email address.");
                    return false;
                }

                if (password.length < 6) {
                    alert("Password must be at least 6 characters long.");
                    return false;
                }

                return true;
            }
        </script>
    </body>
</html>
