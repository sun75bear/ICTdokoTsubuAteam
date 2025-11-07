<%-- 
    Document   : Main
    Created on : 2025/11/07, 9:15:46
    Author     : abi06
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //セッションスコープからUser情報を取得
    User loginUser = (User)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>どこつぶメイン画面</title>
    </head>
    <body>
        <h1>メイン画面だよ！</h1>
        <p><%= loginUser.getName() %>さんがログインしてます！</p>
        <a href="Logout">ログアウトボタン！</a>
    </body>
</html>
