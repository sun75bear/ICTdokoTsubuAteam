<%-- 
    Document   : Main
    Created on : 2025/11/07, 9:15:46
    Author     : abi06
--%>

<%@page import="java.util.List"%>
<%@page import="model.Mutter"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //セッションスコープからUser情報を取得
    User loginUser = (User)session.getAttribute("loginUser");
%>
<%
    //アプリケーションスコープからmutterListを取得
    //TODO USERごとにmutterListを管理して、1Userごとに1listを表示させる
    //Mutterにuser_idを追加して、必要なidを持つMutterを表示できるようにする
    //Mutteridは投稿順で管理する→そのシステムは削除に対応するのが難しい、post時に一意のIDを取得して振っておくべき
    List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
%>

<%
    //
    String errorMsg = (String)request.getAttribute("errorMsg");
%>
<%
    //このページはログインユーザーが過去の投稿を編集したり削除できるページ
    //TODO （編集サーブレットと）削除サーブレットと各ロジックを準備する。
    //編集サーブレットは、編集ロジックを実行してこのページに戻ってくる奴
    //削除サーブレットは、削除ロジックを実行してこのページに戻ってくる奴
%>
<%
    //いいね、よくないねをコントロールするデータ
    //ボタンをクリックすると、いいね、よくないねをMutterに格納する
    ////一人１回、いいねよくないねをできる（発展課題）
    //いいねpost、よくないねpostを受けて、Mutterのいいねよくないね数値を変更するロジック（渡辺
    //ロジックを処理して返すサーブレット（渡辺
%>
<%
    //検索に関する必要データ、List<Mutter>
    //検索文字列と、postするフォーム
    //postを受けて検索を行うロジック（城山
    //postとロジックを処理して返すサーブレット（神田
%>
<%
    //ランキングに関する必要データ、List<Mutter>
    //全体のいい値を収集して、TOP３（仮）を表示するロジック（八反地
    //ロジックを処理して返すサーブレット（神田
%>
<%
    //おみくじ表示に関する必要データ、List<Mutter>
    //全てのつぶやきを収集して、ランダムに３つ表示するロジック（荒木
    //ロジックを処理して返すサーブレット（神田
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>どこつぶメイン画面</title>
    </head>
    <body>
        <h1>メイン画面だよ！</h1>
        <p><%= loginUser.getName() %>さん、ID=<%= loginUser.getId()%>がログインしてます！</p>
        <article>
            <a href="/Main">更新</a><br>
            <form action="Main" method="post">
                <input type="text" name="text">
                <input type="submit" value="つぶやく">
            </form>
            <a href="/hoge">閲覧履歴</a><br>
        </article>
        <div>
            <% for (Mutter mutter : mutterList){%>
                <p>
                    <%= mutter.getId() %>：<%= mutter.getUserName() %>：<%= mutter.getText() %>：いいね＝<%= mutter.getGood()%>：よくないね＝<%=mutter.getBad()%>
                </p>
            <%}%>
        </div>
        <a href="Logout">ログアウトボタン！</a>
    </body>
</html>
