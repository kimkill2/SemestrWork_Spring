<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin</title>
</head>
<body>
    <form class="form" action="/adminPage/addAdmin" method="post">
        <label>Добавить админа</label><br>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" name="username" type="text" placeholder="username" required><br>
        <input class="input" name="password" type="password" placeholder="password" required><br>
        <input class="input" name="email" type="email" placeholder="email" required><br>
        <input name="role" type="hidden" value="Админ">
        <input class="input" name="birthDate" type="text" placeholder="Дата рождения" required><br>
        <input class="input" name="firstName" type="text" placeholder="Имя" required><br>
        <input class="input" name="lastName" type="text" placeholder="Фамилия" required><br>
        <select name="Пол">
            <option value="М">М</option>
            <option value="Ж">Ж</option>
        </select>
        <input type="submit" value="Добавить админа">
    </form>
    <br>
    <form class="form" action="/adminPage/updatePassword" method="post">
        <label>Обновить пароль</label><br>
        <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" type="text" name="username" placeholder="Имя пользователя" required><br>
        <input class="input" type="text" name="password" placeholder="Новые пароль" required><br>
        <button type="submit">Изменить пароль</button>
    </form>
    <br>
    <form class="form" action="/adminPage/deleteAdmin" method="post">
        <label>Удалить админа</label>
        <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" type="text" name="username" placeholder="Введите имя пользователя" required><br>
    </form>
</body>
</html>