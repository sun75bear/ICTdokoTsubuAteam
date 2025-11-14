<%@page import="model.Mutter"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.Post"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>個人ページ</title>
  <%-- 外部CSSファイルを読み込みます --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/person.css">
</head>
<body>
<div class="wrap">

  <div class="board">
    <div class="badge">😊</div>
    <h1><%= request.getParameter("name") %> さんで検索した結果</h1>
  </div>

  <div class="list">
  <%
    //personで抽出したnameHitリストをリクエストスコープから取得する
    List<Mutter> nameHit = (List<Mutter>) request.getAttribute("nameHit");
    //PostがMutterだから、ここを全部Mutterに置き換える
    if (nameHit == null || nameHit.isEmpty()) {
  %>
      <div class="post"><div class="meta">投稿はありません。</div></div>
  <%
    } else {
      for (Mutter mutter : nameHit) {
        // ★ EL から参照できるように mutter をページ属性"p"へ
        pageContext.setAttribute("p", mutter);
  %>
      <div class="post">
        <div class="meta"> <%= mutter.getUsername() %> さん ／ No.<%= mutter.getMutterId() %>  <%//=mutter.getCreatedAt() %></div>

        <div style="white-space: pre-wrap;"><%= mutter.getText() %></div>

        <div class="actions" style="margin-top:8px">
          <form action="<%= request.getContextPath() %>/person" method="post">
            <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
            <input type="hidden" name="action" value="react">
            <input type="hidden" name="type" value="like">
            <input type="hidden" name="id" value="<%= mutter.getMutterId() %>">
            <button class="btn" type="submit">イイネ <span class="pill"><%= mutter.getGood() %></span></button>
          </form>
          <form action="<%= request.getContextPath() %>/person" method="post">
            <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
            <input type="hidden" name="action" value="react">
            <input type="hidden" name="type" value="bad">
            <input type="hidden" name="id" value="<%= mutter.getMutterId() %>">
            <%-- style="background:#e45757" はインラインで残すか、CSSでボタンを分離する必要があります。今回はインラインで残します。 --%>
            <button class="btn" type="submit" style="background:#e45757">バット <span class="pill"><%= mutter.getBad() %></span></button>
          </form>
        </div>
      </div>
  <%
      } // for
    }   // else
  %>
      <p style="margin-top:14px"><a class="link" href="keyword">← 検索に戻る</a></p>
  </div>

</div>
</body>
</html>