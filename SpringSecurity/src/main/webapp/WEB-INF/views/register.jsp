<!-- File: src/main/webapp/WEB-INF/views/register.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Registration Form</h1>
<form action="/register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="userName"><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Register">
</form>
</body>
</html>