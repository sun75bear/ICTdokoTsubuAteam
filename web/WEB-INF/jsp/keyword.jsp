<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title>検索メニュー</title>
    <%-- 外部CSSファイルを読み込みます --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/searchMenu.css">
</head>
<body>
    <div class="hero"><div class="logo">🔎</div><h1>検索メニュー</h1></div>

    <% String err = (String)request.getAttribute("error"); %>
    <% if (err != null && !err.isBlank()) { %><div class="err"><%= err %></div><% } %>

    <div class="timeline">
        <div class="node"><div class="num">①</div><div class="pin"></div></div>
        <div class="card">
            <h2><span class="tag">NAME</span> 名前の検索</h2>
            <p class="desc">氏名で検索</p>
            <form action="search" method="post">
                <input type="hidden" name="mode" value="name">
                <input class="field" type="text" name="keyword" placeholder="例：奥本 / 渡辺 / 長谷川 …" required autofocus>
                <button class="btn" type="submit">氏名検索結果ページへ</button>
            </form>
        </div>

        <div class="node"><div class="num">②</div><div class="pin"></div></div>
        <div class="card">
            <h2><span class="tag green">SEARCH</span> つぶやき本文の検索</h2>
            <p class="desc">自由語で検索して、結果一覧ページで確認します。</p>
            <form action="search" method="post">
                <input type="hidden" name="mode" value="general">
                <input class="field" type="text" name="q" placeholder="例：設備 申請 / 会議メモ / 連絡事項 …">
                <button class="btn green" type="submit">結果を表示</button>
            </form>
        </div>
       <div class="back-button-container">
        <form action="<%= request.getContextPath() %>/Display" method="get">
            <button type="submit" class="back-button">← 一覧へ戻る</button>
        </form>
        </div> 
    </div>
</body>
</html>