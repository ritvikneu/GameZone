<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <html>

  <head>
    <title>Login/Registration Page</title>
    <style>
      body {
        background-image: "gamezone.jpg"
      }

      /* CSS for container */
      .container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
      }

      /* CSS for login and registration forms */
      .form-container {
        display: flex;
        justify-content: space-between;
        width: 600px;
        padding: 20px;
        background-color: #f2f2f2;
        border-radius: 5px;
        box-shadow: 0px 0px 10px #999;
      }

      .form-container form {
        flex: 1;
        margin-right: 20px;
      }

      .form-container h2 {
        margin-bottom: 20px;
      }

      .form-container label,
      .form-container input {
        display: block;
        margin-bottom: 10px;
      }

      .form-container input[type="submit"] {
        background-color: #4caf50;
        color: white;
        padding: 10px;
        border: none;
        cursor: pointer;
      }

      .form-container input[type="submit"]:hover {
        background-color: #45a049;
      }

      /* CSS for error message */
      .error-msg {
        color: red;
        margin-top: 10px;
      }

      .admin-login {
        display: block;
        width: 115px;
        height: 25px;
        background: #4E9CAF;
        padding: 10px;
        text-align: center;
        border-radius: 5px;
        color: white;
        font-weight: bold;
        line-height: 25px;
      }
    </style>
    <script>
      function showSuccessMessage() {
        var gamer = document.getElementById("gamerName")
        alert("Gamer " + gamer + " registered, please Login");
      }
    </script>
  </head>

  <body>
    <div class="container">
      <div class="form-container">


        <form id="login-form" action="/GameZone/gamer/home.htm" method="POST">
          <h2>Gamer Login</h2>
          <label for="email">Email:</label>
          <input type="email" id="email" name="email" required />
          <label for="password">Password:</label>
          <input type="password" id="password" name="password" required />
          <input type="submit" value="LOGIN" />

        </form>
        <a href="/GameZone/admin/getuniv" class="admin-login"> ADMIN LOGIN</a>

        <form id="register-form" method="POST" action="/GameZone/gamer/register.htm">
          <h2>Gamer Registration</h2>
          <label for="gamerName">Gamer Name:</label>
          <input type="text" id="gamerName" name="gamerName" required />
          <br /><br />
          <label for="universityId">University:</label>
          <select name="univId">
            <c:forEach var="e" items="${univList}">
              <option value="${e.getUnivId()}">${e.getUnivName()}</option>
            </c:forEach>
          </select>
          <br /><br />
          <label for="email">Email:</label>
          <input type="email" id="email" name="email" required />
          <br /><br />
          <label for="password">Password:</label>
          <input type="password" id="password" name="password" required />
          <br /><br />
          <input type="submit" value="REGISTER" onclick="showSuccessMessage()" />
        </form>
      </div>
    </div>
  </body>

  </html>