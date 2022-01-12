package am.epam.api.providers.facebook;

import java.util.List;

public class Likes {
    private List<Like> data;

    public Likes() {
    }

    public Likes(List<Like> data) {
        this.data = data;
    }

    public List<Like> getData() {
        return data;
    }

    public void setData(List<Like> data) {
        this.data = data;
    }
}
