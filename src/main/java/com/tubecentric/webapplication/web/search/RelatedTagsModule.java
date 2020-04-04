package com.tubecentric.webapplication.web.search;

import com.google.common.collect.ImmutableSet;
import com.tubecentric.webapplication.framework.module.IModule;
import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.SubscriptionType;
import org.springframework.stereotype.Component;

@Component
public class RelatedTagsModule implements IModule {

    public enum Permission implements IModule.Permission {

        ACCESS("RELATED_TAGS_ACCESS", AccountType.CHANNEL_OWNER);

        private String value;
        private ImmutableSet<AccountType> accountTypes;

        Permission(String value, AccountType... accountTypes) {

            this.value = value;
            this.accountTypes = ImmutableSet.copyOf(accountTypes);
        }

        @Override
        public String value() {

            return value;
        }

        @Override
        public ImmutableSet<AccountType> accountTypes() {

            return accountTypes;
        }
    }

    @Override
    public String name() {

        return this.getClass().getName();
    }

    @Override
    public ImmutableSet<IModule.Permission> permissions() {

        return ImmutableSet.copyOf(Permission.values());
    }

    @Override
    public ImmutableSet<SubscriptionType> subscriptionTypes() {

        return ImmutableSet.of(SubscriptionType.FREE);
    }
}
