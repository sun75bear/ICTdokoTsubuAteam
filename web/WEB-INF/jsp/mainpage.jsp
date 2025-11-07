<%-- 
    Document   : Main
    Created on : 2025/11/07, 9:15:46
    Author     : abi06
--%>

<%@page import="java.util.List"%>
<%@page import="model.Mutter"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //セッションスコープからUser情報を取得
    User loginUser = (User)session.getAttribute("loginUser");
%>
<%
    //アプリケーションスコープからmutterListを取得
    List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
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
        <article>
            <a href="Main">画面更新ボタン</a><br>
            <form action="Main" method="post">
                <input type="text" name="text">
                <input type="submit" value="つぶやく">
            </form>
        </article>
        <div>
            <% for (Mutter mutter : mutterList){%>
                <p>
                    <%= mutter.getUserName() %>：<%= mutter.getText() %>
                </p>
            <%}%>
        </div>
        <a href="Logout">ログアウトボタン！</a>
    </body>
</html>
