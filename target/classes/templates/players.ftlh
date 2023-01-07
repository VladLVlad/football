<!DOCTYPE html>
<html>
<head>
    <title>FC BARCELONA</title>
</head>
<body>
<h1>BARCA </h1><hr>
<#if user.email??>
    <h3>Имя пользователя: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
<#else>
    <a href="/login">Войти</a></h1>
</#if>
<hr>
<h4>Игроки ФК Барселона</h4>
<form action="/" method="get">
    Поиск по названию фамилии: <input type="text" name="surname"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list players as player>
    <div>
        <p><b>${player.surname}</b> ${player.name} | <a href="/player/${player.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Игроков нет</h3>
</#list>
<#if user.email??>
<hr>
<h3>Записать нового игрока</h3>
<form action="/player/create" method="post" enctype="multipart/form-data">
    Фамилия: <input type="text" name="surname"/><br><br>
    Имя: <input type="text" name="name"/><br><br>
    Игровой номер: <input type="number" name="playOfNumber"/><br><br>
    Возраст: <input type="number" name="age"/><br><br>
    Амплуа: <input type="text" name="role"/><br><br>
    Гражданство: <input type="text" name="nationality"/><br><br>
    Контракт: <input type="text" name="contract"/><br><br>
    Кубки: <input type="text" name="cups"/><br><br>
    Личные награды: <input type="text" name="personalAwards"/><br><br>
    Статистика: <input type="text" name="statistics"/><br><br>
    Агент: <input type="text" name="agent"/><br><br>
    Клубная карьера: <input type="text" name="clubCareer"/><br><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить игрока"/>
</form>
</#if>
</body>
</html>
