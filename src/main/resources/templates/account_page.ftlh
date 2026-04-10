<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Account</title>
</head>

<body>
    <#if role == "ROLE_TEACHER">
        <form>
            <input class="input" name="username" type="text" placeholder="username" required><br>
            <input class="input" name="password" type="password" placeholder="password" required><br>
            <input class="input" name="email" type="email" placeholder="email" required><br>
            <input class="input" name="pricePerHour" type="text" placeholder="Цена за час" required><br>
            <input class="input" name="rating" type="text" placeholder="Рейтинг" required><br>
            <input class="input" name="specialization" type="text" placeholder="Специализация" required><br>
            <input class="input" name="yearsOfExperience" type="text" placeholder="Опыт работы" required><br>
            <textarea class="text-area" name="description" typeof="text" placeholder="О себе" required></textarea><br>
            <input type="submit" value="Редактировать данные">

        </form>
    <#elseif role == "ROLE_STUDENT">
        <form>
            <input class="input" name="username" type="text" placeholder="username" required><br>
            <input class="input" name="password" type="password" placeholder="password" required><br>
            <input class="input" name="email" type="email" placeholder="email" required><br>
            <input class="input" name="educationalInstitution" type="text" placeholder="Место обучения" required><br>
            <input class="input" name="parentsFirstName" type="text" placeholder="Имя Родителя" required><br>
            <input class="input" name="parentsLastName" type="text" placeholder="Фамилия Родителя" required><br>
            <input class="input" name="phoneNumber" type="text" placeholder="Номер телефона" required><br>

            <input type="submit" value="Редактировать">
        </form>
    <#else>
        <form>
            <input class="input" name="username" type="text" placeholder="username" required><br>
            <input class="input" name="password" type="password" placeholder="password" required><br>
            <input class="input" name="email" type="email" placeholder="email" required><br>
            <input class="input" name="birthDate" type="text" placeholder="Дата рождения" required><br>
            <input class="input" name="firstName" type="text" placeholder="Имя" required><br>
            <input class="input" name="lastName" type="text" placeholder="Фамилия" required><br>
            <input type="submit" value="Редактировать">
        </form>
    </#if>
</body>
</html>