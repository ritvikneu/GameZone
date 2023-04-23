<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <title>Gamer Booking List</title>
    <script>
        function addScore() {
            var score = prompt("Enter score:");
            if (score !== null) {
                // Check if input is a valid integer
                if (!isNaN(parseInt(score))) {
                    // Set the value of the hidden input field to the entered score
                    var scoreInput = document.getElementsByName('score')[0];
                    scoreInput.value = score;
                    scoreInput.innerText = score;
                    // Submit the form
                    // var form = document.querySelector('form');
                    // form.submit();
                } else {
                    alert("Please enter a valid integer.");
                }
            }
        }
    </script>
</head>
<body>
    <h1>Gamer Booking List</h1>
    <table>
        <tr>
            <th>Game Name</th>
            <th>Gamer Name</th>
            <th>Booking Date</th>
            <th>Actions</th>
            <!-- Add more columns as needed -->
        </tr>
        <c:forEach var="booking" items="${bookingList}">
            <tr>
                <td>${booking.games.gameName}</td>
                <td>${booking.gamer.gamerName}</td>
                <td>${booking.bookDate}</td>
                <td>
                    <jsp:useBean id="current" class="java.util.Date"/>
                        <c:if test="${booking.bookDate lt current}"> 
                    <form action="/GameZone/gamer/addScores.htm" method="get"   style="display: inline;">
                        <input type="hidden" name="bookingId" value="${booking.bookingId}">
                        <input type="hidden" name="gameId" value="${booking.games.gameId}">
                        <input type="hidden" name="bookDate" value="${booking.bookDate}">
                        <input type="hidden" name="score">
                        <input type="submit" value="Add Score" onclick="addScore()">    
                    </form>
                    
                </c:if>
                    <jsp:useBean id="now" class="java.util.Date"/>
                        <c:if test="${booking.bookDate gt now}"> 
                        <form action="/GameZone/booking/cancelBooking.htm" method="post" style="display: inline;">
                            <input type="hidden" name="bookingId" value="${booking.bookingId}">
                            <button type="submit" value="Cancel Booking" >Cancel Booking</button>
                        </form>
                    </c:if>
                    
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>


<!-- <!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Gamer Booking List</title>
</head>
<body>
    <h1>Gamer Booking List</h1>
    <table>
        <tr>
            <th>Game Name</th>
            <th>Gamer Name</th>
            <th>Booking Date</th>
        </tr>
        <c:forEach var="booking" items="${bookingList}">
            <tr>
                <td>${booking.games.gameName}</td>
                <td>${booking.gamer.gamerName}</td>
                <td>${booking.bookDate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html> -->
