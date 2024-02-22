package com.move.move.controller;

import com.move.move.service.PersonDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PersonDetailController {

    private final PersonDetailService personDetailService;

    public PersonDetailController(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }


    @GetMapping("/person-detail/{personCode}")
    public String personDetail(@PathVariable String personCode, Model model){



        return "person-detail";
    }

}
