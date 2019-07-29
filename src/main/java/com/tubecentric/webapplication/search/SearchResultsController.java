package com.tubecentric.webapplication.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchResultsController {

    @GetMapping("")
    public String getDefault() {

        return "landing/view-search-results";
    }
}
