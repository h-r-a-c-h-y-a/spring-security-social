package am.epam.api.config;

import am.epam.api.common.RegistrationId;
import am.epam.api.providers.facebook.Facebook;
import am.epam.api.providers.google.Google;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

import am.epam.api.providers.github.GitHub;

@Configuration
public class SocialConfig {

	@Bean
	@RequestScope
	public Facebook facebook(OAuth2AuthorizedClientService clientService) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String accessToken = fetchAccessToken(authentication, clientService, RegistrationId.FACEBOOK);
		return new Facebook(accessToken);
	}

	@Bean
	@RequestScope
	public Google google(OAuth2AuthorizedClientService clientService) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String accessToken = fetchAccessToken(authentication, clientService, RegistrationId.GOOGLE);
		return new Google(accessToken);
	}

	@Bean
	@RequestScope
	public GitHub gitHub(OAuth2AuthorizedClientService clientService) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String accessToken = fetchAccessToken(authentication, clientService, RegistrationId.GITHUB);
		return new GitHub(accessToken);
	}

	private String fetchAccessToken(Authentication authentication, OAuth2AuthorizedClientService clientService, RegistrationId registrationId) {
		String accessToken = null;
		if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
			OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
			String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
			if (clientRegistrationId.equalsIgnoreCase(registrationId.getName())) {
				OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
				accessToken = client.getAccessToken().getTokenValue();
			}
		}
		return accessToken;
	}

}

