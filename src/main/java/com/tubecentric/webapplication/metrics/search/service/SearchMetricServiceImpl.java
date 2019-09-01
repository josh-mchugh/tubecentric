package com.tubecentric.webapplication.metrics.search.service;

import com.querydsl.jpa.JPQLQueryFactory;
import com.tubecentric.webapplication.metrics.search.entity.QSearchMetricEntity;
import com.tubecentric.webapplication.metrics.search.entity.SearchMetricEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Transactional
@RequiredArgsConstructor
public class SearchMetricServiceImpl implements ISearchMetricService {

    private final EntityManager entityManager;
    private final JPQLQueryFactory queryFactory;

    @Override
    public void recordSearchQuery(String query) {

        if(StringUtils.isNotBlank(query)) {

            QSearchMetricEntity qSearch = QSearchMetricEntity.searchMetricEntity;

            SearchMetricEntity entity = queryFactory.selectFrom(qSearch)
                    .where(qSearch.query.equalsIgnoreCase(query))
                    .fetchOne();

            if(entity != null) {

                Long incrementedCount = entity.getCount() + 1;
                entity.setCount(incrementedCount);

            }else {

                entity = new SearchMetricEntity();
                entity.setQuery(query);
                entity.setCount(1L);
            }

            entityManager.persist(entity);
        }
    }
}
