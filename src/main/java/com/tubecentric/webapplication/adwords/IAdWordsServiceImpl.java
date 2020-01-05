package com.tubecentric.webapplication.adwords;

import com.google.api.ads.adwords.axis.factory.AdWordsServices;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServicesInterface;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.tubecentric.webapplication.framework.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IAdWordsServiceImpl implements IAdWordsService {

    private final AppConfig appConfig;

    @Override
    public AdWordsSession getAdWordsSession() throws Exception {

        return new AdWordsSession.Builder()
                .withDeveloperToken(appConfig.getAdWords().getDeveloperToken())
                .withClientCustomerId(appConfig.getAdWords().getClientCustomerId())
                .withOAuth2Credential(getOAuth2Credential())
                .build();
    }

    @Override
    public AdWordsServicesInterface getAdWordsServices() {

        return AdWordsServices.getInstance();
    }

    private Credential getOAuth2Credential() {

        return new GoogleCredential.Builder()
                .setTransport(getNetHttpTransport())
                .setJsonFactory(getJacksonFactory())
                .setClientSecrets(appConfig.getAdWords().getClientId(), appConfig.getAdWords().getClientSecret())
                .build()
                .setRefreshToken(appConfig.getAdWords().getRefreshToken());
    }

    private NetHttpTransport getNetHttpTransport() {

        return new NetHttpTransport();
    }

    private JacksonFactory getJacksonFactory() {

        return new JacksonFactory();
    }
}
