<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SignUp</title>
    <link href="/css/signUp.css" rel="stylesheet">
</head>
<body>
    <form class="signUpForm" action="/signUpPage" method="post">
        <input class="input" name="username" type="text" placeholder="username"><br>
        <input class="input" name="password" type="password" placeholder="password"><br>
        <input class="input" name="email" type="email" placeholder="email"><br>
        <select class="select" name="role">
            <option class="option" value="Учитель">Учитель</option>
            <option class="option" value="Ученик">Ученик</option>
            <option class="option" value="Админ">Админ</option>
        </select><br>
        <input class="input" name="birthDate" type="text" placeholder="Дата рождения"><br>
        <input class="input" name="firstName" type="text" placeholder="Имя"><br>
        <input class="input" name="lastName" type="text" placeholder="Фамилия"><br>
        <select class="select" name="gender">
            <option value="М">М</option>
            <option value="Ж">Ж</option>
        </select>
        <button class="button" type="submit">Sign Up</button>
    </form>
</body>
</html>