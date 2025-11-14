package model;

public class Post {

    private long id;         // 投稿ID
    private String author;   // 投稿者名 (個人ページのキー)
    private String content;  // 本文
    private int likes;       // いいね数
    private int bads;        // バッド数
    private String createdAt; // 投稿作成日時 (表示用の文字列)

    public Post() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getBads() {
        return bads;
    }
    public void setBads(int bads) {
        this.bads = bads;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
