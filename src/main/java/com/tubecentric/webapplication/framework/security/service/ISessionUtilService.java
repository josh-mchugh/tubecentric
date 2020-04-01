package com.tubecentric.webapplication.framework.security.service;

import com.tubecentric.webapplication.user.entity.UserEntity;

public interface ISessionUtilService {

    String getCurrentUserSub();

    UserEntity getCurrentUser();
}
