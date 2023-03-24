package com.codeup.codeupspringblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class FirstController
{
    @PostMapping("/pizzaOrder")
    public String placePizzaOrder(@RequestParam String crust, @RequestParam String sauce)
    {
        return null;
    }


    @GetMapping("/hello")
    @ResponseBody
    public String returnHelloWorld(@RequestParam(defaultValue = "false") boolean uppercase)
    {
        if(uppercase)
        {
            return "HELLO WORLD";
        }
        return "Hello World";
    }

    @GetMapping("/hello/{fname}/{lname}")
    @ResponseBody
    public String greetName(@PathVariable String fname, @PathVariable String lname)
    {
        return "Hello " + fname + " " + lname;
    }


}
