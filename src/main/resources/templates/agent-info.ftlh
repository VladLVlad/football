<!DOCTYPE html>
<html>
<head>
    <title>FC BARCELONA</title>
</head>
<body>
<h1>BARCA</h1><hr>
<h4>Подробная информация о агенте</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Фамилия: </b>${agent.surname}<br>
<b>Имя: </b>${agent.name}<br>
<hr>
<div>
<form action="/player/${agent.playerId}/agent/${agent.id}/delete" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить агента"/>
</form>
</div>
</body>
</html>