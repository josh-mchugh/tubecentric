package com.tubecentric.webapplication.adwords.keywords.ideas;

import com.tubecentric.webapplication.adwords.keywords.ideas.model.TargetingIdeaRequest;
import com.tubecentric.webapplication.adwords.keywords.ideas.model.TargetingIdeaResponse;

public interface ITargetingIdeaService {

    TargetingIdeaResponse getTargetingIdeas(TargetingIdeaRequest request) throws Exception;
}
