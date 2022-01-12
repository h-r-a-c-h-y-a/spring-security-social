package am.epam.api.providers.facebook;

import java.util.List;
import java.util.Objects;

public class Posts {

	public Posts() {
	}

	public Posts(List<Post> data) {
		this.data = data;
	}

	public List<Post> getData() {
		return data;
	}

	public void setData(List<Post> data) {
		this.data = data;
	}

	private List<Post> data;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Posts posts = (Posts) o;
		return Objects.equals(data, posts.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}
}
