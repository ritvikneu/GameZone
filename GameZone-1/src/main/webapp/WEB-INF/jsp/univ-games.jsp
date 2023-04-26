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
    <style>
        /* Add your CSS styles here */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f7f7f7;
        }

        h1, h2 {
            color: #333;
        }

        form {
            background-color: #fff;
            padding: 20px;
            margin-top: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #666;
        }

        input[type="text"], textarea, select {
            width: 50%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        hr {
            margin-top: 30px;
            margin-bottom: 30px;
            border: none;
            border-top: 1px solid #ccc;
        }
    </style>
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
        <h2>Add Game To University</h2>
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
        <input type="number" id="gameSlots" name="availSlots" required>
        <br><br>
        <label for="location">Game Location:</label>
        <textarea id="location" name="location" required></textarea>
        <br><br>
        
        <input type="submit" value="Add Game">
    </form>
</body>
</html>
