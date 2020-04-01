package com.tubecentric.webapplication.framework.module;

import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.SubscriptionType;

import java.util.Set;

public interface IModule {

    interface Permission {

        String value();

        Set<AccountType> accountTypes();
    }

    String name();

    Set<IModule.Permission> permissions();

    Set<SubscriptionType> subscriptionTypes();
}
