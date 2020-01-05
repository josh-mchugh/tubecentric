package com.tubecentric.webapplication.extension;

import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.List;

public interface ITagSearchService {

    List<ExtractedResult> getSearchTags();
}
