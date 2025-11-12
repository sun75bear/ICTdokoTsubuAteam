<%-- 
    Document   : main
    Created on : 2025/11/07, 10:07:05
    Author     : teacher
--%>
<%@page import="model.Mutter"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    User loginUser = (User)session.getAttribute("loginUser");
    List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
    String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/login.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>どこつぶ</title>
    </head>
    <body>
        <div id="container">
        <div id="incontainer">
        <h1>ようこそ<%= loginUser.getName() %>さん</h1>
        <p>
            <a href="Logout">ログアウト</a>
        </p>
        <p><a href="Main">更新</a></p>
        <a href="Display">検索閲覧</a> <br>
        ///eharaリンクを追加する
        <form action="Main" method="post">
            <input type="text" name="text">
            <input type="submit" value="つぶやく">
        </form>
        
        <% if(errorMsg != null){%>
        <P><%= errorMsg %></p>
        <% } %>
        <% for(Mutter mutter : mutterList) {%>
            <p><%= mutter.getUsername() %>：<%= mutter.getText() %>:<%= mutter.getmutterId() %></p>
        <% } %>
        </div>
        </div>
    </body>
</html>
