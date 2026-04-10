<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Учителя</title>
</head>
<body>
    <div class="search-container">
        <input class="input" name="name">

    </div>
    <div class="teacher-list-container">
        <#list teachers as t>
            <div class="teacher-container">
                <p>${t[0].firstName} ${t[0].lastName} ${t[1].specialization}</p>
            </div>
        </#list>
    </div>
</body>
</html>