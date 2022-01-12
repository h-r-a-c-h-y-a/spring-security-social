package am.epam.api.providers.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Like {

    private String id;
    private String name;
    @JsonProperty("created_time")
    private String createdTime;

    public Like() {
    }

    public Like(String id, String name, String createdTime) {
        this.id = id;
        this.name = name;
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
