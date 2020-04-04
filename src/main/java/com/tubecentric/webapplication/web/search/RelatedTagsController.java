package com.tubecentric.webapplication.web.search;

import com.tubecentric.webapplication.web.search.service.IRelatedTagsService;
import lombok.RequiredArgsConstructor;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag-search")
@RequiredArgsConstructor
public class RelatedTagsController {

    private final IRelatedTagsService relatedTagsService;

    @GetMapping("")
    public List<ExtractedResult> getRelatedTags(@RequestParam("query") String query) {

        return relatedTagsService.getRelatedTags(query);
    }
}
