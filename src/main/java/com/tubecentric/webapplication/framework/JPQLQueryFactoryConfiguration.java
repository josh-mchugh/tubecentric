package com.tubecentric.webapplication.framework;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class JPQLQueryFactoryConfiguration {

    @Autowired
    private EntityManager entityManager;

    @Bean
    public JPQLQueryFactory jpqlQueryFactory() {

        return new JPAQueryFactory(this.entityManager);
    }
}
