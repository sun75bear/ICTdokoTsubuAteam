<%@page import="model.Mutter"%>
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
    List<Mutter> textHit = (List<Mutter>) request.getAttribute("textHit");
    if (textHit == null || textHit.isEmpty()) {
  %>
    <p class="empty">該当する投稿は見つかりませんでした。</p>
    <p><a class="link" href="Keyword">← 検索画面に戻る</a></p>
  <%
    } else {
  %>
    <div class="grid">
      <%
        //requestスコープから取得したkeywordを利用する
        String kw = (String) request.getAttribute("keyword");
        for (Mutter m : textHit) {
          //<mark>で修飾されたkwは蛍光ペンでハイライトしたような表示が入る
          //処理した文字列snを、つぶやき本文として表示する
          //処理不明で保留、mをそのまま用いる
          //String sn = m.replace(kw, "<mark>" + kw + "</mark>");
      %>
        <div class="card">
          <div class="meta"><strong><%= m.getUsername() %></strong> さん ／ <%// m.getCreatedAt() %>  No.<%= m.getMutterId() %></div>
          <div class="snippet"><%= m.getText() %></div>
<a class="link"
  href="<%= request.getContextPath() %>/person?name=<%= java.net.URLEncoder.encode(m.getUsername(), "UTF-8") %>&q=<%= java.net.URLEncoder.encode(kw, "UTF-8") %>#post-<%= m.getMutterId() %>">
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