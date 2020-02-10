package com.tubecentric.webapplication.extension.entity.service;

import com.querydsl.jpa.JPQLQueryFactory;
import com.tubecentric.webapplication.extension.entity.KeywordSearchTerm;
import com.tubecentric.webapplication.extension.entity.KeywordSearchTermEntry;
import com.tubecentric.webapplication.extension.entity.QKeywordSearchTerm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Transactional
@AllArgsConstructor
public class KeywordSearchService implements IKeywordSearchService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public void addKeywordSearchTerm(String query) {

        KeywordSearchTerm keywordSearchTerm;

        if (existsKeywordSearchTerm(query)) {

            keywordSearchTerm = getKeywordSearchTermByQuery(query);

        } else {

            keywordSearchTerm = new KeywordSearchTerm();
            keywordSearchTerm.setQuery(query);
            entityManager.persist(keywordSearchTerm);
        }

        KeywordSearchTermEntry entry = new KeywordSearchTermEntry();
        entry.setKeywordSearchTerm(keywordSearchTerm);
        entityManager.persist(entry);
    }

    private boolean existsKeywordSearchTerm(String query) {

        QKeywordSearchTerm qKeywordSearchTerm = QKeywordSearchTerm.keywordSearchTerm;

        long count = queryFactory.select(qKeywordSearchTerm.query.count())
                .from(qKeywordSearchTerm)
                .where(qKeywordSearchTerm.query.equalsIgnoreCase(query))
                .fetchCount();

        return count >= 1;
    }

    private KeywordSearchTerm getKeywordSearchTermByQuery(String query) {

        QKeywordSearchTerm qKeywordSearchTerm = QKeywordSearchTerm.keywordSearchTerm;

        return queryFactory.selectFrom(qKeywordSearchTerm)
                .where(qKeywordSearchTerm.query.equalsIgnoreCase(query))
                .fetchOne();
    }
}
