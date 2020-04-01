package com.tubecentric.webapplication.keyword;

import com.google.common.collect.Sets;
import com.tubecentric.webapplication.framework.module.IModule;
import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.SubscriptionType;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class KeywordSearchModule implements IModule {

    public enum Permission implements IModule.Permission {

        ACCESS("KEYWORD_SEARCH_ACCESS", AccountType.CHANNEL_OWNER);

        private String value;
        private Set<AccountType> accountTypes;

        Permission(String value, AccountType... accountTypes) {

            this.value = value;
            this.accountTypes = Sets.newHashSet(accountTypes);
        }

        @Override
        public String value() {

            return value;
        }

        @Override
        public Set<AccountType> accountTypes() {

            return accountTypes;
        }
    }

    @Override
    public String name() {

        return this.getClass().getName();
    }

    @Override
    public Set<IModule.Permission> permissions() {

        return Sets.newHashSet(Permission.values());
    }

    @Override
    public Set<SubscriptionType> subscriptionTypes() {

        return Sets.newHashSet(SubscriptionType.FREE);
    }
}
