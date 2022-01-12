package am.epam.api.providers.facebook;

import java.util.Optional;

import am.epam.api.common.ApiBinding;
import am.epam.api.common.Wrapper;

public class Facebook extends ApiBinding {

	public static final String POST_SCOPE = "posts";
	public static final String LIKE_SCOPE = "likes";
	private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/";
	
	public Facebook(String accessToken) {
		super(accessToken);
	}
	
	public Profile getProfile() {
		return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me", Profile.class);
	}
	
	public Optional<Wrapper> getInfo(String scope) {
		Wrapper feed = restTemplate.getForObject(GRAPH_API_BASE_URL + "/me?fields=" + scope, Wrapper.class);
		return Optional.ofNullable(feed);
	}

}
