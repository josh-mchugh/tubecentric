package com.tubecentric.webapplication.register.entity;

import com.tubecentric.webapplication.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name="registration")
public class RegistrationEntity extends AbstractEntity {

    @Basic
    @Column(name="email", length=500, nullable=false, unique=true)
    private String email;

    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="registrationEntity")
    private QuestionnaireEntity questionnaireEntity;
}
