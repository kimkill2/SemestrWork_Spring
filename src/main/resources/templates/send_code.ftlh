<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Подтверждение кода</title>
    <link rel="stylesheet" href="/css/sendCode.css">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<div class="container">
    <h2>Подтверждение номера телефона</h2>
    <input type="hidden" id="csrf" value="${_csrf.token}">
    <input type="text" id="code" placeholder="Введите код из SMS" autocomplete="off">
    <button id="verifyBtn">Подтвердить</button>
    <div id="message" class="message info">
        Отправка кода...
    </div>
</div>

<script src="/js/sendCode.js"></script>
</body>
</html>