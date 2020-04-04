package com.tubecentric.webapplication.framework.module;

import com.google.common.collect.ImmutableSet;
import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.SubscriptionType;

public interface IModule {

    interface Permission {

        String value();

        ImmutableSet<AccountType> accountTypes();
    }

    String name();

    ImmutableSet<Permission> permissions();

    ImmutableSet<SubscriptionType> subscriptionTypes();
}
