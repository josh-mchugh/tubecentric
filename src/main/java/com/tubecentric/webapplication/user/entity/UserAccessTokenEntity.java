package com.tubecentric.webapplication.user.entity;

import com.tubecentric.webapplication.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users_access_token")
public class UserAccessTokenEntity extends AbstractEntity {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "users_id", nullable = false)
    private UserEntity user;

    @Column(name = "refresh_token_value", nullable = false)
    private String refreshTokenValue;

    @Column(name = "refresh_token_issued_at", nullable = false)
    private Instant refreshTokenIssuedAt;

    @Column(name = "access_token_type", nullable = false)
    private String accessTokenType;

    @Column(name = "access_token_value", nullable = false)
    private String accessTokenValue;

    @Column(name = "access_token_issued_at", nullable = false)
    private Instant accessTokenIssuedAt;

    @Column(name = "access_token_expires_at", nullable = false)
    private Instant accessTokenExpiresAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "accessToken")
    private Set<UserAccessTokenScopeEntity> scopes;
}
