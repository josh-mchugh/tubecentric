package com.tubecentric.webapplication.framework.security.service;

import com.tubecentric.webapplication.user.IUserService;
import com.tubecentric.webapplication.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionUtilService implements ISessionUtilService {

    private final IUserService userService;

    @Override
    public String getCurrentUserSub() {

        if(SecurityContextHolder.getContext().getAuthentication() != null) {

            return SecurityContextHolder.getContext().getAuthentication().getName();
        }

        return null;
    }

    @Override
    public UserEntity getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof OAuth2User) {

            return userService.findBySub(((OAuth2User) principal).getName());
        }

        return null;
    }
}
