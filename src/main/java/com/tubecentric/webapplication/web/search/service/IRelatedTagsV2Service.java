package com.tubecentric.webapplication.web.search.service;

import com.tubecentric.webapplication.web.search.model.RankedTags;

public interface IRelatedTagsV2Service {

    RankedTags getRankedTags(String query);
}
