package am.epam.api.common;

import am.epam.api.providers.facebook.Likes;
import am.epam.api.providers.facebook.Posts;

public class Wrapper {
    private Posts posts;
    private Likes likes;

    public Wrapper(Posts posts, Likes likes) {
        this.posts = posts;
        this.likes = likes;
    }

    public Wrapper() {
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }
}
