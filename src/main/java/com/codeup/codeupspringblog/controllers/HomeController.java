package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController
{
    private final EmailService emailService;

    public HomeController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String welcome()
    {
//        emailService.prepareAndSend("test", "this is a test");
        System.out.println("email sent!!!!");
        return "hello";
    }
}
