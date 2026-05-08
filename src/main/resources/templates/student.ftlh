<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Student</title>
</head>
<body>
    <form class="form" action="/adminPage/addStudent" method="post">
        <label>Добавить Студента</label><br>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" name="username" type="text" placeholder="username" required><br>
        <input class="input" name="password" type="password" placeholder="password" required><br>
        <input class="input" name="email" type="email" placeholder="email" required><br>
        <input name="role" type="hidden" value="Ученик">
        <input class="input" name="birthDate" type="text" placeholder="Дата рождения" required><br>
        <input class="input" name="firstName" type="text" placeholder="Имя" required><br>
        <input class="input" name="lastName" type="text" placeholder="Фамилия" required><br>
        <select name="Пол">
            <option value="М">М</option>
            <option value="Ж">Ж</option>
        </select>
        <input class="input" name="educationalInstitution" type="text" placeholder="Место обучения" required><br>
        <input class="input" name="parentsFirstName" type="text" placeholder="Имя Родителя" required><br>
        <input class="input" name="parentsLastName" type="text" placeholder="Фамилия Родителя" required><br>
        <input class="input" name="phoneNumber" type="text" placeholder="Номер телефона" required><br>

        <input type="submit" value="Добавить ученика">
    </form>
    <br>
    <form class="form" action="/adminPage/updateParentsFirstName" method="post">
        <label>Обновить Имя родителя</label><br>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" type="text" name="username" placeholder="Имя пользователя" required><br>
        <input class="input" type="text" name="parentsFirstName" placeholder="Новое Имя родителя" required><br>
        <button type="submit">Обновить имя родителя</button>
    </form>
</body>
</html>