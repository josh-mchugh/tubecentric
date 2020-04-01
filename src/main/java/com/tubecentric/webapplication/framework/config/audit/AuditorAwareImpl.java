package com.tubecentric.webapplication.framework.config.audit;

import com.tubecentric.webapplication.framework.security.service.ISessionUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Primary
@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    private final ISessionUtilService sessionUtilService;

    @Override
    public Optional<String> getCurrentAuditor() {

        String userId = Optional.ofNullable(sessionUtilService.getCurrentUserSub())
                .orElse("SYSTEM");

        return Optional.of(userId);
    }
}

