package com.tubecentric.webapplication.adwords.keywords.ideas;

import com.google.api.ads.adwords.axis.v201809.cm.Language;
import com.google.api.ads.adwords.axis.v201809.cm.NetworkSetting;
import com.google.api.ads.adwords.axis.v201809.cm.Paging;
import com.google.api.ads.adwords.axis.v201809.o.AttributeType;
import com.google.api.ads.adwords.axis.v201809.o.IdeaType;
import com.google.api.ads.adwords.axis.v201809.o.LanguageSearchParameter;
import com.google.api.ads.adwords.axis.v201809.o.NetworkSearchParameter;
import com.google.api.ads.adwords.axis.v201809.o.RelatedToQuerySearchParameter;
import com.google.api.ads.adwords.axis.v201809.o.RequestType;
import com.google.api.ads.adwords.axis.v201809.o.SearchParameter;
import com.google.api.ads.adwords.axis.v201809.o.TargetingIdeaPage;
import com.google.api.ads.adwords.axis.v201809.o.TargetingIdeaSelector;
import com.google.api.ads.adwords.axis.v201809.o.TargetingIdeaServiceInterface;
import com.tubecentric.webapplication.adwords.IAdWordsService;
import com.tubecentric.webapplication.adwords.keywords.ideas.model.TargetingIdeaRequest;
import com.tubecentric.webapplication.adwords.keywords.ideas.model.TargetingIdeaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ITargetingIdeaServiceImpl implements ITargetingIdeaService {

    private final IAdWordsService adWordsService;

    @Override
    public TargetingIdeaResponse getTargetingIdeas(TargetingIdeaRequest request) throws Exception {

        TargetingIdeaServiceInterface targetingIdeaService = getTargetIdeaServiceInterface();

        // Create selector.
        TargetingIdeaSelector selector = getTargetingIdeaSelector();
        selector.setPaging(getPaging(request.getStartIndex(), request.getNumberResults()));
        selector.setSearchParameters(getSearchParameters(request.getQuery()));

        // Get keyword ideas.
        TargetingIdeaPage page = targetingIdeaService.get(selector);

        return TargetingIdeaResponse.builder()
                .keywordIdeas(page.getEntries())
                .build();
    }

    private TargetingIdeaServiceInterface getTargetIdeaServiceInterface() throws Exception {

        return adWordsService.getAdWordsServices().get(adWordsService.getAdWordsSession(), TargetingIdeaServiceInterface.class);
    }

    private TargetingIdeaSelector getTargetingIdeaSelector() {

        TargetingIdeaSelector selector = new TargetingIdeaSelector();
        selector.setRequestType(RequestType.IDEAS);
        selector.setIdeaType(IdeaType.KEYWORD);
        selector.setRequestedAttributeTypes(new AttributeType[] {
                AttributeType.KEYWORD_TEXT,
                AttributeType.SEARCH_VOLUME,
                AttributeType.COMPETITION
        });

        return selector;
    }

    private Paging getPaging(int startIndex, int numberResults) {

        Paging paging = new Paging();
        paging.setStartIndex(startIndex);
        paging.setNumberResults(numberResults);

        return paging;
    }

    private SearchParameter[] getSearchParameters(String query) {

        SearchParameter[] searchParameters = new SearchParameter[3];
        searchParameters[0] = getRelatedToQuerySearchParameter(query);
        searchParameters[1] = getLanguageSearchParameter();
        searchParameters[2] = getSearchNetworkParameter();

        return searchParameters;
    }

    private RelatedToQuerySearchParameter getRelatedToQuerySearchParameter(String query) {

        RelatedToQuerySearchParameter relatedToQuerySearchParameter = new RelatedToQuerySearchParameter();
        relatedToQuerySearchParameter.setQueries(new String[] {query});

        return relatedToQuerySearchParameter;
    }

    private LanguageSearchParameter getLanguageSearchParameter() {

        Language english = new Language();
        english.setId(1000L);

        LanguageSearchParameter languageParameter = new LanguageSearchParameter();
        languageParameter.setLanguages(new Language[] {english});

        return languageParameter;
    }

    private NetworkSearchParameter getSearchNetworkParameter() {

        NetworkSetting networkSetting = new NetworkSetting();
        networkSetting.setTargetGoogleSearch(true);
        networkSetting.setTargetSearchNetwork(false);
        networkSetting.setTargetContentNetwork(false);
        networkSetting.setTargetPartnerSearchNetwork(false);

        NetworkSearchParameter networkSearchParameter = new NetworkSearchParameter();
        networkSearchParameter.setNetworkSetting(networkSetting);

        return networkSearchParameter;
    }
}
