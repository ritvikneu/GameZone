<!-- <%-- Document : user-registraion Created on : Apr 13, 2023, 01:43:57 AM Author :
ritvikparamkusham --%>

 -->

<!DOCTYPE html>
<html>
<head>
  <title>Game Booking</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      // Get references to the select elements
      var universitySelect = $('select[name="univId"]');
      var gameSelect = $('select[name="gameId"]');

      // Attach an event listener to the university select element
      universitySelect.change(function() {
        // Get the selected university ID
        var univId = universitySelect.val();

        // Make an AJAX call to retrieve the games list
        $.ajax({
          url: "/getGamesList", // Update this URL to match your server endpoint
          data: { univId: univId },
          success: function(data) {
            // Clear the game select element
            gameSelect.empty();

            // Populate the game select element with the retrieved games list
            $.each(data, function(i, game) {
              gameSelect.append($('<option>', {
                value: game.gameId,
                text: game.gameName
              }));
            });
          }
        });
      });
    });
  </script>
</head>
<body>
  <h1>Game Booking</h1>
  <form action="/booking/bookgames.htm" method="post"> <!-- Update the form action to match your actual endpoint -->
    <label for="univId">Choose a University:</label>
    <select name="univId">
      <c:forEach var="univ" items="${univList}">
        <option value="${univ.univId}">${univ.univName}</option>
      </c:forEach>
    </select>
    <br><br>
    <label for="gameId">Choose a Game:</label>
    <select name="gameId">
      <!-- Games will be dynamically populated based on the selected university -->
    </select>
    <br><br>
    <input type="submit" value="Submit">
  </form>
</body>
</html>

