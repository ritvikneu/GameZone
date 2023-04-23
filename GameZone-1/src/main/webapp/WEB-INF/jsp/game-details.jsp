<!DOCTYPE html>
<html>
<head>
    <title>GameZone</title>
    <style>
        /* CSS for styling the button group */
body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
    padding: 20px;
}

h1 {
    text-align: center;
}

.button-group {
    display: flex;
    justify-content: center;
    margin-top: 50px;
}

.btn {
    background-color: #007bff;
    color: #fff;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    margin: 0 10px;
}

.btn:hover {
    background-color: #0056b3;
}

    </style>
</head>
<body>
    <h1>Welcome to GameZone</h1>
    <div class="button-group">
        <button onclick="location.href='/booking'" class="btn">Booking</button>
        <button onclick="location.href='/available'" class="btn">Available</button>
        <button onclick="location.href='/leaderboard'" class="btn">Leaderboard</button>
    </div>
</body>
</html>
