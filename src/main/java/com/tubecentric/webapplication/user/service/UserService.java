package com.tubecentric.webapplication.user.service;

import com.querydsl.jpa.JPQLQueryFactory;
import com.tubecentric.webapplication.framework.module.IModuleService;
import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.QUserEntity;
import com.tubecentric.webapplication.user.entity.SubscriptionType;
import com.tubecentric.webapplication.user.entity.UserEntity;
import com.tubecentric.webapplication.user.entity.UserPermissionEntity;
import com.tubecentric.webapplication.user.mapper.UserMapper;
import com.tubecentric.webapplication.user.model.User;
import com.tubecentric.webapplication.user.service.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService, IUserEntityService{

    private final IModuleService moduleService;
    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public UserEntity getBySub(String sub) {

        QUserEntity qUserEntity = QUserEntity.userEntity;

        return queryFactory.selectFrom(qUserEntity)
                .where(qUserEntity.sub.eq(sub))
                .fetchOne();
    }

    @Override
    public User findBySub(String sub) {

        return UserMapper.map(getBySub(sub));
    }

    @Override
    public boolean existsBySub(String sub) {

        QUserEntity qUserEntity = QUserEntity.userEntity;

        long count = queryFactory.select(qUserEntity.id)
                .from(qUserEntity)
                .where(qUserEntity.sub.eq(sub))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public User updateOrCreateUser(OidcUser oidcUser) {

        if(existsBySub(oidcUser.getSubject())) {

            return UserMapper.map(update(oidcUser));
        }

        return UserMapper.map(create(oidcUser));
    }

    @Override
    public AuthenticationResponse handleAuthenticationRequest(String sub) {

        UserEntity userEntity = getBySub(sub);

        Map<String, Object> attributes = new HashedMap<>();
        attributes.put("sub", userEntity.getSub());
        attributes.put("name", userEntity.getName());
        attributes.put("email", userEntity.getEmail());
        attributes.put("email_verified", userEntity.getEmailedVerified());
        attributes.put("picture", userEntity.getImageURL());
        attributes.put("locale", userEntity.getLocale());

        List<SimpleGrantedAuthority> authorities = userEntity.getPermissions().stream()
                .map(entity -> new SimpleGrantedAuthority(entity.getPermission()))
                .collect(Collectors.toList());

        return AuthenticationResponse.builder()
                .attributes(attributes)
                .authorities(authorities)
                .build();
    }

    private UserEntity update(OidcUser oidcUser) {

        UserEntity userEntity = getBySub(oidcUser.getSubject());

        userEntity.setName(oidcUser.getFullName());
        userEntity.setEmail(oidcUser.getEmail());
        userEntity.setEmailedVerified(oidcUser.getEmailVerified());
        userEntity.setImageURL(oidcUser.getPicture());
        userEntity.setLocale(oidcUser.getLocale());

        entityManager.persist(userEntity);

        return userEntity;
    }

    private UserEntity create(OidcUser oidcUser) {

        UserEntity userEntity = new UserEntity();
        userEntity.setSub(oidcUser.getSubject());
        userEntity.setName(oidcUser.getFullName());
        userEntity.setEmail(oidcUser.getEmail());
        userEntity.setEmailedVerified(oidcUser.getEmailVerified());
        userEntity.setImageURL(oidcUser.getPicture());
        userEntity.setLocale(oidcUser.getLocale());
        userEntity.setAccountType(AccountType.CHANNEL_OWNER);
        userEntity.setSubscriptionType(SubscriptionType.FREE);

        List<UserPermissionEntity> modulePermissionEntities = moduleService.getPermissions(userEntity.getSubscriptionType(), userEntity.getAccountType()).stream()
                .map(permission -> new UserPermissionEntity(userEntity, permission))
                .collect(Collectors.toList());

        userEntity.setPermissions(modulePermissionEntities);

        List<UserPermissionEntity> scopePermissionEntities = oidcUser.getAuthorities().stream()
                .map(scope -> new UserPermissionEntity(userEntity, scope.getAuthority()))
                .collect(Collectors.toList());

        userEntity.getPermissions().addAll(scopePermissionEntities);

        entityManager.persist(userEntity);

        return userEntity;
    }
}
