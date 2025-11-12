<%-- 
    Document   : loginResult.jsp
    Created on : 2025/11/06, 13:51:00
    Author     : abi06
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    //ログインが失敗していれば、nullが値として取得される
    User loginUser = (User)session.getAttribute("loginUser");
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
        <h1>どこつぶログイン</h1>
        <% if(loginUser != null){ %>
            <p>ログインに成功しました</p>
            <p><%= loginUser.getName() %>さん</p>
            
            
            <a href="Main">つぶやき投稿・閲覧へ</a><br>
            

            
        <% }else{ %>
            <p>ログインに失敗しました</p>
            <a href="index.jsp">TOPへ</a>
        <% } %>
        </div>
        </div>
    </body>
</html>
