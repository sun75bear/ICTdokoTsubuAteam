<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.SearchHit"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>検索結果</title>
  <%-- 外部CSSファイルを読み込みます --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/textResult.css">
</head>
<body>
<div class="wrap">
  <h1>検索結果：<span class="kw"><%= request.getAttribute("keyword") %></span></h1>

  <%
    //requestスコープのhitsからテキスト検索の結果を引っ張ってくる、null or notnull
    List<SearchHit> hits = (List<SearchHit>) request.getAttribute("hits");
    if (hits == null || hits.isEmpty()) {
  %>
    <p class="empty">該当する投稿は見つかりませんでした。</p>
    <p><a class="link" href="Keyword">← 検索画面に戻る</a></p>
  <%
    } else {
  %>
    <div class="grid">
      <%
        //requestスコープから取得した
        String kw = (String) request.getAttribute("keyword");
        for (SearchHit h : hits) {
          // スニペット中のキーワードをハイライト（簡易）
          //<mark>で修飾されたkwは蛍光ペンでハイライトしたような表示が入る
          //処理した文字列snを、つぶやき本文として表示する
          String sn = h.getSnippet().replace(kw, "<mark>" + kw + "</mark>");
      %>
        <div class="card">
          <div class="meta"><strong><%= h.getName() %></strong> さん ／ <%= h.getCreatedAt() %> ／ No.<%= h.getPostId() %></div>
          <div class="snippet"><%= sn %></div>
<a class="link"
  href="<%= request.getContextPath() %>/person?name=<%= java.net.URLEncoder.encode(h.getName(), "UTF-8") %>&q=<%= java.net.URLEncoder.encode(kw, "UTF-8") %>#post-<%= h.getPostId() %>">
  個人ページで表示
</a>


        </div>
      <%
        }
      %>
    </div>
    <p style="margin-top:14px"><a class="link" href="keyword">← 検索に戻る</a></p>
  <%
    }
  %>
</div>
</body>
</html>