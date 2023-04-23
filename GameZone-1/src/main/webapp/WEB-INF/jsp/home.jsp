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
</head>
<body>

<div class="topnav">
  <a class="active" href="#home">Home</a>
  <a href="getAllGames.htm">Games</a>
  <a href="/booking">Booking</a>
  <h5>${sessionScope.loggedGamer.gamerName}</h5>
  <h5>${sessionScope.loggedGamer.university.univId}</h5>
  <a href="/GameZone/gamer/logout.htm">Logout</a>
</div>

<!-- Content of the page goes here -->

</body>
</html>

<!-- <!DOCTYPE html>
<html>
  <head>
    <title>My JSP Page with Navbar</title>
    <style>
      /* CSS for top navigation bar */
      .topnav {
        overflow: hidden;
        background-color: #f2f2f2;
        position: fixed;
        top: 0;
        width: 100%;
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
        color: black;
      }

      .topnav a.active {
        background-color: #4caf50;
        color: white;
      }

      /* CSS for content area */
      .content {
        margin-top: 80px; /* Add margin top equal to the height of the navigation bar */
        padding: 16px;
      }
    </style>
  </head>
  <body>
    <div class="topnav">
      <a class="active" href="#home">Home</a>
      <a href="getAllGames.htm">Games</a>
      <a href="/booking">Booking</a>
      <p>${sessionScope.loggedGamer.gamerName}</p>
      <a href="/GameZone/gamer/logout.htm">Logout</a>
    </div>

    <div class="content">
    </div>
  </body>
</html>


