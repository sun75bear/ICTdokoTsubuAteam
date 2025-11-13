package model;

public class UserRanking {
    private String name;
    private int likes;
    private String content;

    public UserRanking(String name, int likes, String content) {
        this.name = name;
        this.likes = likes;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public int getLikes() {
        return likes;
    }

    public String getContent() {
        return content;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}