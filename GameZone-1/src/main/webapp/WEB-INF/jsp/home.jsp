<!DOCTYPE html>
<html>

<head>
  <style>
    body::before {
      content: "";
      display: block;
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      /* background-image: url("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/7d185393-7bf0-465b-b68b-fccf3240c210/db9ias3-e6f90220-6947-46b8-a833-951ad2587cc2.jpg/v1/fill/w_1192,h_670,q_70,strp/rime_tribute_by_tohad_db9ias3-pre.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NzIwIiwicGF0aCI6IlwvZlwvN2QxODUzOTMtN2JmMC00NjViLWI2OGItZmNjZjMyNDBjMjEwXC9kYjlpYXMzLWU2ZjkwMjIwLTY5NDctNDZiOC1hODMzLTk1MWFkMjU4N2NjMi5qcGciLCJ3aWR0aCI6Ijw9MTI4MCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.A9DRV5IApTJfGu2cVL3oBasF6UdrkpopmuXRFeMAI_U"); */
      opacity: 0.8; /* Set the opacity value to adjust the opacity of the background image */
      z-index: -1; /* Ensure the pseudo-element is positioned below the content */
    }

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
    <a class="active" href="/GameZone/gamer/gameCard.htm">Home</a>
    <a href="/GameZone/gamer/getAllGames.htm">Games</a>
    <a href="/GameZone/gamer/gamer-booking-list.htm"">Booking</a>
    <a>${sessionScope.loggedGamer.gamerName}<a>
    <a href=" /GameZone/gamer/logout.htm">Logout</a>
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