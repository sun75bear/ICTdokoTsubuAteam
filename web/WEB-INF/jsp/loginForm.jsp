<%-- 
    Document   : loginForm
    Created on : 2025/11/06, 14:00:47
    Author     : abi06
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ログインフォーム入力欄</h1>
        <form action="Login" method="post">
            ユーザー名<input type="text" name="name"><br>
            パスワード:<input type="password" name="pass"><br>
            <input type="submit" value="ログイン">
        </form>
    </body>
</html>
