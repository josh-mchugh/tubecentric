package com.tubecentric.webapplication.search.controller;

import com.tubecentric.webapplication.search.controller.model.KeywordSearchDefaultRequest;
import com.tubecentric.webapplication.search.controller.model.KeywordSearchListRequest;
import com.tubecentric.webapplication.search.controller.model.KeywordSearchListResponse;
import com.tubecentric.webapplication.search.service.IKeywordIdeaService;
import com.tubecentric.webapplication.search.service.model.KeywordIdeaRequest;
import com.tubecentric.webapplication.search.service.model.KeywordIdeaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/youtube-keyword-search")
public class KeywordSearchController {

    private final IKeywordIdeaService keywordIdeaSearchService;

    @GetMapping("")
    public String getDefault(Model model, KeywordSearchDefaultRequest request) {

        model.addAttribute("query", request.getQuery());

        return "landing/view-keyword-search";
    }

    @PostMapping("/list")
    public ResponseEntity<KeywordSearchListResponse> getKeywordSuggestionsTest(KeywordSearchListRequest request) {

        try {

            KeywordIdeaRequest searchRequest = KeywordIdeaRequest.builder()
                    .query(request.getQuery())
                    .startIndex(request.getStartIndex())
                    .numberResults(request.getNumberResults())
                    .build();

            KeywordIdeaResponse searchResponse = keywordIdeaSearchService.handleKeywordSearchResults(searchRequest);

            KeywordSearchListResponse response = KeywordSearchListResponse.builder()
                    .data(searchResponse.getKeywords())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e) {

            log.error("Unable to retrieve keyword ideas.", e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
