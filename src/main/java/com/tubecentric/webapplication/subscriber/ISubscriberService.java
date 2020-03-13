package com.tubecentric.webapplication.subscriber;

public interface ISubscriberService {

    void handleSubscriberSave(String email);

    boolean existSubscriber(String email);
}
