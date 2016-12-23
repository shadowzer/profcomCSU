<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 19.12.2016
  Time: 1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createUser</title>
</head>
<body>
    <form method="post" action="/admin/user/create">
        Логин:<input type="text" name="login" maxlength="50"/><br>
        Пароль:<input type="password" name="password" maxlength="50"/><br>
        Имя:<input type="text" name="firstname" maxlength="50"/><br>
        Отчество:<input type="text" name="lastname" maxlength="50"/><br>
        Фамилия:<input type="text" name="surname" maxlength="50"/><br>
        Группа:<input type="text" name="studentgroup" maxlength="50"/><br>
        Бюджет:<select name="fulltime" size="1">
            <option  value="true">бюджет</option>
            <option  value="false">не бюджет</option>
        </select><br>
        Форма обучения:<select name="budget" size="1">
            <option value="true">Очная</option>
            <option value="false">Заочная</option>
        </select><br>
        <input type="submit" value="Отправить">
     </form>
    <a href="/admin/user">Назад</a><br>
    <a href="/">На главную</a><br>
</body>
</html>
