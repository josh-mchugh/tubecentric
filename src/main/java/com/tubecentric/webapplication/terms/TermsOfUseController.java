package com.tubecentric.webapplication.terms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/terms-of-use")
public class TermsOfUseController {

    @GetMapping("")
    private String getDefault() {

        return "landing/view-terms-of-use";
    }
}
