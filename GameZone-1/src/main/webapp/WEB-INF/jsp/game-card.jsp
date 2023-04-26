<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html lang="en">

    <head>
        <meta charset="UTF-8" />
        <title>Game List</title>
        <style>
            main-container {
                border-radius: 5px;
                padding: 5px;

                display: flex;
                flex-direction: row;

                /* align-content: center; */
            }

            .add-image {
                width: 50%;
            }

            .side-bar {
                height: 80%;
                width: 10%;
                background-color: aquamarine;
                align-items: center;
            }

            .reminder-space {
                height: 80%;
                width: 90%;
                background-color: rgb(213, 230, 245);

                display: flex;
                flex-direction: row;
                flex-wrap: wrap;
                gap: 20px;

            }

            .rem-card {
                flex: 0 0 300px;
                box-shadow: 5px 12px 9px 5px rgb(0, 0, 0, 0.2);
                height: 240px;
                width: 150px;
                border-radius: 7px;
                margin: 10px;
                padding: 10px;
            }

            .rem-card input {
                border: none;
                border-radius: 2px;
                background-color: rgb(213, 230, 245);
                width: 95%;

            }

            .name-text {
                font-size: larger;
            }

            .details-text {
                font-size: small;
            }

            .time-text {
                font-size: large;
            }

            .rem-buttons {
                display: flex;
                flex-direction: row;
            }

            checkbox {
                position: absolute;
                bottom: 8px;
                right: 16px;
                margin-top: -20px;
            }
        </style>
    </head>

    <body>
        <h1>Games List</h1>

        <div class="main-container">
            <c:forEach var="game" items="${gamesList}">
                <div class="reminder-space">
                    <div class="rem-card">
                        <div class="rem-name">
                            <p type="text" placeholder="Reminder Name" class="name-text">${game.gameName}</p>
                        </div>
                        <div class="rem-details">
                            <p type="text" class="details-text">${game.location}</p>
                        </div>
                        <div class="rem-time">
                            <p type="text" placeholder="Reminder Time" class="time-text">${game.availSlots}</p>
                        </div>
                        <div class="buttons"> <input type="hidden" name="gameId" value="${game.gameId}">
                            <button onclick=showCreateBookingForm(this) data-gameId="${game.gameId}"
                                data-gameName="${game.gameName}" class="btn">Create Booking</button>

                            <form action="/GameZone/booking/joinBooking.htm" method="get">
                                <input type="hidden" name="gameId" value="${game.gameId}">
                                <button class="btn">Join a Booking</button>
                            </form>

                            <form action="/GameZone/games/getLeaderBoard.htm" method="get">
                                <input type="hidden" name="gameId" value="${game.gameId}">
                                <button onclick=showLeaderboardForm(this) data-gameId="${game.gameId}"
                                    class="btn">Leaderboard</button>
                            </form>
                        </div>

                    </div>
                </div>
            </c:forEach>

        </div>
    </body>

    </html>