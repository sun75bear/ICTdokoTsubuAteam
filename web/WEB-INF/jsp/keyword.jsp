<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>検索</title>
  <style>
    body{margin:0;padding:0;background:#f4f4f8;font-family:system-ui,"Hiragino Kaku Gothic ProN",Meiryo,sans-serif;
         display:flex;justify-content:center;align-items:center;height:100vh}
    .card{background:#fff;padding:40px 50px;border-radius:16px;box-shadow:0 8px 18px rgba(0,0,0,.12);
          text-align:center;width:380px;animation:fade .6s ease}
    h1{margin:0 0 18px;font-size:1.6rem;color:#222}
    .err{color:#d33;margin:-4px 0 10px;font-size:.92rem}
    input[type="text"]{width:100%;padding:.7rem 1rem;font-size:1rem;border:1px solid #ccc;border-radius:8px;outline:none}
    input[type="text"]:focus{border-color:#2d7ff9;box-shadow:0 0 4px rgba(45,127,249,.45)}
    button{width:100%;margin-top:14px;padding:.7rem 1rem;font-size:1rem;border:none;background:#2d7ff9;color:#fff;border-radius:8px;cursor:pointer;transition:.2s}
    button:hover{background:#1d6be2}
    p.sub{margin-top:24px;color:#777;font-size:.9rem}
    @keyframes fade{from{opacity:0;transform:translateY(8px)}to{opacity:1;transform:translateY(0)}}
  </style>
</head>
<body>
  <div class="card">
    <h1>ユーザー検索</h1>

    <% String err = (String)request.getAttribute("error"); %>
    <% if (err != null && !err.isBlank()) { %>
      <div class="err"><%= err %></div>
    <% } %>

 <form action="${pageContext.request.contextPath}/search" method="post">
  <input type="text" name="keyword" placeholder="キーワードを入力">
  <button type="submit">検索</button>
</form>
        <!-- 戻るボタン -->
    <div class="back-container">
        <form action="<%= request.getContextPath() %>/Display" method="get">
            <button type="submit" class="back-button">← 一覧へ戻る</button>
        </form>
    </div>
    <p class="sub">　　　</p>
    <p class="sub">　　　</p>
  </div>
</body>
</html>


