package com.tubecentric.webapplication.framework.security.service;

import com.tubecentric.webapplication.user.model.User;
import com.tubecentric.webapplication.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionUtilService implements ISessionUtilService {

    private final IUserService userService;

    @Override
    public String getCurrentUserSub() {

        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Principal::getName)
                .orElse(null);
    }

    @Override
    public User getCurrentUser() {

        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(principal -> principal instanceof OAuth2User)
                .map(authentication -> userService.findBySub(authentication.getName()))
                .orElse(null);
    }
}
