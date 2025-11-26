// レート制限のために、最後にいいねが送信された時刻を保持する変数
let lastLikeTime = 0;
// 制限時間 (10秒)
const rateLimitTime = 10000; // 10000ミリ秒 = 10秒

function sendLike(mutterId) {
    // 1. レート制限のチェック
    const currentTime = new Date().getTime();

    if (currentTime - lastLikeTime < rateLimitTime) {
        // 制限時間内に再度クリックされた場合
        console.warn('いいねは10秒間に1回しかできません。');
        // 必要であれば、ユーザーに通知する処理（例: alert('少し待ってください')）を追加
        return; // 処理を中断
    }

    // 制限をクリアした場合、最終クリック時刻を更新
    lastLikeTime = currentTime;

    // 2. サーバーへのリクエスト送信 (いいね増加ロジックはサーバー側で実装)
    fetch(contextPath + '/LikeServlet', { // 💡 パスを'/LikeServlet'など適切なものに変更することを推奨
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        // サーバー側でMutterを特定するためにIDを渡す必要があります。
        // mutterIdではなくMutter IDを渡すことを強く推奨します。
        body: 'id=' + mutterId // mutterIdがMutter IDを兼ねていると仮定
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('サーバーエラーが発生しました。');
        }
        return response.json();
    })
    .then(data => {
        // 3. サーバーから返された新しいいいね数を表示に反映
        if (data && data.likes !== undefined) {
            document.getElementById('likes-' + mutterId).textContent = data.likes;
            console.log(`Mutter ID ${mutterId} のいいねが ${data.likes} に更新されました。`);
        } else {
            console.error('サーバーからの応答データが不正です。', data);
        }
    })
    .catch(error => {
        console.error('いいねの送信中にエラーが発生しました:', error);
        // エラー発生時は、レート制限の時刻をリセットするかどうか検討が必要です
        // lastLikeTime = 0;
    });
}