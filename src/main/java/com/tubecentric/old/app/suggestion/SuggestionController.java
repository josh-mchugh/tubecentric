package com.tubecentric.old.app.suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/video-meta-builder/api/suggestion")
public class SuggestionController {

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/submit")
    public ResponseEntity submitSuggestion(@RequestBody Map<String, String> params, HttpServletRequest httpServletRequest) {

        //Get suggestion and uniqueness of the suggestion
        String suggestion = params.get("suggestion");
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String ipAddress;
        String local = httpServletRequest.getLocale().getDisplayCountry();

        //Get IP address
        if(httpServletRequest.getHeader("X-FORWARDED-FOR") != null) {

            ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");

        }else {

            ipAddress = httpServletRequest.getRemoteAddr();
        }

        //Send email in a separate thread to speed up response time.
        Runnable runnable = () -> sendEmail(suggestion, timeStamp, ipAddress, local);
        Thread thread = new Thread(runnable);
        thread.start();

        return new ResponseEntity(HttpStatus.OK);
    }

    private void sendEmail(String suggestion, String timeStamp, String ipAddress, String local) {

        String subject = String.format("Suggestion - %s", timeStamp);
        String body = String.format("Suggestion\n\n\n IP Address: %s \n Local: %s \n Time: %s \n Suggestion: \n\n %s", ipAddress, local, timeStamp, suggestion);

        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo("tubecentric@gmail.com");
        message.setFrom("no-reply@tubecentric.com");
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
    }
}
