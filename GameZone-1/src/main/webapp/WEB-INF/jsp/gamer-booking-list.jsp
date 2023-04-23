
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Gamer Booking List</title>
</head>
<body>
    <h1>Gamer Booking List</h1>
    <table>
        <tr>
            <th>Booking ID</th>
            <th>Game Name</th>
            <th>Booking Date</th>
            <!-- Add more columns as needed -->
        </tr>
        <c:forEach var="booking" items="${bookingList}">
            <tr>
                <td>${booking.bookingId}</td>
                <td>${booking.gameName}</td>
                <td>${booking.bookDate}</td>
                <!-- Display other booking details as needed -->
            </tr>
        </c:forEach>
    </table>
</body>
</html>
