<%-- 
    Document   : result
    Created on : 2025/11/10, 14:28:01
    Author     : abi03
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>検索結果</title>
  <style>
    body{margin:0;padding:0;height:100vh;background:#f4f4f8;
         font-family:system-ui,"Hiragino Kaku Gothic ProN",Meiryo,sans-serif;display:flex;align-items:center;justify-content:center}
    .card{width:520px;max-width:92vw;background:#fff;border-radius:16px;box-shadow:0 8px 18px rgba(0,0,0,.12);padding:28px 32px;animation:fade .5s ease}
    h1{margin:0 0 10px;font-size:1.4rem;color:#222}
    .kw{color:#2d7ff9;font-weight:700}
    ul{margin:14px 0 0 1.2rem;line-height:1.8}
    a.btn{display:inline-block;margin-top:18px;text-decoration:none;background:#2d7ff9;color:#fff;padding:.6rem 1rem;border-radius:8px}
    a.btn:hover{background:#1d6be2}
    @keyframes fade{from{opacity:0;transform:translateY(8px)}to{opacity:1;transform:translateY(0)}}
  </style>
</head>
<body>
  <div class="card">
    <h1>検索結果</h1>
    <% String kw = (String)request.getAttribute("keyword"); %>
    <p>キーワード：<span class="kw"><%= (kw == null || kw.isBlank()) ? "（未入力）" : kw %></span></p>

    <%
      List<String> results = (List<String>) request.getAttribute("results");
      if (results == null || results.isEmpty()) {
    %>
        <p>該当する結果がありませんでした。</p>
    <%
      } else {
    %>
        <ul>
        <%
          for (String r : results) {
        %>
            <li><%= r %></li>
        <%
          }
        %>
        </ul>
    <%
      }
    %>

    <p><a class="btn" href="<%= request.getContextPath() %>/index.jsp">← 検索に戻る</a></p>
  </div>
</body>
</html>
