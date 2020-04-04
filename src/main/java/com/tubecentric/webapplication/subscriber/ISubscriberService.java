package com.tubecentric.webapplication.subscriber;

public interface ISubscriberService {

    void persistSubscriber(String email);

    boolean existSubscriber(String email);
}
