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
@Table(name = "users_permission")
public class UserPermissionEntity extends AbstractEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "users_id", nullable = false)
    private UserEntity user;

    @Column(name = "permission", nullable = false)
    private String permission;

    public UserPermissionEntity(UserEntity userEntity, String permission) {

        this.user = userEntity;
        this.permission = permission;
    }
}
