<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/teacherPage.css">
    <title>Teacher</title>
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
    <div class="teacher-container">

        <img class="img" src="/images/common.jpg" alt="Фото">

        <p class="teacher-name">${user.firstName} ${user.lastName}</p>

        <#if profile?has_content>
            <#if profile.yearsOfExperience?has_content>
                <p>📚 Опыт работы: ${profile.yearsOfExperience} лет</p>
            </#if>
            <#if profile.specialization?has_content>
                <p>📖 Специализация: ${profile.specialization}</p>
            </#if>
            <#if profile.pricePerHour?has_content>
                <p>💰 ${profile.pricePerHour} руб/час</p>
            </#if>
            <#if profile.description?has_content>
                <p>📝 О себе: ${profile.description}</p>
            </#if>
        <#else>
            <p>📋 Данные о преподавателе отсутствуют</p>
        </#if>
        <p class="rating">⭐ Рейтинг:  ${ratings[user.userId?string]!"нет"}</p>
    </div>

    <<div class="comments-container">

        <#if comments?has_content>
            <#list comments as c>
                <div class="comment-card">
                    <div class="comment-author">
                        ${c.studentFirstName} ${c.studentLastName}
                    </div>

                    <div class="comment-time">
                        ${c.formattedTime}
                    </div>

                    <div class="comment-text">
                        ${c.text}
                    </div>
                </div>
            </#list>
        <#else>
            <div class="empty">Комментариев нет</div>
        </#if>
    </div>

    <div class="add-comment-container">
        <form class="form" action="/teachers/${user.userId}" method="post">
            <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
            <textarea class="textarea" name="comment" placeholder="Введите комментарий"></textarea>
            <select class="select" name="rating">
                <#assign ratings = [0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0]>
                <#list ratings as rating>
                    <#assign ratingValue = rating?string("0.0")?replace(",", ".")>
                    <option value="${ratingValue}">${ratingValue}</option>
                </#list>
            </select>
            <button class="button" type="submit">Добавить комментарий</button>
        </form>
    </div>
</body>
</html>