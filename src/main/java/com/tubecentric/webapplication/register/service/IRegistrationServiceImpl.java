package com.tubecentric.webapplication.register.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQueryFactory;
import com.tubecentric.webapplication.register.FeatureSet;
import com.tubecentric.webapplication.register.entity.QRegistrationEntity;
import com.tubecentric.webapplication.register.entity.QuestionnaireEntity;
import com.tubecentric.webapplication.register.entity.QuestionnaireFeatureEntity;
import com.tubecentric.webapplication.register.entity.QuestionnaireReportIntervalEntity;
import com.tubecentric.webapplication.register.entity.RegistrationEntity;
import com.tubecentric.webapplication.register.service.model.QuestionnairePersistRequest;
import com.tubecentric.webapplication.register.service.model.QuestionnairePersistResponse;
import com.tubecentric.webapplication.register.service.model.RegistrationPersistRequest;
import com.tubecentric.webapplication.register.service.model.RegistrationPersistResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class IRegistrationServiceImpl implements IRegistrationService{

    private final EntityManager entityManager;
    private final JPQLQueryFactory queryFactory;

    @Override
    public RegistrationPersistResponse persistRegistration(RegistrationPersistRequest request) {

        QRegistrationEntity qRegistrationEntity = QRegistrationEntity.registrationEntity;

        long count = queryFactory.select(qRegistrationEntity.id)
                .from(qRegistrationEntity)
                .where(qRegistrationEntity.email.eq(request.getEmail()))
                .fetchCount();

        if(count >= 1) {

            RegistrationEntity entity = queryFactory.selectFrom(qRegistrationEntity)
                    .where(qRegistrationEntity.email.eq(request.getEmail()))
                    .fetchOne();

            return RegistrationPersistResponse.builder()
                    .id(entity.getId())
                    .build();
        }

        RegistrationEntity entity = new RegistrationEntity();
        entity.setEmail(request.getEmail());

        entityManager.persist(entity);

        return RegistrationPersistResponse.builder()
                .id(entity.getId())
                .build();
    }

    @Override
    public QuestionnairePersistResponse persistQuestionnaire(QuestionnairePersistRequest request) {

        QRegistrationEntity qRegistrationEntity = QRegistrationEntity.registrationEntity;

        RegistrationEntity registrationEntity = queryFactory.selectFrom(qRegistrationEntity)
                .where(qRegistrationEntity.id.eq(request.getId()))
                .fetchOne();

        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setRegistrationEntity(registrationEntity);
        questionnaireEntity.setFrustrations(request.getFrustrations());
        questionnaireEntity.setAllowFutureQuestionnaires(request.isAllowFutureQuestionnaires());

        List<QuestionnaireFeatureEntity> featureEntities = request.getFeatureSet().stream()
                .map(featureSet -> {
                    QuestionnaireFeatureEntity featureEntity = new QuestionnaireFeatureEntity();
                    featureEntity.setQuestionnaireEntity(questionnaireEntity);
                    featureEntity.setFeatureSet(featureSet);
                    return featureEntity;
                })
                .collect(Collectors.toList());
        questionnaireEntity.setFeatureEntities(featureEntities);

        List<QuestionnaireReportIntervalEntity> reportIntervalEntities = request.getReportingIntervals().stream()
                .map(reportingInterval -> {
                    QuestionnaireReportIntervalEntity reportIntervalEntity = new QuestionnaireReportIntervalEntity();
                    reportIntervalEntity.setQuestionnaireEntity(questionnaireEntity);
                    reportIntervalEntity.setInterval(reportingInterval);
                    return reportIntervalEntity;
                })
                .collect(Collectors.toList());
        questionnaireEntity.setReportIntervalEntities(reportIntervalEntities);

        registrationEntity.setQuestionnaireEntity(questionnaireEntity);

        entityManager.persist(registrationEntity);

        return QuestionnairePersistResponse.builder()
                .id(registrationEntity.getId())
                .build();
    }
}
