package com.example.Ivzilol.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello(Model model,
                            @RequestParam("num") Integer num) {
        model.addAttribute("num", num);
        return "helloWorld";
    }
}
