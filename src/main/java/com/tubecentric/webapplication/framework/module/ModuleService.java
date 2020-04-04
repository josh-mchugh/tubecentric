package com.tubecentric.webapplication.framework.module;

import com.google.common.collect.ImmutableSet;
import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.SubscriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleService implements IModuleService {

    @Autowired(required = false)
    private List<IModule> modules = new ArrayList<>();

    @Override
    public ImmutableSet<String> getPermissions(SubscriptionType subscriptionType, AccountType accountType) {

        return modules.stream()
                .filter(modules -> modules.subscriptionTypes().contains(subscriptionType))
                .flatMap(module -> module.permissions().stream())
                .filter(permission -> permission.accountTypes().contains(accountType))
                .map(IModule.Permission::value)
                .collect(ImmutableSet.toImmutableSet());
    }
}
