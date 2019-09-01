package com.tubecentric.webapplication.adwords;

import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServicesInterface;

public interface IAdWordsService {

    AdWordsSession getAdWordsSession() throws Exception;

    AdWordsServicesInterface getAdWordsServices();
}
