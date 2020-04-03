package com.tubecentric.webapplication.framework.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
@RequiredArgsConstructor
public class AuditingConfig {

    private final AuditorAwareImpl auditorAware;

    @Bean
    public AuditorAware<String> auditorAware() {

        return auditorAware;
    }
}
