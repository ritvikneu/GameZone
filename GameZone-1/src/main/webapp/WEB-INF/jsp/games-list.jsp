<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <html>

    <head>
        <jsp:include page="home.jsp" />
        <!-- <title>Games List</title> -->
        <style>
            /* CSS for table */
            table {
                width: 100%;
                border-collapse: collapse;
            }

            th,
            td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }

            .create-booking {
                display: none;
            }

            .leaderboard {
                animation: hide 5s forwards;
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
                            <input type="hidden" name="gameId" value="${game.gameId}">
                            <button onclick=showCreateBookingForm(this) data-gameId="${game.gameId}"
                                data-gameName="${game.gameName}" class="btn">Create Booking</button>
                            <button onclick=showJoinBooking() class="btn">Join a Booking</button>

                            <form action="/GameZone/games/getScoresForGame.htm" method="get">
                                <input type="hidden" name="gameId" value="${game.gameId}">
                                <button onclick=showLeaderboardForm(this) data-gameId="${game.gameId}"
                                    class="btn">Leaderboard</button>
                            </form>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            <p> ${sessionScope.message}</p>
        </div>
        <div class="create-booking" id="createBookingForm">
            <h2>Create Booking on <span id="selectedGameName"></span></h2>
            <form action="/GameZone/booking/add.htm" method="post">
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
                <input type="hidden" name="gameId" id="gameId">
                <input type="hidden" name="gameName" id="gameName">
                <input type="submit" value="Submit" class="btn">
            </form>
        </div>

        <div id="leaderboardTable"></div>


        <div class="leaderboard" id="leaderboardForm">

            <h2>Leaderboard on <span id="selectedGameName">"${scoreList[0].games.gameName}"</span></h2>

            <table>
                <tr>
                    <th>Gamer ID</th>
                    <th>Score</th>
                    <!-- Add more columns as needed -->
                </tr>
                <c:forEach var="score" items="${scoreList}">
                    <tr>
                        <td>${score.gamer.gamerName}</td>
                        <td>${score.score}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>


        <script>
            // Function to show the create-booking form when the "Booking" button is clicked
            function showCreateBookingForm(element) {
                document.getElementById('selectedGameName').innerText = element.getAttribute("data-gameName");
                document.getElementById('gameId').value = element.getAttribute("data-gameId");
                document.getElementById('gameName').value = element.getAttribute("data-gameName");
                // document.getElementById('gameId').innerText = element.getAttribute("data-gameId");
                // document.getElementById('gameName').innerText = element.getAttribute("data-gameName");
                var display = document.getElementById('createBookingForm').style.display;
                if (display === "block") {
                    document.getElementById('createBookingForm').style.display = "none"
                } else {
                    document.getElementById('createBookingForm').style.display = "block"
                }
            }

            function showLeaderboardForm(element) {
                document.getElementById('selectedGameName').innerText = element.getAttribute("data-gameName");
                // document.getElementById('gameId').innerText = element.getAttribute("data-gameId");
                // document.getElementById('gameName').innerText = element.getAttribute("data-gameName");
                var display = document.getElementById('leaderboardForm').style.display;
                if (display === "block") {
                    document.getElementById('leaderboardForm').style.display = "none"
                } else {
                    document.getElementById('leaderboardForm').style.display = "block"
                }
            }



            //     function showLeaderboard(element) {
            //         var gameId = element.getAttribute("data-gameId");
            //     const xhr = new XMLHttpRequest();
            //     xhr.open('GET', `/GameZone/games/getScoresForGame.htm?gameId=1`); // Update the URL to the correct endpoint
            //     xhr.setRequestHeader('Content-Type', 'application/json');
            //     xhr.onload = () => {
            //         if (xhr.status === 200) {
            //             const responseData = JSON.parse(xhr.responseText);

            //             const leaderboardContainer = document.getElementById('leaderboardContainer');
            //             leaderboardContainer.innerHTML = ''; // Clear the container before adding new content
            //             leaderboardContainer.appendChild(createLeaderboardTable(responseData.scoreList));
            //         } else {
            //             console.error('Failed to fetch leaderboard data:', xhr.status, xhr.statusText);
            //         }
            //     };
            //     xhr.send();
            // }

            function createLeaderboardTable(scoreList) {
                const table = document.createElement('table');
                const headerRow = document.createElement('tr');
                const gamerIdHeader = document.createElement('th');
                const scoreHeader = document.createElement('th');

                gamerIdHeader.textContent = 'Gamer ID';
                scoreHeader.textContent = 'Score';

                headerRow.appendChild(gamerIdHeader);
                headerRow.appendChild(scoreHeader);

                table.appendChild(headerRow);

                scoreList.forEach((score) => {
                    const row = document.createElement('tr');
                    const gamerIdCell = document.createElement('td');
                    const scoreCell = document.createElement('td');

                    gamerIdCell.textContent = score.gamerId;
                    scoreCell.textContent = score.score;

                    row.appendChild(gamerIdCell);
                    row.appendChild(scoreCell);

                    table.appendChild(row);
                });

                return table;
            }


            // Function to set game ID and game name in the hidden fields of the create booking form
            function setGameDetails(gameId, gameName) {
                document.getElementById('gameId').value = gameId;
                document.getElementById('gameName').value = gameName;
            }
        </script>
    </body>

    </html>