<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/comments.css" rel="stylesheet">
    <title>Комментарии</title>
</head>
<body>
<div class="header">
    Комментарии
</div>

<div class="comments-container">

    <#if comments?has_content>
        <#list comments as c>
            <div class="comment-card">
                <div class="comment-author">
                    ${c.studentFirstName} ${c.studentLastName}
                </div>

                <div class="comment-time">
                    ${c.createdTime}
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
    <form class="form">
        <select>

        </select>
        <textarea class="textarea"></textarea>
    </form>
</div>

</body>
</html>