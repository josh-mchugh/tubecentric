package com.tubecentric.webapplication.register.controller;

import com.tubecentric.webapplication.register.controller.model.QuestionnaireForm;
import com.tubecentric.webapplication.register.controller.model.RegistrationForm;
import com.tubecentric.webapplication.register.service.IRegistrationService;
import com.tubecentric.webapplication.register.service.model.QuestionnairePersistRequest;
import com.tubecentric.webapplication.register.service.model.QuestionnairePersistResponse;
import com.tubecentric.webapplication.register.service.model.RegistrationPersistRequest;
import com.tubecentric.webapplication.register.service.model.RegistrationPersistResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegisterController {

    private final IRegistrationService registrationService;

    @GetMapping("")
    public String getDefault(Model model) {

        model.addAttribute("form", new RegistrationForm());

        return "landing/view-register";
    }

    @PostMapping("/submit")
    public String postRegistrationSubmission(Model model, @Valid @ModelAttribute("form") RegistrationForm form, BindingResult result) {

        if(result.hasErrors()) {

            return "redirect:/registration";
        }

        RegistrationPersistRequest request = RegistrationPersistRequest.builder()
                .email(form.getEmail())
                .build();

        RegistrationPersistResponse response = registrationService.persistRegistration(request);

        return String.format("redirect:/registration/%s/follow-up", response.getId());
    }

    @GetMapping("/{id}/follow-up")
    public String postRegistrationFollowUp(Model model, @PathVariable("id") String id) {

        model.addAttribute("form", new QuestionnaireForm());

        return "landing/view-register-follow-up";
    }

    @PostMapping("/{id}/follow-up/submit")
    public String postRegistrationSuccess(Model model, @PathVariable("id") String id, @Valid @ModelAttribute("form") QuestionnaireForm form, BindingResult result) {

        if(result.hasErrors()) {

            return "redirect:/registration/follow-up";
        }

        QuestionnairePersistRequest request = QuestionnairePersistRequest.builder()
                .id(id)
                .featureSet(form.getFeatureSets())
                .reportingIntervals(form.getReportingIntervals())
                .frustrations(form.getFrustrations())
                .allowFutureQuestionnaires(form.isAllowFutureQuestionnaires())
                .build();

        QuestionnairePersistResponse response = registrationService.persistQuestionnaire(request);

        return String.format("redirect:/registration/%s/complete", response.getId());
    }

    @GetMapping("/{id}/complete")
    public String getRegistrationComplete(@PathVariable("id") String id) {

        return "landing/view-register-success";
    }
}
