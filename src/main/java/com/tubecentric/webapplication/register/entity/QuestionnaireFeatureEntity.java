package com.tubecentric.webapplication.register.entity;

import com.tubecentric.webapplication.framework.AbstractEntity;
import com.tubecentric.webapplication.register.FeatureSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name="questionnaire_feature")
public class QuestionnaireFeatureEntity extends AbstractEntity {

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="questionnaire_id")
    private QuestionnaireEntity questionnaireEntity;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name="feature_set")
    private FeatureSet featureSet;
}
