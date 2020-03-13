package com.tubecentric.webapplication.web.keyword.service;

import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.List;

public interface ITagSearchService {

    List<ExtractedResult> getSearchTags(String query);
}
