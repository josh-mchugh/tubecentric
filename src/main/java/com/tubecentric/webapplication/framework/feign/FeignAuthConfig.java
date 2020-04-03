package com.tubecentric.webapplication.framework.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class FeignAuthConfig {

    @Bean
    public RequestInterceptor requestInterceptor(OAuth2AuthorizedClientService authorizedClientService) {

        return template -> {

            OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

            template.header("Authorization", String.format("Bearer %s", client.getAccessToken().getTokenValue()));
        };
    }
}
