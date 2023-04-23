<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Games List</title>
    <style>
        /* CSS for table */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
        .create-booking{
            display: none;
        }
    </style>
</head>
<body>
    <h1>Games List</h1>
    <div>
    <table>
        <tr>
            <th>Game ID</th>
            <th>Game Name</th>
            <th>Location</th>
            <th>Available Slots</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="game" items="${gamesList}">
            <tr>
                <td>${game.gameId}</td>
                <td>${game.gameName}</td>
                <td>${game.location}</td>
                <td>${game.availSlots}</td>
                <td>
                    <!-- <form action="/bookSlot" method="post"> -->
                        <input type="hidden" name="gameId" value="${game.gameId}">
                        <button onclick=showCreateBookingForm(this) data-gameId="${game.gameId}" data-gameName="${game.gameName}" class="btn">Create Booking</button>
                        <button onclick=showJoinBooking() class="btn">Join a Booking</button>
                        <button onclick=showLeaderboard() class="btn">Leaderboard</button>
                    <!-- </form> -->
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
    <div><p> <c:out value="${message}" /> </p></div>
    <div class="create-booking" id="createBookingForm">
        <h2>Create Booking on <span id="selectedGameName"></span></h2>
        <form action="/GameZone/booking/add.htm" method="post" >
            <label for="date">Date:</label>
            <input type="date" id="bookDate" name="bookDate">
            <br>
            <label for="slot">Slot:</label>
            <select id="slot" name="slot">
                <option value="slot1">Morning</option>
                <option value="slot2">Afternoon</option>
                <option value="slot3">Evening</option>
                <option value="slot4">Night</option>
            </select>
            <br>
            <input type="hidden" name="gameId" id="gameId">
            <input type="hidden" name="gameName" id="gameName">
            <input type="submit" value="Submit" class="btn">
        </form>
    </div>

    <script>
        // Function to show the create-booking form when the "Booking" button is clicked
        function showCreateBookingForm(element) {
            document.getElementById('selectedGameName').innerText = element.getAttribute("data-gameName");
            document.getElementById('gameId').value = element.getAttribute("data-gameId");
            document.getElementById('gameName').value = element.getAttribute("data-gameName");
            // document.getElementById('gameId').innerText = element.getAttribute("data-gameId");
            // document.getElementById('gameName').innerText = element.getAttribute("data-gameName");
         var display =   document.getElementById('createBookingForm').style.display;
         if(display==="block"){
            document.getElementById('createBookingForm').style.display = "none"
         }else{
            document.getElementById('createBookingForm').style.display = "block"
         }
        }

        // Function to set game ID and game name in the hidden fields of the create booking form
        function setGameDetails(gameId, gameName) {
            document.getElementById('gameId').value = gameId;
            document.getElementById('gameName').value = gameName;
        }
    </script>
</body>
</html> 
