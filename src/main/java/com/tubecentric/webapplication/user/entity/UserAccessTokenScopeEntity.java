package com.tubecentric.webapplication.user.entity;

import com.tubecentric.webapplication.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users_access_token_scope")
public class UserAccessTokenScopeEntity extends AbstractEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_access_token_id")
    private UserAccessTokenEntity accessToken;

    @Column(name = "scope")
    private String scope;

    public UserAccessTokenScopeEntity(UserAccessTokenEntity accessTokenEntity, String scope) {

        this.accessToken = accessTokenEntity;
        this.scope = scope;
    }
}
