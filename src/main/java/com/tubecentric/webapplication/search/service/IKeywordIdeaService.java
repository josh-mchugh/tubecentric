package com.tubecentric.webapplication.search.service;

import com.tubecentric.webapplication.search.service.model.KeywordIdeaRequest;
import com.tubecentric.webapplication.search.service.model.KeywordIdeaResponse;

public interface IKeywordIdeaService {

    KeywordIdeaResponse handleKeywordSearchResults(KeywordIdeaRequest request) throws Exception;
}
