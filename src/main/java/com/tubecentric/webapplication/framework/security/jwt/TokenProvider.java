package com.tubecentric.webapplication.framework.security.jwt;

import com.tubecentric.webapplication.framework.config.AppConfig;
import com.tubecentric.webapplication.user.service.IUserService;
import com.tubecentric.webapplication.user.service.model.AuthenticationResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final AppConfig appConfig;
    private final IUserService userService;

    private Key key;
    private JwtParser jwtParser;

    @PostConstruct
    public void init() {

        byte[] keyBytes = Base64.decode(appConfig.getJwt().getKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);

        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    public String createToken(Authentication authentication) {

        Set<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        Date expiresAt = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(appConfig.getJwt().getExpiresInDays()));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("permissions", authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiresAt)
                .compact();
    }

    public Authentication getAuthentication(String token) {

        Claims claims = jwtParser.parseClaimsJws(token)
                .getBody();

        if(userService.existsBySub(claims.getSubject())) {

            AuthenticationResponse response = userService.handleAuthenticationRequest(claims.getSubject());

            OAuth2User principle = new DefaultOAuth2User(response.getAuthorities(), response.getAttributes(), "sub");

            return new OAuth2AuthenticationToken(principle, response.getAuthorities(), "google");
        }

        return null;
    }

    public boolean validateToken(String token) {

        try {

            jwtParser.parseClaimsJws(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {

            log.info("Unable to parse invalid JWT token: {}", e.getMessage());
        }

        return false;
    }
}
