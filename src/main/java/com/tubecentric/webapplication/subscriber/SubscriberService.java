package com.tubecentric.webapplication.subscriber;

import com.querydsl.jpa.JPQLQueryFactory;
import com.tubecentric.webapplication.subscriber.entity.QSubscriberEntity;
import com.tubecentric.webapplication.subscriber.entity.SubscriberEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Transactional
@AllArgsConstructor
public class SubscriberService implements ISubscriberService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public void persistSubscriber(String email) {

        if (!existSubscriber(email)) {

            SubscriberEntity subscriberEntity = new SubscriberEntity();
            subscriberEntity.setEmail(email);
            entityManager.persist(subscriberEntity);
        }
    }

    @Override
    public boolean existSubscriber(String email) {

        QSubscriberEntity qSubscriber = QSubscriberEntity.subscriberEntity;

        long count = queryFactory.select(qSubscriber.id.count())
                .from(qSubscriber)
                .where(qSubscriber.email.eq(email))
                .fetchCount();

        return count >= 1;
    }
}
