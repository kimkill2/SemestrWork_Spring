<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/findTeachers.css">
    <title>Учителя</title>
</head>
<header>
    <div class="header">
        <a class="link" href="/account">Личный кабинет</a>
        <a class="link" href="/user/home/">Главная</a>
        <a class="link" href="/teachers">Найти учителя</a>
        <img class="img" src="/images/${avatarSrc}" alt="Фото">
    </div>
</header>
<body>
<form class="search-form" method="post" action="/teachers">
    <input class="input" name="name" placeholder="Введите Имя и Фамилию учителя" value="${name!}">
    <button class="button" type="submit">Найти учителя</button>
</form>

<div class="teacher-list-container">
    <#if teachers?has_content>
        <#list teachers as t>
            <div class="teacher-container" data-iser-id="${t.user.userId}">
                <img class="img" src="/images/common.jpg" alt="Фото нет">
                <p class="teacher-name">${t.user.firstName} ${t.user.lastName}</p>
                <p class="rating">⭐ Рейтинг: ${t.rating!"нет"}</p>
                <a class="comments-link" href="/teachers/${t.user.userId}">💬 Подробная информация</a>
                <p>Просмотрело: <span class="views-count">${t.views!0}</span></p>
            </div>
        </#list>
    <#else>
        <p class="no-teachers">Преподаватели не найдены</p>
    </#if>
</div>

<script src="/js/findTeachers.js"></script>
</body>
</html>