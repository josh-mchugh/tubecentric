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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name="questionnaire")
public class QuestionnaireEntity extends AbstractEntity {

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="registration_id")
    private RegistrationEntity registrationEntity;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="questionnaireEntity")
    private List<QuestionnaireFeatureEntity> featureEntities;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="questionnaireEntity")
    private List<QuestionnaireReportIntervalEntity> reportIntervalEntities;

    @Basic
    @Column(name="frustrations", length=5000)
    private String frustrations;

    @Basic
    @Column(name="allow_future_questionnaires")
    private boolean allowFutureQuestionnaires;
}
