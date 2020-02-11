package com.tubecentric.webapplication.landing.entity.service;

import com.querydsl.jpa.JPQLQueryFactory;
import com.tubecentric.webapplication.landing.entity.QSubscriber;
import com.tubecentric.webapplication.landing.entity.Subscriber;
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
    public void handleSubscriberSave(String email) {

        if (!existSubscriber(email)) {

            Subscriber subscriber = new Subscriber();
            subscriber.setEmail(email);
            entityManager.persist(subscriber);
        }
    }

    public boolean existSubscriber(String email) {

        QSubscriber qSubscriber = QSubscriber.subscriber;

        long count = queryFactory.select(qSubscriber.id.count())
                .from(qSubscriber)
                .where(qSubscriber.email.eq(email))
                .fetchCount();

        return count >= 1;
    }
}
