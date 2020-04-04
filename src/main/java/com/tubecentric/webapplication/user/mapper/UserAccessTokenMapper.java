package com.tubecentric.webapplication.user.mapper;

import com.google.common.collect.ImmutableSet;
import com.tubecentric.webapplication.user.entity.UserAccessTokenEntity;
import com.tubecentric.webapplication.user.entity.UserAccessTokenScopeEntity;
import com.tubecentric.webapplication.user.model.UserAccessToken;

import java.util.Collection;

public class UserAccessTokenMapper {

    public static UserAccessToken map(UserAccessTokenEntity entity) {

        return UserAccessToken.builder()
                .id(entity.getId())
                .refreshTokenValue(entity.getRefreshTokenValue())
                .refreshTokenIssuedAt(entity.getRefreshTokenIssuedAt())
                .accessTokenValue(entity.getAccessTokenValue())
                .accessTokenType(entity.getAccessTokenType())
                .accessTokenIssuedAt(entity.getAccessTokenIssuedAt())
                .accessTokenExpiresAt(entity.getAccessTokenExpiresAt())
                .scopes(UserAccessTokenMapper.map(entity.getScopes()))
                .build();
    }

    public static ImmutableSet<String> map(Collection<UserAccessTokenScopeEntity> entities) {

        return entities.stream()
                .map(UserAccessTokenScopeEntity::getScope)
                .collect(ImmutableSet.toImmutableSet());
    }
}
