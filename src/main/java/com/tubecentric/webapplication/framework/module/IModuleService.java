package com.tubecentric.webapplication.framework.module;

import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.SubscriptionType;

import java.util.Set;

public interface IModuleService {

    Set<String> getAllPermissions();

    Set<String> getPermissions(SubscriptionType subscriptionType, AccountType accountType);
}
