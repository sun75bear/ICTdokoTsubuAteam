<!-- TODO ５００だけど互換性を取るのが難しいからこのまま。検索を押すと飛べることだけ確認して終了。-->
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.Post"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>個人ページ</title>
  <style>
    body{margin:0;padding:20px;background:#f4f4f8;font-family:system-ui,"Hiragino Kaku Gothic ProN",Meiryo,sans-serif}
    .wrap{max-width:860px;margin:0 auto}
    .board{background:#fff;border-radius:16px;box-shadow:0 8px 18px rgba(0,0,0,.12);padding:24px 28px;margin-bottom:16px;display:flex;align-items:center;gap:16px}
    .badge{background:none;color:#ffcc00;font-size:3rem;animation:fuwafuwa 1.6s infinite ease-in-out}
    @keyframes fuwafuwa{0%{transform:translateY(0)}50%{transform:translateY(-6px)}100%{transform:translateY(0)}}
    h1{margin:0;font-size:1.4rem}
    .form{background:#fff;border-radius:16px;box-shadow:0 8px 18px rgba(0,0,0,.12);padding:20px 24px;margin-bottom:16px}
    textarea{width:100%;min-height:90px;padding:.7rem 1rem;border:1px solid #ccc;border-radius:10px;font-size:1rem;outline:none}
    textarea:focus{border-color:#2d7ff9;box-shadow:0 0 4px rgba(45,127,249,.45)}
    .btn{padding:.55rem .9rem;border:none;border-radius:8px;cursor:pointer;color:#111;background:#2d7ff9}
    .btn:hover{background:#1d6be2}
    .list{display:grid;grid-template-columns:1fr 1fr;gap:12px}
    .post{background:#fff;border-radius:14px;box-shadow:0 6px 14px rgba(0,0,0,.1);padding:16px 18px}
    .meta{font-size:.85rem;color:#666;margin-bottom:8px}
    .actions form{display:inline}
    .pill{display:inline-block;padding:.2rem .5rem;border-radius:999px;background:#eef2ff;margin-left:6px;font-size:.8rem}
    .link{display:inline-block;margin-top:10px;text-decoration:none;color:#2d7ff9}
    .flash{color:#d33;margin-bottom:10px}
  </style>
</head>
<body>
<div class="wrap">

  <!-- 看板 -->
  <div class="board">
    <div class="badge">😊</div>
    <h1><%= request.getParameter("name") %> さんのページ</h1>
  </div>

  <!-- 投稿フォーム -->

  <!-- 投稿一覧 -->
  <div class="list">
  <%
    List<Post> posts = (List<Post>) request.getAttribute("posts");
    if (posts == null || posts.isEmpty()) {
  %>
      <div class="post"><div class="meta">まだ投稿がありません。</div></div>
  <%
    } else {
      for (Post p : posts) {
        // ★ EL から参照できるように p をページ属性へ
        pageContext.setAttribute("p", p);
  %>
      <div class="post">
        <div class="meta">No.<%= p.getId() %> ／ <%= p.getAuthor() %> さん ／ <%= p.getCreatedAt() %></div>

        <!-- ★ XSS対策：c:out が HTML をエスケープ。改行は CSS で表示（<br>変換不要） -->
        <div style="white-space: pre-wrap;"><c:out value="${p.content}"/></div>

        <div class="actions" style="margin-top:8px">
          <form action="<%= request.getContextPath() %>/person" method="post">
            <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
            <input type="hidden" name="action" value="react">
            <input type="hidden" name="type" value="like">
            <input type="hidden" name="id" value="<%= p.getId() %>">
            <button class="btn" type="submit">イイネ <span class="pill"><%= p.getLikes() %></span></button>
          </form>
          <form action="<%= request.getContextPath() %>/person" method="post">
            <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
            <input type="hidden" name="action" value="react">
            <input type="hidden" name="type" value="bad">
            <input type="hidden" name="id" value="<%= p.getId() %>">
            <button class="btn" type="submit" style="background:#e45757">バット <span class="pill"><%= p.getBads() %></span></button>
          </form>
        </div>
      </div>
  <%
      } // for
    }   // else
  %>
  </div>

</div>
</body>
</html>
