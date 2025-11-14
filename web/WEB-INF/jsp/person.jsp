<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.Post"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>個人ページ</title>
  <%-- 外部CSSファイルを読み込みます --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/personResult.css">
</head>
<body>
<div class="wrap">

  <div class="board">
    <div class="badge">😊</div>
    <h1><%= request.getParameter("name") %> さんで検索した結果</h1>
  </div>

  <div class="list">
  <%
    //nameでの検索結果を受けて抽出されたリストを表示している
    //application対処であれば、nameをresponseスコープに格納してapplicationスコープから検索する？
    //今回はボタンを押すと飛べるから、そこの対処と兼ね合いがある、検索対象であるテキストを載せられるようにするか、このまま抽出リストを使い続けるか
    //段階を踏むから、抽出リスト方法で行った方がいい気がする
    //いい値の問題を抜きにすれば、このページで編集などの操作を行う事がないから、applicationスコープにデータを保持し、格納操作する必要がないはず
    List<Post> posts = (List<Post>) request.getAttribute("posts");
    //PostがMutterだから、ここを全部Mutterに置き換える
    if (posts == null || posts.isEmpty()) {
  %>
      <div class="post"><div class="meta">投稿はありません。</div></div>
  <%
    } else {
      for (Post p : posts) {
        // ★ EL から参照できるように p をページ属性へ
        pageContext.setAttribute("p", p);
  %>
      <div class="post">
        <div class="meta">No.<%= p.getId() %> ／ <%= p.getAuthor() %> さん ／ <%= p.getCreatedAt() %></div>

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
            <%-- style="background:#e45757" はインラインで残すか、CSSでボタンを分離する必要があります。今回はインラインで残します。 --%>
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