package com.tubecentric.webapplication.metric;

import com.querydsl.jpa.JPQLQueryFactory;
import com.tubecentric.webapplication.metric.entity.MetricRelatedTagsQueryEntity;
import com.tubecentric.webapplication.metric.entity.MetricRelatedTagsQueryEntryEntity;
import com.tubecentric.webapplication.metric.entity.QMetricRelatedTagsQueryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
@Transactional
@AllArgsConstructor
public class MetricRelatedTagsService implements IMetricRelatedTagsService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public void persistRelatedTagsQuery(String query) {

        MetricRelatedTagsQueryEntity relatedTagsQueryEntity = getOrCreateKeywordSearchTerm(query);

        MetricRelatedTagsQueryEntryEntity entry = new MetricRelatedTagsQueryEntryEntity();
        entry.setRelatedTagsQuery(relatedTagsQueryEntity);

        entityManager.persist(entry);
    }

    private MetricRelatedTagsQueryEntity getOrCreateKeywordSearchTerm(String query) {

        QMetricRelatedTagsQueryEntity relatedTagsQuery = QMetricRelatedTagsQueryEntity.metricRelatedTagsQueryEntity;

        MetricRelatedTagsQueryEntity keywordSearchTerm = queryFactory.selectFrom(relatedTagsQuery)
                .where(relatedTagsQuery.query.equalsIgnoreCase(query))
                .fetchOne();

        return Optional.ofNullable(keywordSearchTerm)
                .orElse(new MetricRelatedTagsQueryEntity(query));
    }
}
