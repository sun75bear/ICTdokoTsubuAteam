<%@page import="model.Mutter"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User loginUser = (User)session.getAttribute("loginUser");
    List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>どこつぶ</title>    
               
        <link rel="stylesheet" href="css/myPage.css">      
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
        
        <div class="post-form">
            <form action="Main" method="post">
                <input type="text" name="text" placeholder="今、どこつぶ？">
                <input type="submit" value="つぶやく">
            </form>
        </div>
        
        <div class="mutter-list">
    <% for(Mutter mutter : mutterList){ %>
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
                <form action="EditMutter" method="post" class="action-form">
                    <input type="hidden" name="mutterId" value="<%= mutter.getMutterId() %>">
                    <input type="submit" value="編集" class="edit-button">
                </form>
                <form action="DeleteMutter" method="post" class="action-form" 
                      onsubmit="return confirm('本当に削除しますか？');">
                    <input type="hidden" name="mutterId" value="<%= mutter.getMutterId() %>">
                    <input type="submit" value="削除" class="delete-button">
                </form>
            </div>
        </div>
                    
                    
    <% } %>
</div>

        <!-- ★ ここに追加！ -->
        <div class="back-button">
    <form action="Main" method="get">
        <input type="submit" value="メインページに戻る">
    </form>
</div>


    </body>
</html>
