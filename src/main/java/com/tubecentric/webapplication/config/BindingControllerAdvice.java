package com.tubecentric.webapplication.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
class BindingControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.initDirectFieldAccess();
    }
}