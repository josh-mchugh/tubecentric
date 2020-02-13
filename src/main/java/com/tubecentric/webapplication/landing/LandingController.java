package com.tubecentric.webapplication.landing;

import com.tubecentric.webapplication.landing.entity.service.ISubscriberService;
import com.tubecentric.webapplication.landing.model.SubscribeForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class LandingController {

    private final ISubscriberService subscriberService;

    @GetMapping("")
    public String getDefault(Model model) {

        model.addAttribute("form", new SubscribeForm());

        return "landing/view-new-landing";
    }

    @GetMapping("/privacy-policy")
    public String getPrivacyPolicy() {

        return "landing/view-new-privacy-policy";
    }

    @GetMapping("/terms-of-service")
    public String getTermsOfService() {

        return "landing/view-new-terms-of-service";
    }

    @PostMapping("/subscribe")
    public String getSubscribe(@Valid @ModelAttribute("form") SubscribeForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            return "landing/fragments/partial-subscribe-section";
        }

        subscriberService.handleSubscriberSave(form.getEmail());

        return "landing/fragments/partial-subscribe-success-section";
    }
}
