package com.tubecentric.webapplication.framework.config.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditingConfig {

    @Autowired
    private AuditorAwareImpl auditorAware;

    @Bean
    public AuditorAware<String> auditorAware() {

        return auditorAware;
    }
}
