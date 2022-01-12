package am.epam.api.controller;

import am.epam.api.common.ApiBinding;
import am.epam.api.common.RegistrationId;
import am.epam.api.providers.facebook.Facebook;
import am.epam.api.providers.google.Google;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import am.epam.api.providers.github.GitHub;

import java.util.Locale;

@Controller
public class HomeController {

	private final Facebook facebook;
	private final Google google;
	private final GitHub gitHub;

	@Autowired
	public HomeController(Facebook facebook, Google google, GitHub gitHub) {
		this.facebook = facebook;
		this.google = google;
		this.gitHub = gitHub;
	}
	
	@GetMapping("/")
	public String home(Model model, OAuth2AuthenticationToken principal) {
		final String authorizedClientRegistrationId = principal.getAuthorizedClientRegistrationId();
		final ApiBinding activeAPI = getProfileFromAPI(RegistrationId.valueOf(authorizedClientRegistrationId.toUpperCase(Locale.ROOT)));
		model.addAttribute("profile", activeAPI.getProfile());
		return "home";
	}

	@GetMapping("/posts")
	public String posts(Model model, OAuth2AuthenticationToken principal) {
		final String authorizedClientRegistrationId = principal.getAuthorizedClientRegistrationId();
		final ApiBinding activeAPI = getProfileFromAPI(RegistrationId.valueOf(authorizedClientRegistrationId.toUpperCase(Locale.ROOT)));
		model.addAttribute("regId", authorizedClientRegistrationId);
		model.addAttribute("profile", activeAPI.getProfile());
		if (activeAPI instanceof Facebook) {
			activeAPI.getInfo(Facebook.POST_SCOPE)
					.ifPresent(wrapper -> model.addAttribute("posts", wrapper.getPosts().getData()));
		}
		return "posts";
	}

	@GetMapping("/likes")
	public String likes(Model model, OAuth2AuthenticationToken principal) {
		final String authorizedClientRegistrationId = principal.getAuthorizedClientRegistrationId();
		final ApiBinding activeAPI = getProfileFromAPI(RegistrationId.valueOf(authorizedClientRegistrationId.toUpperCase(Locale.ROOT)));
		model.addAttribute("profile", activeAPI.getProfile());
		model.addAttribute("regId", authorizedClientRegistrationId);
		if (activeAPI instanceof Facebook) {
			activeAPI.getInfo(Facebook.LIKE_SCOPE)
					.ifPresent(wrapper -> model.addAttribute("likes", wrapper.getLikes().getData()));
		}
		return "likes";
	}

	private ApiBinding getProfileFromAPI(RegistrationId authorizedClientRegistrationId) {
		switch (authorizedClientRegistrationId) {
			case GITHUB:
				return gitHub;
			case GOOGLE:
				return google;
			default:
				return facebook;
		}
	}
	
}
