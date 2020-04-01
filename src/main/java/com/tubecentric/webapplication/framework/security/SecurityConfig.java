package com.tubecentric.webapplication.framework.security;

import com.google.common.collect.ImmutableList;
import com.tubecentric.webapplication.framework.security.jwt.JWTFilter;
import com.tubecentric.webapplication.framework.security.oauth2.CustomAuthenticationFailureHandler;
import com.tubecentric.webapplication.framework.security.oauth2.CustomAuthenticationSuccessHandler;
import com.tubecentric.webapplication.framework.security.oauth2.CustomAuthorizationRequestRepository;
import com.tubecentric.webapplication.framework.security.oauth2.CustomAuthorizationRequestResolver;
import com.tubecentric.webapplication.framework.security.oauth2.CustomAuthorizedClientService;
import com.tubecentric.webapplication.framework.security.oauth2.CustomOidcUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthorizationRequestResolver customAuthorizationRequestResolver;
    private final CustomAuthorizedClientService customAuthorizedClientService;
    private final CustomOidcUserService customOidcUserService;
    private final CustomAuthorizationRequestRepository customAuthorizationRequestRepository;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final JWTFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] publicUrls = new String[] {
                "/",
                "/terms-of-service",
                "/privacy-policy",
                "/resources/**",
                "/favicon.ico",
                // TODO: Remove once security is implemented on frontend
                "/api/**"
        };

        http
            .cors()
                .configurationSource(corsConfigurationSource())
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                    .antMatchers(publicUrls).permitAll()
                    .anyRequest().authenticated()
            .and()
                .oauth2Login()
                    .authorizationEndpoint()
                    .authorizationRequestResolver(customAuthorizationRequestResolver)
                    .authorizationRequestRepository(customAuthorizationRequestRepository)
                .and()
                    .userInfoEndpoint()
                        .oidcUserService(customOidcUserService)
                .and()
                .authorizedClientService(customAuthorizedClientService)
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    public CorsConfigurationSource corsConfigurationSource() {

        List<String> exposedHeaders = ImmutableList.of(
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Methods",
                "Access-Control-Allow-Headers",
                "Access-Control-Max-Age",
                "Access-Control-Request-Headers",
                "Access-Control-Request-Method"
        );

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("*"));
        configuration.setAllowedHeaders(ImmutableList.of("*"));
        configuration.setExposedHeaders(exposedHeaders);
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);

        return source;
    }
}
