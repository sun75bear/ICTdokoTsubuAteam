<%-- 
    Document   : osusume
    Created on : 2025/11/10, 15:02:30
    Author     : abi07
--%>

<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.Mutter"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User loginUser = (User) session.getAttribute("loginUser");
    List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
    String errorMsg = (String) request.getAttribute("errorMsg");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>どこつぶ</title>
    <style>
        .reaction-button {
            padding: 5px 10px;
            margin: 0 5px;
            font-size: 14px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .good-button {
            background-color: #4CAF50;
            color: white;
        }

        .bad-button {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>
    <h1>おすすめ記事</h1>

    <h2>ランダムおすすめ</h2>

    <% if (mutterList != null && mutterList.size() > 0) {
       List<Mutter> randomList = new ArrayList<>(mutterList);
       Collections.shuffle(randomList);
       int displayCount = Math.min(2, randomList.size());
       for (int i = 0; i < displayCount; i++) {
           Mutter mutter = randomList.get(i); %>
        <div style="margin-bottom: 1em; border-bottom: 1px solid #ccc; padding-bottom: 10px;">
            <p>
                <strong><%= mutter.getUsername() %></strong>：<%= mutter.getText() %><br>
                👍 いいね＝<%= mutter.getGood() %>　
                👎 バッド＝<%= mutter.getBad() %>
            </p>

            <!-- いいねボタン -->
            <form action="GoodBadServlet" method="post" style="display:inline;">
                <input type="hidden" name="mutterId" value="<%= mutter.getMutterId() %>">
                <input type="hidden" name="action" value="good">
                <button type="submit" class="reaction-button good-button">👍 いいね</button>
            </form>

            <!-- バッドボタン -->
            <form action="GoodBadServlet" method="post" style="display:inline;">
                <input type="hidden" name="mutterId" value="<%= mutter.getMutterId() %>">
                <input type="hidden" name="action" value="bad">
                <button type="submit" class="reaction-button bad-button">👎 バッド</button>
            </form>
        </div>
    <%   }
       } else { %>
        <p>おすすめのつぶやきはありません。</p>
    <% } %>

    <% if (errorMsg != null) { %>
        <p style="color:red;"><%= errorMsg %></p>
    <% } %>
            <!-- もどるボタン -->
            <div style="text-align: center; margin-top: 30px;">
                <form action="<%= request.getContextPath() %>/Display" method="get">
                    <button type="submit" style="padding: 10px 20px; font-size: 16px; border-radius: 5px; background-color: #2196F3; color: white; border: none; cursor: pointer;">
                        ← もどる
                    </button>
                </form>
            </div>   
</body>
</html>