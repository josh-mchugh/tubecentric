package com.tubecentric.webapplication.web.search;

import com.tubecentric.webapplication.web.search.model.RankedTags;
import com.tubecentric.webapplication.web.search.service.IRelatedTagsV2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/tag-search")
@RequiredArgsConstructor
public class RelatedTagsV2Controller {

    private final IRelatedTagsV2Service relatedTagsService;

    @GetMapping("")
    public RankedTags getRelatedTags(@RequestParam("query") String query) {

        return relatedTagsService.getRankedTags(query);
    }
}
