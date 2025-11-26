<%--
    Document : main (修正済み)
    Created on : 2025/11/07, 10:07:05
    Author : teacher
--%>
<%@page import="model.Mutter"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    User loginUser = (User)session.getAttribute("loginUser");
    // ★ nullチェックはそのまま維持 ★
    if (loginUser == null) {
        response.sendRedirect("Login");
        return;
    }
    List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
    String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>どこつぶメイン</title>
        <link rel="stylesheet" href="css/myPage.css">
        <link href="style/login.css" rel="stylesheet">
    </head>
    <body>
        
        <h1>どこつぶメイン</h1>

        <div class="user-info">
            <p>
                <%= loginUser.getName() %>さん（ID: <%= loginUser.getUserId() %>）、ログイン中
            </p>
            <a href="Logout">ログアウト</a>
        </div>
        
        <p class="refresh-link">
            <a href="Main">更新</a>
        </p>

        <div class="navigation-links">
            <a href="Display">検索閲覧</a> <br>
            <a href="MyPage">マイページ</a>
        </div>
        
        <div class="post-form">
            <form action="Main" method="post">
                <input type="text" name="text" placeholder="今、どこつぶ？">
                <input type="submit" value="つぶやく">
            </form>
        </div>
        
        <%-- エラーメッセージ表示 --%>
        <% if(errorMsg != null){%>
        <p class="error-msg"><%= errorMsg %></p>
        <% } %>
        
        <div class="mutter-list">
        <% if (mutterList != null) { %>
            <% for(Mutter mutter : mutterList) {%>
                <div class="mutter-card">
                    <div class="mutter-header">
                        <strong class="mutter-username"><%= mutter.getUsername() %></strong>
                        <span class="mutter-id">投稿ID: <%= mutter.getMutterId() %></span>
                    </div>

                    <p class="mutter-text"><%= mutter.getText() %></p>

                    <div class="mutter-stats">
                        👍 <%= mutter.getGood() %>　
                        👎 <%= mutter.getBad() %>
                    </div>
                    
                    <div class="mutter-actions">
                    </div>
                </div>
            <% } %>
        <% } %>
        </div>

        <button id="backToTop">↑</button>
        <script>
        // スクロールイベントでボタン表示切り替え
        window.addEventListener("scroll", function() {
            const button = document.getElementById("backToTop");
            if (window.scrollY > 300) { // 300px以上スクロールしたら表示
                button.style.display = "block";
            } else {
                button.style.display = "none";
            }
        });

        // ボタンクリックでトップへスムーズスクロール
        document.getElementById("backToTop").addEventListener("click", function() {
            window.scrollTo({ top: 0, behavior: "smooth" });
        });
        </script>
    </body>
</html>