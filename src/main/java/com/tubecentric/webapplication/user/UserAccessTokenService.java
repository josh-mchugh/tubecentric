package com.tubecentric.webapplication.user;

import com.querydsl.jpa.JPQLQueryFactory;
import com.tubecentric.webapplication.user.entity.QUserAccessTokenEntity;
import com.tubecentric.webapplication.user.entity.UserAccessTokenEntity;
import com.tubecentric.webapplication.user.entity.UserAccessTokenScopeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UserAccessTokenService implements IUserAccessTokenService {

    private final IUserService userService;
    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public UserAccessTokenEntity findBySub(String sub) {

        QUserAccessTokenEntity qUserAccessTokenEntity = QUserAccessTokenEntity.userAccessTokenEntity;

        return queryFactory.selectFrom(qUserAccessTokenEntity)
                .where(qUserAccessTokenEntity.user.sub.eq(sub))
                .fetchOne();
    }

    @Override
    public boolean existsBySub(String sub) {

        QUserAccessTokenEntity qUserAccessTokenEntity = QUserAccessTokenEntity.userAccessTokenEntity;

        long count = queryFactory.select(qUserAccessTokenEntity.id)
                .from(qUserAccessTokenEntity)
                .where(qUserAccessTokenEntity.user.sub.eq(sub))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public UserAccessTokenEntity updateOrCreateAccessToken(Principal principal, OAuth2AuthorizedClient authorizedClient) {

        if(existsBySub(principal.getName())) {

            return update(principal, authorizedClient);
        }

        return create(principal, authorizedClient);
    }

    @Override
    public void deleteBySub(String sub) {

        UserAccessTokenEntity userAccessTokenEntity = findBySub(sub);

        entityManager.remove(userAccessTokenEntity);
    }

    private UserAccessTokenEntity create(Principal principal, OAuth2AuthorizedClient authorizedClient) {

        UserAccessTokenEntity accessTokenEntity = new UserAccessTokenEntity();
        accessTokenEntity.setUser(userService.findBySub(principal.getName()));
        accessTokenEntity.setRefreshTokenValue(authorizedClient.getRefreshToken().getTokenValue());
        accessTokenEntity.setRefreshTokenIssuedAt(authorizedClient.getRefreshToken().getIssuedAt());
        accessTokenEntity.setAccessTokenType(authorizedClient.getAccessToken().getTokenType().getValue());
        accessTokenEntity.setAccessTokenValue(authorizedClient.getAccessToken().getTokenValue());
        accessTokenEntity.setAccessTokenIssuedAt(authorizedClient.getAccessToken().getIssuedAt());
        accessTokenEntity.setAccessTokenExpiresAt(authorizedClient.getAccessToken().getExpiresAt());

        accessTokenEntity.setScopes(buildAccessTokenScopeEntities(accessTokenEntity, authorizedClient.getAccessToken()));

        entityManager.persist(accessTokenEntity);

        return accessTokenEntity;
    }

    private UserAccessTokenEntity update(Principal principal, OAuth2AuthorizedClient authorizedClient) {

        UserAccessTokenEntity accessTokenEntity = findBySub(principal.getName());
        accessTokenEntity.setRefreshTokenValue(authorizedClient.getRefreshToken().getTokenValue());
        accessTokenEntity.setRefreshTokenIssuedAt(authorizedClient.getRefreshToken().getIssuedAt());
        accessTokenEntity.setAccessTokenType(authorizedClient.getAccessToken().getTokenType().getValue());
        accessTokenEntity.setAccessTokenValue(authorizedClient.getAccessToken().getTokenValue());
        accessTokenEntity.setAccessTokenIssuedAt(authorizedClient.getAccessToken().getIssuedAt());
        accessTokenEntity.setAccessTokenExpiresAt(authorizedClient.getAccessToken().getExpiresAt());

        Set<UserAccessTokenScopeEntity> removedScopeEntities = accessTokenEntity.getScopes().stream()
                .filter(entity -> authorizedClient.getAccessToken().getScopes().stream().noneMatch(scope -> scope.equals(entity.getScope())))
                .collect(Collectors.toSet());

        accessTokenEntity.getScopes().removeAll(removedScopeEntities);

        Set<UserAccessTokenScopeEntity> addScopesEntities = authorizedClient.getAccessToken().getScopes().stream()
                .filter(scope -> accessTokenEntity.getScopes().stream().noneMatch(entity-> entity.getScope().equals(scope)))
                .map(scope -> new UserAccessTokenScopeEntity(accessTokenEntity, scope))
                .collect(Collectors.toSet());

        accessTokenEntity.getScopes().addAll(addScopesEntities);

        entityManager.persist(accessTokenEntity);

        return accessTokenEntity;
    }

    private Set<UserAccessTokenScopeEntity> buildAccessTokenScopeEntities(UserAccessTokenEntity accessTokenEntity, OAuth2AccessToken accessToken) {

        return accessToken.getScopes().stream()
                .map(scope -> new UserAccessTokenScopeEntity(accessTokenEntity, scope))
                .collect(Collectors.toSet());
    }
}
