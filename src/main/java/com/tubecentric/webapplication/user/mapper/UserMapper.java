package com.tubecentric.webapplication.user.mapper;

import com.google.common.collect.ImmutableSet;
import com.tubecentric.webapplication.user.entity.UserEntity;
import com.tubecentric.webapplication.user.entity.UserPermissionEntity;
import com.tubecentric.webapplication.user.model.User;

import java.util.Collection;

public class UserMapper {

    public static User map(UserEntity entity) {

        User.Builder builder = User.builder()
                .id(entity.getId())
                .sub(entity.getSub())
                .name(entity.getName())
                .email(entity.getEmail())
                .emailedVerified(entity.getEmailedVerified())
                .imageURL(entity.getImageURL())
                .locale(entity.getLocale())
                .accountType(entity.getAccountType())
                .subscriptionType(entity.getSubscriptionType())
                .permissions(UserMapper.map(entity.getPermissions()));

        if(entity.getAccessToken() != null) {

            builder.userAccessToken(UserAccessTokenMapper.map(entity.getAccessToken()));
        }

        return builder.build();
    }

    public static ImmutableSet<String> map(Collection<UserPermissionEntity> entities) {

        return entities.stream()
                .map(UserPermissionEntity::getPermission)
                .collect(ImmutableSet.toImmutableSet());
    }
}
