<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Subject</title>
</head>
<body>

    <form class="form" action="/adminPage/addSubject" method="post">
        <label>Добавить предмет</label><br>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" type="text" name="subjectName" placeholder="Название предмета" required><br>
        <input class="input" type="text" name="description" placeholder="Описание предмета" required><br>
        <input class="input" type="text" name="difficulty" placeholder="Сложность предмета" required><br>
        <button type="submit">Добавить предмет</button>
    </form>
    <br>
    <form class="form" action="/adminPage/updateSubjectDifficulty" method="post">
        <label>Обновить сложность предмета</label><br>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" type="text" name="subjectName" placeholder="Название предмета" required><br>
        <input class="input" type="text" name="difficulty" placeholder="Новая сложность предмета" required><br>
        <button class="button" type="submit">Обновить сложность</button>
    </form>
</body>
</html>