<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/signIn.css" rel="stylesheet">
    <title>SignIn</title>
</head>
<body>
    <form class="form" action="/signIn" method="post">
        <label class="label">Логин</label>
        <input class="input" type="text" name="username" placeholder="username" >
        <label class="label">Пароль</label>
        <input class="input" type="password" name="password" placeholder="password">
        <input class="button" type="submit" value="SignIn">
        <div class="oauth2-buttons">
            <a class="btn-google" href="/oauth2/authorization/google" >Войти через google</a>
        </div>
        <a class="link" href="restore">Забыли пароль?</a>
    </form>
</body>
</html>