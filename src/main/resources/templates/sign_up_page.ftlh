<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SignUp</title>
    <link href="/css/signUp.css" rel="stylesheet">
</head>
<body>
    <form class="form">
        <p>Регистрация</p>
        <input class="input" name="username" type="text" placeholder="username"><br>
        <div id="username-error-div"></div>
        <input class="input" name="password" type="password" placeholder="password"><br>
        <div id="password-error-div"></div>
        <input class="input" name="email" type="email" placeholder="email"><br>
        <div id="email-error-div"></div>
        <select class="select" name="role">
            <option class="option" value="Учитель">Учитель</option>
            <option class="option" value="Ученик">Ученик</option>
            <option class="option" value="Админ">Админ</option>
        </select><br>
        <div id="role-error-div"></div>
        <input class="input" name="birthDate" type="text" placeholder="Дата рождения"><br>
        <div id="birthDate-error-div"></div>
        <input class="input" name="firstName" type="text" placeholder="Имя"><br>
        <div id="firstName-error-div"></div>
        <input class="input" name="lastName" type="text" placeholder="Фамилия"><br>
        <div id="lastName-error-div"></div>
        <select class="select" name="gender">
            <option value="М">М</option>
            <option value="Ж">Ж</option>
        </select>
        <div id="gender-error-div"></div>
        <input class="input" name="phoneNumber" type="text" placeholder="Номер телефона"><br>
        <div id="phone-error-div"></div>

        <div id="teacher" class="div">
        <input class="input" name="description" type="text" placeholder="О себе">
        <input class="input" name="yearsOfExperience" type="text" placeholder="Опыт работы">
        <input class="input" name="specialization" type="text" placeholder="Специализация">
        <input class="input" name="pricePerHour" type="text" placeholder="Цена за занятие">
        </div>
        <div id="student" class="div">
            <input class="input" name="educationalInstitution" type="text" placeholder="Место обучения">
            <input class="input" name="parentsFirstName" type="text" placeholder="Имя родителя">
            <input class="input" name="parentsLastName" type="text" placeholder="Фамилия родителя">
        </div>
        <button class="button" type="submit">Sign Up</button>
        <div id="form-error-div"></div>
    </form>

    <script src="/js/signUp.js"></script>
</body>
</html>