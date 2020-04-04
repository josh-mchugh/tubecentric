package com.tubecentric.webapplication.framework.module;

import com.google.common.collect.ImmutableSet;
import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.SubscriptionType;

public interface IModuleService {

    ImmutableSet<String> getPermissions(SubscriptionType subscriptionType, AccountType accountType);
}
