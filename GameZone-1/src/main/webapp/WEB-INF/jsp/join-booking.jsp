<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        <html>

        <head>
            <title>Gamer Booking List</title>

            <jsp:include page="home.jsp" />


            <style>
                .modify-booking {
                    display: none;
                }
            </style>


        </head>

        <body>
            <h1>Gamer Booking List</h1>
            <table>
                <tr>
                    <th>Game Name</th>
                    <th>Booked by Gamer</th>
                    <th>Booking ID</th>
                    <th>Booking Date</th>
                    <th>Slot Time</th>
                    <th>Actions</th>
                    <!-- Add more columns as needed -->
                </tr>
                <c:forEach var="booking" items="${bookingList}">
                    <tr>
                        <td>${booking.games.gameName}</td>
                        <td>${booking.gamer.gamerName}</td>
                        <td>${booking.bookingId}</td>
                        <td>${booking.bookDate}</td>
                        <td>${booking.slot}</td>
                        <td>
                          
                            <jsp:useBean id="now" class="java.util.Date" />
                            <c:if test="${booking.bookDate gt now}">
                                <form action="/GameZone/booking/gamer/joinBooking.htm" method="get"
                                    style="display: inline;">
                                    <input type="hidden" name="bookingId" value="${booking.bookingId}">
                                    <button type="submit" value="Join Booking">Join Booking</button>
                                </form>

                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div class="modify-booking" id="modifyBookingForm">
                <h2>Modify Booking <span id="selectedGameName"></span></h2>
                <form action="/GameZone/booking/modifyBooking.htm" method="get">
                    <label for="date">Date: </label>
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
                    <input type="hidden" name="bookingId" id="bookingId">
                    <input type="submit" value="Submit" class="btn">
                </form>
            </div>

            <script>
                function addScore() {
                    var score = prompt("Enter score:");
                    if (score !== null) {
                        // Check if input is a valid integer
                        if (!isNaN(parseInt(score))) {
                            // Set the value of the hidden input field to the entered score
                            var scoreInput = document.getElementById('score');
                            scoreInput.value = score;
                            scoreInput.innerText = score;
                            // Submit the form
                            var form = document.querySelector('scoreForm');
                            form.submit();
                        } else {
                            alert("Please enter a valid integer.");
                        }
                    }
                }
                function showModifyBookingForm(element) {
                    document.getElementById('selectedGameName').innerText = element.getAttribute("data-gameName");
                    document.getElementById('bookingId').value = element.getAttribute("data-bookingId");
                    // document.getElementById('gameId').innerText = element.getAttribute("data-gameId");
                    // document.getElementById('gameName').innerText = element.getAttribute("data-gameName");
                    var display = document.getElementById('modifyBookingForm').style.display;
                    if (display === "block") {
                        document.getElementById('modifyBookingForm').style.display = "none"
                    } else {
                        document.getElementById('modifyBookingForm').style.display = "block"
                    }
                }

            </script>
        </body>

        </html>


