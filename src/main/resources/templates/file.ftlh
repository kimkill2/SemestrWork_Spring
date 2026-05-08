<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>File</title>
</head>
<body>
    <form class="form" action="/adminPage/addFile" method="post" enctype="multipart/form-data">
        <label>Добавить файл</label><br>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" type="file" name="file" placeholder="Добавьте файл" required><br>
        <button type="submit">Добавить файл</button>
    </form>
    <br>
    <form class="form" action="/adminPage/updateFileName" method="post">
        <label>Обновить Имя файла</label><br>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input class="input" type="text" name="id" placeholder="id файла" required><br>
        <input class="input" type="text" name="fileName" placeholder="новое название файла" required><br>
        <button class="button" type="submit">Обновить</button>
    </form>
</body>
</html>