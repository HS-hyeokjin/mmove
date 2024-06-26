package com.move.move.personinfo.controller;

import com.move.move.personinfo.dto.PersonDetailResponse;
import com.move.move.personinfo.service.PersonDetailService;
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

    @GetMapping("/person-detail/{personName}")
    public String personDetail(@PathVariable String personName, Model model){
        PersonDetailResponse personDetailResponse = personDetailService.getPersonDetail(personName);
        model.addAttribute("personDetailData", personDetailResponse);
        return "person/person-detail";
    }

}
