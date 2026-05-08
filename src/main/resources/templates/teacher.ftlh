<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Teacher</title>
</head>
<body>
    <form class="form" action="/adminPage/addTeacher" method="post">
        <label>Добавить Учителя</label><br>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" name="username" type="text" placeholder="username" required><br>
        <input class="input" name="password" type="password" placeholder="password" required><br>
        <input class="input" name="email" type="email" placeholder="email" required><br>
        <input name="role" type="hidden" value="Учитель">
        <input class="input" name="birthDate" type="text" placeholder="Дата рождения" required><br>
        <input class="input" name="firstName" type="text" placeholder="Имя" required><br>
        <input class="input" name="lastName" type="text" placeholder="Фамилия" required><br>
        <select name="gender">
            <option value="М">М</option>
            <option value="Ж">Ж</option>
        </select>
        <input class="input" name="pricePerHour" type="text" placeholder="Цена за час" required><br>
        <input class="input" name="rating" type="text" placeholder="Рейтинг" required><br>
        <input class="input" name="specialization" type="text" placeholder="Специализация" required><br>
        <input class="input" name="yearsOfExperience" type="text" placeholder="Опыт работы" required><br>
        <textarea class="text-area" name="description" typeof="text" placeholder="О себе" required></textarea><br>

        <input type="submit" value="Добавить учителя">
    </form>
    <br>
    <form class="form" action="/adminPage/updateSpecialization" method="post">
        <label>Обновить специализацию</label><br>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" type="text" name="username" placeholder="Имя пользователя"><br>
        <input class="input" type="text" name="specialization" placeholder="Новая Специализация"><br>
        <button type="submit">Обновить специализацию</button>
    </form>

</body>
</html>