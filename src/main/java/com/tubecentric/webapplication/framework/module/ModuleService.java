package com.tubecentric.webapplication.framework.module;

import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.SubscriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ModuleService implements IModuleService {

    @Autowired(required = false)
    private List<IModule> modules = new ArrayList<>();

    @Override
    public Set<String> getAllPermissions() {

        return modules.stream()
                .map(IModule::permissions)
                .flatMap(Set::stream)
                .map(IModule.Permission::value)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getPermissions(SubscriptionType subscriptionType, AccountType accountType) {

        return modules.stream()
                .filter(modules -> modules.subscriptionTypes().contains(subscriptionType))
                .flatMap(module -> module.permissions().stream())
                .filter(permission -> permission.accountTypes().contains(accountType))
                .map(IModule.Permission::value)
                .collect(Collectors.toSet());
    }
}
