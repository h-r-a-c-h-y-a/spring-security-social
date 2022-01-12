package am.epam.api.providers.github;

import am.epam.api.common.ApiBinding;
import am.epam.api.providers.facebook.Profile;
import am.epam.api.common.Wrapper;

import java.util.Optional;


public class GitHub extends ApiBinding {

    public static final String POST_SCOPE = "posts";
    public static final String LIKE_SCOPE = "likes";
    private static final String GRAPH_API_BASE_URL = "https://api.github.com";

    public GitHub(String accessToken) {
        super(accessToken);
    }

    public Profile getProfile() {
        return restTemplate.getForObject(GRAPH_API_BASE_URL + "/user", Profile.class);
    }

    public Optional<Wrapper> getInfo(String scope) {
        Wrapper feed = restTemplate.getForObject(GRAPH_API_BASE_URL + "/me?fields=" + scope, Wrapper.class);
        return Optional.ofNullable(feed);
    }
}