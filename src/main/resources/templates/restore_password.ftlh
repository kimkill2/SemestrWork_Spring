<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restore password</title>
</head>
<body>
    <form action="/restorePassword" method="post">
        <p>На эту почту придёт код</p>
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="email" name="email" placeholder="Введите название почты">
        <input type="submit" value="Отправить">
    </form>
</body>
</html>