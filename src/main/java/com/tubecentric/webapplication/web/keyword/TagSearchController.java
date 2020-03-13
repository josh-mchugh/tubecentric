package com.tubecentric.webapplication.web.keyword;

import com.tubecentric.webapplication.web.keyword.service.ITagSearchService;
import lombok.AllArgsConstructor;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag-search")
@AllArgsConstructor
public class TagSearchController {

    private final ITagSearchService tagSearchService;

    @CrossOrigin
    @GetMapping("")
    public List<ExtractedResult> getYouTubeVideoIds(@RequestParam("query") String query) {

        return tagSearchService.getSearchTags(query);
    }

}
