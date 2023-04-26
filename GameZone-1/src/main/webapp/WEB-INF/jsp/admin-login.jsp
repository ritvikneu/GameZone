<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }

        .login-container {
            width: 300px;
            margin: 0 auto;
            margin-top: 100px;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0px 0px 5px 2px rgba(0,0,0,0.1);
            border-radius: 5px;
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .login-container input[type="text"],
        .login-container input[type="password"],
        .login-container select {
            width: 100%;
            padding: 12px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .login-container select {
            height: 40px;
        }

        .login-container input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .login-container input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-msg {
            color: #ff0000;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form id="login-form" onsubmit="return false;">
            <input type="text" id="email" name="email" placeholder="Email">
            <input type="password" id="password" name="password" placeholder="Password">
            <select id="role" name="role">
                <option value="admin">Admin</option>
                <option value="user">User</option>
            </select>
            <input type="submit" value="Login">
            <div id="error-msg" class="error-msg"></div>
        </form>
    </div>

    <script>
        document.getElementById("login-form").addEventListener("submit", function() {
            // Get form data
            var email = document.getElementById("email").value;
            var password = document.getElementById("password").value;
            var role = document.getElementById("role").value;

            // Perform validation
            if (email === "" || password === "") {
                document.getElementById("error-msg").innerHTML = "Please enter email and password.";
                return false;
            }

            // Perform login logic
            // Replace with your actual login logic, e.g. make an API call to server
            // with the form data and handle the response accordingly

            // Example of a successful login
            if (email === "user@example.com" && password === "password") {
                if (role === "admin") {
                    window.location.href = "/admin/dashboard"; // Redirect to admin dashboard
                } else if (role === "user") {
                    window.location.href = "/user/dashboard"; // Redirect to user dashboard
                }
            } else {
                document.getElementById("error-msg").innerHTML = "Invalid email or password.";
            }

            return false;
        });
    </script>
</body>
</html>
