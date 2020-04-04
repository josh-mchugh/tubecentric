package com.tubecentric.webapplication.framework.security.service;

import com.tubecentric.webapplication.user.model.User;

public interface ISessionUtilService {

    String getCurrentUserSub();

    User getCurrentUser();
}
