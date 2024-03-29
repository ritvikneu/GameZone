<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        <html>

        <head>
            <title>Gamer Booking List</title>

            <jsp:include page="home.jsp" />


            <style>
                  /* CSS for table */
            table {
                width: 70%;
                border-collapse: collapse;
            }

            th,
            td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
                background-color: #939090;
            }

            th {
                background-color: #f2f2f2;
            }
                .modify-booking {
                    display: none;
                }

                .message {
                    animation: hide 15s forwards;
                }

                @keyframes hide {
                    0% {
                        opacity: 1;
                    }

                    100% {
                        opacity: 0;
                    }
                }
            </style>


        </head>

        <body>
            <h1>Gamer Booking List</h1>
            <table>
                <tr>
                    <th>Game Name</th>
                    <th>Gamer Name</th>
                    <th>Zone Name</th>
                    <th>Booking Date</th>
                    <th>Gamers in Zone</th>

                    <th>Actions</th>
                    <!-- Add more columns as needed -->
                </tr>
                <c:forEach var="booking" items="${bookingList}">
                    <tr>
                        <td>${booking.bookingId.games.gameName}</td>
                        <td>${booking.bookingId.gamer.gamerName}</td>
                        <td>${booking.nameOfZone}</td>
                        <td>${booking.bookingId.bookDate}</td>

                        <td>
                            <form id="zoners" action="/GameZone/booking/showZoners.htm" method="get"
                                style="display: inline;">
                                <input type="hidden" name="gamerId" value="${booking.bookingId.gamer.gamerId}">
                                <input type="hidden" name="gameId" value="${booking.bookingId.games.gameId}">
                                <input type="hidden" name="bookDate" value="${booking.bookingId.bookDate}">
                                <input type="submit" value="Show" onclick=showZoners(this)>
                            </form>
                        </td>
                        <td>
                            <jsp:useBean id="current" class="java.util.Date" />
                            <c:if test="${booking.bookingId.bookDate lt current}">
                                <form id="scoreForm" action="/GameZone/booking/modifyBookingScore.htm" method="get"
                                    style="display: inline;">
                                    <input type="hidden" name="gamerId" value="${booking.bookingId.gamer.gamerId}">
                                    <input type="hidden" name="gameId" value="${booking.bookingId.games.gameId}">
                                    <input type="hidden" name="bookDate" value="${booking.bookingId.bookDate}">
                                    <input type="text" name="score" id="score" value="${booking.score}">

                                    <input type="submit" value="Edit Score">
                                </form>

                            </c:if>
                            <jsp:useBean id="now" class="java.util.Date" />
                            <c:if test="${booking.bookingId.bookDate gt now}">
                                <form action="/GameZone/booking/cancelBooking.htm" method="post"
                                    style="display: inline;">
                                    <input type="hidden" name="gamerId" value="${booking.bookingId.gamer.gamerId}">
                                    <input type="hidden" name="gameId" value="${booking.bookingId.games.gameId}">
                                    <input type="hidden" name="bookDate" value="${booking.bookingId.bookDate}">
                                    <button type="submit" value="Cancel Booking">Cancel Booking</button>
                                </form>
                                <input type="hidden" name="gameId" value="${booking.bookingId.games.gameId}">
                                <input type="hidden" name="bookDate" value="${booking.bookingId.bookDate}">
                                <button type="submit" value="Modify Booking" onclick=showModifyBookingForm(this)
                                    data-bookDate="${booking.bookingId.bookDate}"
                                    data-gameId="${booking.bookingId.games.gameId}"
                                    data-gameName="${booking.bookingId.games.gameName}">Modify Booking</button>

                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
            </table>


            <div class="message">
                <p> ${sessionScope.message}</p>
            </div>

            <div class="modify-booking" id="modifyBookingForm">
                <h2>Modify Booking <span id="selectedGameName"></span></h2>
                <form action="/GameZone/booking/modifyBooking.htm" method="get">
                    <label for="date">Date: </label>
                    <input type="date" id="bookDate" name="bookDate" onchange=checkDate()>
                    <br>
                    <label for="slot">Slot:</label>
                    <select id="slot" name="slot">
                        <option value="Morning">Morning</option>
                        <option value="Afternoon">Afternoon</option>
                        <option value="Evening">Evening</option>
                        <option value="Night">Night</option>
                    </select>
                    <br>

                    <p id="error-msg"></p>
                    <!-- <span id="selectedGameId" id="gameId" name="gameId"></span>  -->
                    <input type="hidden" id="gameId" name="gameId">
                    <input type="date" id="currBookDate" name="currBookDate" hidden>
                    <input type="submit" value="Submit" class="btn">
                </form>
            </div>

            <div class="zoners" id="zoners">
                <table>
                    <tr>
                        <th>Gamer Name</th>
                        <th>Email</th>
                        <!-- Add more columns as needed -->
                    </tr>
                    <c:forEach var="gamer" items="${zonerList[0].zoners}">
                        <tr>
                            <td>${gamer.gamerName}</td>
                            <td>${gamer.email}</td>
                            <!-- Display other properties of Gamer object as needed -->
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <h3>My Zones</h3>
            <div class="myZones" id="myZones">
                <table>
                    <tr>
                        <th>Game Name</th>
                    <th>Gamer Name</th>
                    <th>Zone Name</th>
                    <th>Booking Date</th>
                        <!-- Add more columns as needed -->
                    </tr>
                    <c:forEach var="booking" items="${myZoneList}">
                        <tr>
                            <td>${booking.bookingId.games.gameName}</td>
                            <td>${booking.bookingId.gamer.gamerName}</td>
                            <td>${booking.nameOfZone}</td>
                            <td>${booking.bookingId.bookDate}</td>
                            <!-- Display other properties of Gamer object as needed -->
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <script>

                function showModifyBookingForm(element) {
                    // document.getElementById('selectedGameId').innerText = element.getAttribute("data-gameId");
                    document.getElementById('gameId').value = element.getAttribute("data-gameId");
                    document.getElementById('currBookDate').value = element.getAttribute("data-bookDate");
                    document.getElementById('selectedGameName').innerText = element.getAttribute("data-gameName");

                    var display = document.getElementById('modifyBookingForm').style.display;
                    if (display === "block") {
                        document.getElementById('modifyBookingForm').style.display = "none"
                    } else {
                        document.getElementById('modifyBookingForm').style.display = "block"
                    }
                }

                function checkDate(element) {
                
                var selectedDate = new Date(document.getElementById("bookDate").value);
                var currentDate = new Date();
                if (selectedDate < currentDate) {
                    document.getElementById("error-msg").innerHTML = "Selected date cannot be less than today's date.";
                    document.getElementById("modifyBookingForm").querySelector("input[type='submit']").disabled = true;
                } else {
                    document.getElementById("error-msg").innerHTML = "";
                    document.getElementById("modifyBookingForm").querySelector("input[type='submit']").disabled = false;
                }
           
                }
    


                function showZoners(element) {

                    var display = document.getElementById('zoners').style.display;
                    if (display === "block") {
                        document.getElementById('zoners').style.display = "none"
                    } else {
                        document.getElementById('zoners').style.display = "block"
                    }
                }

            </script>
        </body>

        </html>