package com.tubecentric.webapplication.web.search.service;

import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.List;

public interface IRelatedTagsService {

    List<ExtractedResult> getRelatedTags(String query);
}
