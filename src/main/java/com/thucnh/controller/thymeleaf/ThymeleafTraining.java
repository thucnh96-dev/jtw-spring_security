package com.thucnh.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafTraining {

    @GetMapping(value = "/thymeleaf")
    public String view(Model model){
        List<String> loop = new ArrayList<>();
        loop.add("LOOP1");
        loop.add("LOOP2");
        loop.add("LOOP3");
        model.addAttribute("loops",loop);


        return "thymeleaf/training";
    }
}
