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
<b>Фамилия: </b>${player.surname}<br>
<b>Имя: </b>${player.name}<br>
<b>Игровой номер: </b>${player.playOfNumber}<br>
<b>Возраст: </b>${player.age}<br>
<b>Амплуа: </b>${player.role}<br>
<b>Гражданство: </b>${player.nationality}<br>
<b>Контракт: </b>${player.contract}<br>
<b>Кубки: </b>${player.cups}<br>
<b>Личные награды: </b>${player.personalAwards}<br>
<b>Статистика: </b>${player.statistics}<br>
<b>Агент: </b>${player.agent}<br>
<b>Клубная карьера: </b>${player.clubCareer}<br>
<hr>
<form action="/player/delete/${player.id}" method="post">
    <input type="submit" value="Удалить игрока"/>
</form>
</body>
</html>