function sendLike(rank) {
    fetch(contextPath + '/like', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'rank=' + rank
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('likes-' + rank).textContent = data.likes;
    });
}
