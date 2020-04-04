package com.tubecentric.webapplication.user.service;

import com.tubecentric.webapplication.user.entity.UserEntity;

public interface IUserEntityService {

    UserEntity getBySub(String sub);
}
