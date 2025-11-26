<%-- 
    Document   : ranking
    Created on : 2025/11/11, 9:16:23
    Author     : abi05
--%>

<%@page import="model.User"%>
<%@page import="model.Mutter"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, model.UserRanking" %>

<%
    //List<UserRanking> rankingList = (List<UserRanking>) request.getAttribute("rankingList");
    //if (rankingList == null) {
     //   rankingList = new ArrayList<>();
    //}
    User loginUser = (User)session.getAttribute("loginUser");
    List<Mutter> rankingList = (List<Mutter>)application.getAttribute("mutterList");
    String errorMsg = (String)request.getAttribute("errorMsg");
    //アプリケーションスコープのリストをコピーして、いい値が多い順に並べなおしたリストsortedRankingListを作る
    //sortedRankingListの１・２・３番目を１位２位３位として表示、その後についてもrankingListを順番に表示させる
    
    // Listをコピーしてソートする（元のapplicationスコープのリストを変更しないため）
    List<Mutter> sortedRankingList = new ArrayList<>(rankingList);
    
    if (rankingList != null) {

        // Comparatorを使用して、getGood()の値が大きい順（降順）にソート
        Collections.sort(sortedRankingList, new Comparator<Mutter>() {
            @Override
            public int compare(Mutter m1, Mutter m2) {
                // 降順ソート: m2 (次の要素) の方が m1 (前の要素) より大きければ (いいねが多ければ) 正の値
                return m2.getGood() - m1.getGood();
            }
        });        
    } else {
        // rankingListリストがnullの場合に備えて空のリストをセット
        sortedRankingList = new ArrayList<>();
    }
    // ----------------------------------------------------
%>

<html>
<head>
    <title>投稿ランキング</title>
    <link href="https://fonts.googleapis.com/css2?family=Baloo+2&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/ranking.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <script>
        const contextPath = '<%= request.getContextPath() %>';
    </script>
    <script src="<%= request.getContextPath() %>/js/like.js"></script>

</head>
<body>
    <h1>投稿ランキング</h1>
    
        <!-- 戻るボタン -->
    <div class="back-container">
        <form action="<%= request.getContextPath() %>/Display" method="get">
            <button type="submit" class="back-button">← 一覧へ戻る</button>
        </form>
    </div>
    <div class="card-container">
    <%
    int rank = 1;
    System.out.println("int");
    //    for (UserRanking user : sortRankingList) {
    //UserRankin usreは、Mutter utterのこと
    //sortedRankingListを順番に表示する
    for (Mutter mutter : sortedRankingList){
        System.out.println("for");
        String rankClass = "";
        String iconHtml = "";
        if (rank == 1) {
            System.out.println("rank1");
            rankClass = "gold";
            iconHtml = "<i class='fas fa-crown'></i>";
        } else if (rank == 2) {
            rankClass = "silver";
            iconHtml = "<i class='fas fa-medal' style='color:silver;'></i>";
        } else if (rank == 3) {
            rankClass = "bronze";
            iconHtml = "<i class='fas fa-medal' style='color:#cd7f32;'></i>";
        }
        System.out.println("endfor");
    %>
        <div class="card <%= rankClass %>">
            <div class="rank"><%= iconHtml %> 第<%= rank %>位</div>
            <div class="username">👤 <%= mutter.getUsername() %></div>
            <div class="likes">
                <button onclick="sendLike(<%= rank %>)">👍</button>
                <span id="likes-<%= rank %>"><%= mutter.getGood() %></span> いいね                
            </div>
            <div class="content">📝 <%= mutter.getText() %></div>
        </div>

    <%
        //順位のインクリメント
        rank++;
        // forループ終了
    }
    %>
    </div>
</body>
</html>
