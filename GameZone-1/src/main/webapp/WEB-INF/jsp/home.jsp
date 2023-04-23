<!DOCTYPE html>
<html>
<head>
  <style>
    .topnav {
      background-color: #f2f2f2;
      overflow: hidden;
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
      z-index: 999;
    }

    .topnav a {
      float: left;
      display: block;
      color: black;
      text-align: center;
      padding: 14px 16px;
      text-decoration: none;
    }

    .topnav a:hover {
      background-color: #ddd;
    }

    .topnav p {
      float: right;
      margin-top: 16px;
      margin-right: 16px;
      font-weight: bold;
    }
  </style>
  <div class="topnav">
    <a class="active" href="#home">Home</a>
    <a href="/GameZone/gamer/getAllGames.htm">Games</a>
    <a href="/GameZone/gamer/gamer-booking-list.htm"">Booking</a>
    <a>${sessionScope.loggedGamer.gamerName}<a>
    <a href="/GameZone/gamer/logout.htm">Logout</a>
  </div>
  
</head>


<!-- <div class="topnav">
  <a class="active" href="#home">Home</a>
  <a href="getAllGames.htm">Games</a>
  <a href="gamer-booking-list.htm"">Booking</a>
  <a>${sessionScope.loggedGamer.gamerName}<a>
  <a href="/GameZone/gamer/logout.htm">Logout</a>
</div> -->


<!-- Content of the page goes here -->

</html>

