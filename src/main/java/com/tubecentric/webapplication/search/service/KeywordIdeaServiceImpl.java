package com.tubecentric.webapplication.search.service;

import com.tubecentric.webapplication.adwords.keywords.ideas.ITargetingIdeaService;
import com.tubecentric.webapplication.adwords.keywords.ideas.model.TargetingIdeaRequest;
import com.tubecentric.webapplication.adwords.keywords.ideas.model.TargetingIdeaResponse;
import com.tubecentric.webapplication.metrics.search.service.ISearchMetricService;
import com.tubecentric.webapplication.search.mapper.KeywordMapper;
import com.tubecentric.webapplication.search.service.model.KeywordIdeaRequest;
import com.tubecentric.webapplication.search.service.model.KeywordIdeaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordIdeaServiceImpl implements IKeywordIdeaService {

    private final ITargetingIdeaService keywordIdeasService;
    private final ISearchMetricService searchMetricService;

    @Override
    public KeywordIdeaResponse handleKeywordSearchResults(KeywordIdeaRequest request) throws Exception {

        TargetingIdeaRequest ideasRequest = TargetingIdeaRequest.builder()
                .query(request.getQuery())
                .startIndex(request.getStartIndex())
                .numberResults(request.getNumberResults())
                .build();

        TargetingIdeaResponse ideasResponse = keywordIdeasService.getTargetingIdeas(ideasRequest);

        if(request.getStartIndex() == 0) {

            searchMetricService.recordSearchQuery(request.getQuery());
        }

        return KeywordIdeaResponse.builder()
                .keywords(KeywordMapper.map(ideasResponse.getKeywordIdeas()))
                .build();
    }
}
