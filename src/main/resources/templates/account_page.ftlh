<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/accountPage.css">
    <title>Account</title>
</head>

<header>
    <div class="header">
        <a class="link" href="/account">Личный кабинет</a>
        <a class="link" href="/user/home/">Главная</a>
        <a class="link" href="/teachers">Найти учителя</a>
        <img class="img" src="/images/${avatarSrc}" alt="Фото">
    </div>
</header>
<br>
<body>

    <form action="/account/changeUserData" method="post" enctype="multipart/form-data">
        <img class="img" src="/images/${avatarSrc}" alt="Фото нет"><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class=input type="file" name="file"><br>
        <label>Имя</label><br>
        <input class="input" type="hidden" name="username" value="${user.username}">
        <input class="input" name="firstName" type="text" placeholder="Имя" value="${user.firstName}" required><br>
        <label>Фамилия</label><br>
        <input class="input" name="lastName" type="text" placeholder="Фамилия" value="${user.lastName}" required><br>
        <#if role = "Учитель">
            <label>Цена за час</label><br>
            <input class="input" name="pricePerHour" type="text" placeholder="Цена за час" required><br>
            <label>Специализация</label><br>
            <input class="input" name="specialization" type="text" placeholder="Специализация" required><br>
            <label>Опыт работы</label><br>
            <input class="input" name="yearsOfExperience" type="text" placeholder="Опыт работы" required><br>
            <label>О себе</label><br>
            <textarea class="text-area" name="description" typeof="text" placeholder="О себе" required></textarea><br>
        <#elseif role == "Ученик">
            <label>Место обучения</label><br>
            <input class="input" name="educationalInstitution" type="text" placeholder="Место обучения" required><br>
            <label>Имя Родителя</label><br>
            <input class="input" name="parentsFirstName" type="text" placeholder="Имя Родителя" required><br>
            <label>Фамилия Родителя</label><br>
            <input class="input" name="parentsLastName" type="text" placeholder="Фамилия Родителя" required><br>
            <label>Номер телефона</label><br>
            <input class="input" name="phoneNumber" type="text" placeholder="Номер телефона" required><br>
        <#else>
        </#if>
        <button class="button" type="submit">Сохранить изменения</button>
    </form>
</body>
</html>