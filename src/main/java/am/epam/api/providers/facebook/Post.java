package am.epam.api.providers.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Post {

	private String id;
	@JsonProperty("created_time")
	private String createdTime;
	private String message;

	public Post() {}

	public Post(String id, String createdTime, String message) {
		this.id = id;
		this.createdTime = createdTime;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Post post = (Post) o;
		return Objects.equals(id, post.id) && Objects.equals(createdTime, post.createdTime) && Objects.equals(message, post.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, createdTime, message);
	}
}
