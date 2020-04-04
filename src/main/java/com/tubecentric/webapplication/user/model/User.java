package com.tubecentric.webapplication.user.model;

import com.google.common.collect.ImmutableSet;
import com.tubecentric.webapplication.user.entity.AccountType;
import com.tubecentric.webapplication.user.entity.SubscriptionType;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class User {

    private final String id;
    private final String sub;
    private final String name;
    private final String email;
    private final Boolean emailedVerified;
    private final String imageURL;
    private final String locale;
    private final AccountType accountType;
    private final SubscriptionType subscriptionType;
    private final UserAccessToken userAccessToken;

    @Singular
    private final ImmutableSet<String> permissions;
}
