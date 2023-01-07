<!DOCTYPE html>
<html>
<head>
    <title>FC BARCELONA</title>
</head>
<body>
<h1>BARCA</h1><hr>
<h4>Подробная информация о игроке</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<form action="/player/${player.id}/update" method="post" enctype="multipart/form-data">
<b>Фамилия: </b>${player.surname}<br>
<b>Имя: </b>${player.name}<br>
<b>Игровой номер: </b> <input type="text" name="playOfNumber" value=${player.playOfNumber} /><br>
<b>Возраст: </b><input type="text" name="age" value=${player.age} /><br>
<b>Амплуа: </b><input type="text" name="role" value=${player.role} /><br>
<b>Гражданство: </b>${player.nationality}<br>
<b>Контракт: </b>${player.contract}<br>
<b>Кубки: </b>${player.cups}<br>
<b>Личные награды: </b>${player.personalAwards}<br>
<b>Статистика: </b>${player.statistics}<br>
<b>Клубная карьера: </b>${player.clubCareer}<br>
<#if player.agentId??>
     <b>Агент: </b><a href="/player/${player.id}/agent/${player.agentId}">${agent.name}</a></h1><br>
</#if>

<input type="hidden" name="_csrf" value="${_csrf.token}">
<input type="submit" value="Обновить игрока"/>
</form>
<form action="/player/delete/${player.id}" method="post">
 <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить игрока"/>
</form>
</div>
<hr>
<div>
<#if player.agentId??>
<#else>
<form action="/player/${player.id}/agent" method="post" enctype="multipart/form-data">
    Фамилия: <input type="text" name="surname"/><br><br>
    Имя: <input type="text" name="name"/><br><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить агента"/>
</form>
</#if>
</div>
</body>
</html>