<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Mutter" %>
<%
    Mutter editMutter = (Mutter) request.getAttribute("editMutter");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>投稿の編集</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #f0f2f5;
            padding: 30px;
            text-align: center;
        }
        .form-box {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            display: inline-block;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        input[type="text"] {
            width: 80%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .update-btn {
            background-color: #4CAF50;
            color: white;
        }
        .back-btn {
            background-color: #1da1f2;
            color: white;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h1>投稿の編集</h1>
    <div class="form-box">
        <!--TODO UpdateMutterのリファクタリング-->
        <form action="UpdateMutter" method="post">
            <input type="hidden" name="mutterId" value="<%= editMutter.getMutterId() %>">
            <input type="text" name="text" value="<%= editMutter.getText() %>">
            <br>
            <input type="submit" value="更新" class="update-btn">
        </form>

        <form action="MyPage" method="get">
            <input type="submit" value="マイページへ戻る" class="back-btn">
        </form>
    </div>
</body>
</html>
