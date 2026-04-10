<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Comment</title>
</head>
<body>
    <form class="form" action="/adminPage/addComment" method="post">
        <label>Добавить комментарий</label><br>
        <input class="input" type="text" name="subject" placeholder="Имя пользователя Учителя" required><br>
        <input class="input" type="text" name="author" placeholder="Имя пользователя Ученика" required><br>
        <textarea class="text-area" name="text" placeholder="Текст комментария" required></textarea><br>
        <button type="submit">Добавить комментарий</button>
    </form>
    <br>
    <form class="form" action="/adminPage/updateComment" method="post">
        <label>Обновить комментарий</label><br>
        <input class="input" type="text" name="id" placeholder="Id комментария" required><br>
        <textarea class="input" name="comment" placeholder="Новый комментарий" required></textarea><br>
        <button type="submit">Обновить комментарий</button>
    </form>
</body>
</html>