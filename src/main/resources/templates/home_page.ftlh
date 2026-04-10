<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
</head>
<header>
    <div class="header">
        <a class="link" href="/account">Личный кабинет</a>
        <a class="link" href="/user/home/">Главная</a>
    </div>
</header>
<body>
    <form action="/user/home/show" method="post">
        <input type="text" name="firstName" placeholder="Введите имя">
        <input type="text" name="lastName" placeholder="Введите фамилию">
        <button type="submit">Отправить</button>
    </form>
</body>
</html>