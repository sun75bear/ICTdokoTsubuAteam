<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>閲覧タイプ選択</title>
        <!-- 外部CSSを読み込む -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/display.css">
</head>
<body>
    <h2 class="visit-count">
        総アクセス数: <%= request.getAttribute("totalVisits") %> 回
    </h2>
    
<% 
        // totalVisitsを取得して数値に変換
        Object totalObj = request.getAttribute("totalVisits");
        int total = 0;
        if (totalObj != null) {
            total = Integer.parseInt(totalObj.toString());
        }

        // 777のときだけ表示
        if (total == 777) {
%>
<form action="Akui" method="get">
    <button type="submit" value="akui" class="akui-button">
        おめでとうございます！総アクセス数がゾロ目のお祝いです<br>
        サイト主からご祝儀をお渡しさせていただきます。<br>
        ご希望の方は、ここをクリック💛<br>
        <span id="countdown" style="color:yellow; font-weight:bold;"></span>
    </button>
</form>

<script>
    // カウントダウン秒数
    let timeLeft = 30; // 30秒からスタート
    const countdownEl = document.getElementById("countdown");

    function updateCountdown() {
        countdownEl.textContent = "⏰ 残り時間: " + timeLeft + " 秒";

        // 10秒以下になったら文字を大きく＆赤色に変更
        if (timeLeft <= 10) {
            countdownEl.style.fontSize = "2em";   // 文字サイズを大きく
            countdownEl.style.color = "red";      // 色を赤に
        } else {
            // 通常時のスタイルに戻す
            countdownEl.style.fontSize = "1em";
            countdownEl.style.color = "yellow";
        }

        timeLeft--;

        if (timeLeft < 0) {
            countdownEl.textContent = "⏰ 時間切れ！もう一度アクセスしてください";
            clearInterval(timer);
        }
    }

    // 1秒ごとに更新
    updateCountdown();
    const timer = setInterval(updateCountdown, 1000);
</script>


<% 
    } 
%>


    <h1>閲覧タイプを選択してください</h1>
   
    
    <form action="<%= request.getContextPath() %>/Display" method="get">
        <button type="submit" name="viewType" value="keyword">キーワード検索</button><br>
        <button type="submit" name="viewType" value="ranking">ランキング</button><br>
        <button type="submit" name="viewType" value="recommend">おすすめ記事</button><br>
    </form>
    <br>
    
    
    <a href="<%= request.getContextPath() %>/Main">戻る</a>
</body>
</html>

