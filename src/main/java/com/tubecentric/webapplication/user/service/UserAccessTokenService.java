package com.tubecentric.webapplication.user.service;

import com.querydsl.jpa.JPQLQueryFactory;
import com.tubecentric.webapplication.user.entity.QUserAccessTokenEntity;
import com.tubecentric.webapplication.user.entity.UserAccessTokenEntity;
import com.tubecentric.webapplication.user.entity.UserAccessTokenScopeEntity;
import com.tubecentric.webapplication.user.mapper.UserAccessTokenMapper;
import com.tubecentric.webapplication.user.model.UserAccessToken;
import com.tubecentric.webapplication.user.service.model.UserAccessTokenUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UserAccessTokenService implements IUserAccessTokenService {

    private final IUserEntityService userEntityService;
    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public UserAccessToken findBySub(String sub) {

        return UserAccessTokenMapper.map(getBySub(sub));
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
    public UserAccessToken updateOrCreateAccessToken(OAuth2AuthorizedClient authorizedClient) {

        if(existsBySub(authorizedClient.getPrincipalName())) {

            return UserAccessTokenMapper.map(update(authorizedClient));
        }

        return UserAccessTokenMapper.map(create(authorizedClient));
    }

    @Override
    public void deleteBySub(String sub) {

        entityManager.remove(getBySub(sub));
    }

    @Override
    public UserAccessToken handleUpdateAccessToken(UserAccessTokenUpdateRequest request) {

        UserAccessTokenEntity userAccessTokenEntity = getById(request.getId());

        userAccessTokenEntity.setAccessTokenValue(request.getAccessToken().getTokenValue());
        userAccessTokenEntity.setAccessTokenIssuedAt(request.getAccessToken().getIssuedAt());
        userAccessTokenEntity.setAccessTokenExpiresAt(request.getAccessToken().getExpiresAt());

        entityManager.persist(userAccessTokenEntity);

        return UserAccessTokenMapper.map(userAccessTokenEntity);
    }

    private UserAccessTokenEntity getById(String id) {

        QUserAccessTokenEntity qUserAccessToken = QUserAccessTokenEntity.userAccessTokenEntity;

        return queryFactory.selectFrom(qUserAccessToken)
                .where(qUserAccessToken.id.eq(id))
                .fetchOne();
    }

    private UserAccessTokenEntity getBySub(String sub) {

        QUserAccessTokenEntity qUserAccessToken = QUserAccessTokenEntity.userAccessTokenEntity;

        return queryFactory.selectFrom(qUserAccessToken)
                .where(qUserAccessToken.user.sub.eq(sub))
                .fetchOne();
    }

    private UserAccessTokenEntity create(OAuth2AuthorizedClient authorizedClient) {

        UserAccessTokenEntity accessTokenEntity = new UserAccessTokenEntity();
        accessTokenEntity.setUser(userEntityService.getBySub(authorizedClient.getPrincipalName()));
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

    private UserAccessTokenEntity update(OAuth2AuthorizedClient authorizedClient) {

        UserAccessTokenEntity accessTokenEntity = getBySub(authorizedClient.getPrincipalName());
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
