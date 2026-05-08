<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/signIn.css" rel="stylesheet">

    <title>SignIn</title>
</head>
<body>
    <form class="form" action="/signIn" method="post">
        <input type="hidden" id=csrf name="${_csrf.parameterName}" value="${_csrf.token}">
        <label class="label">Логин</label>
        <input class="input" type="text" name="username" placeholder="username">
        <div class="hidden error-input" id="username-error-div"></div>
        <label class="label">Пароль</label>
        <input class="input" type="password" name="password" placeholder="password">
        <div class="hidden error-input" id="password-error-div"></div>
        <input class="button" type="submit" value="SignIn"><br>
        <div class="hidden error-input" id="form-error-div">Пользователя не существует</div>
        <div class="oauth2-buttons">
            <a class="btn-google" href="/oauth2/authorization/google" >Войти через google</a>
        </div>
        <a class="link" href="/">Забыли пароль?</a>
    </form>
    <script src="/js/signIn.js"></script>
</body>
</html>