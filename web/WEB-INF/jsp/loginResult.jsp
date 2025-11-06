<%-- 
    Document   : loginResult.jsp
    Created on : 2025/11/06, 13:51:00
    Author     : abi06
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // ユーザー情報をセッションから取得
    User loginUser = (User)session.getAttribute("loginUser");
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>どこつぶ</title>
    </head>
    <body>
        <h1>どこつぶログイン画面</h1>
        //セッション情報を判定して、成功失敗を判断
    <% if (loginUser != null){   %>
        <p>ログインに成功しました！</p>
        <a href="Main"> つぶやき投稿・閲覧へ </a>
        }
    <% }else{%>
        <p>ログインに失敗しました！</p>
        <a href="index.jsp">トップへ戻る</a>    
    <%}%>
    </body>
</html>
