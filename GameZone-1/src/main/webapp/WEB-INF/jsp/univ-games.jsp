<%-- 
    Document   : univ-games
    Created on : Apr 6, 2023, 1:07:56 AM
    Author     : ritvikparamkusham
--%>

<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Add Universities and Games</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Add Universities and Games</h1>
        <form method="post" action="/GameZone/admin/adduniv.htm">
            <h2>Add University</h2>
            <label for="universityName">University Name:</label>
            <input type="text" id="universityName" name="universityName" required>
            <br><br>
            <input type="submit" value="Add University">
        </form>
        
        <form method="POST" action="/GameZone/admin/addgames.htm">
            <br><br>
            <hr>
            <h2>Add Game</h2>
            <label for="gameName">Game Name:</label>
            <input type="text" id="gameName" name="gameName" required>
            <br><br>
            <label for="gameDescription">Game Description:</label>
            <textarea id="gameDescription" name="gameDescription" required></textarea>
            <br><br>
            <label for="universityId">University:</label>
            <select name="univId">
                <c:forEach var="e" items="${univList}">
                    <option value="${e.getUnivId()}">${e.getUnivName()}</option>
                </c:forEach>
            </select>
            <br><br>
            <label for="gameSlots">Game Slots:</label>
            <textarea id="gameSlots" name="availSlots" required></textarea>
            <br><br>
            <label for="location">Game Location:</label>
            <textarea id="location" name="location" required></textarea>
            <br><br>
            <input type="submit" value="Add Game">
        </form>
    </body>
</html>
