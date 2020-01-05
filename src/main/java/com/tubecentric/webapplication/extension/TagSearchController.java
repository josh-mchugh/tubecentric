package com.tubecentric.webapplication.extension;

import lombok.AllArgsConstructor;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag-search")
@AllArgsConstructor
public class TagSearchController {

    private final ITagSearchService tagSearchService;

    @GetMapping(value="/test")
    public List<ExtractedResult> getYouTubeVideoIds() {

        return tagSearchService.getSearchTags();
    }

}
