package com.move.move.search.searchperson.controller;

import com.move.move.search.searchperson.dto.PersonSearchListResponseDto;
import com.move.move.search.searchperson.service.PersonSearchListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/person-search")
public class PersonSearchListController {

    private final PersonSearchListService personSearchListService;

    public PersonSearchListController(PersonSearchListService personSearchListService) {
        this.personSearchListService = personSearchListService;
    }

    @GetMapping
    public String getPersonSearch(){
        return "person/person-search";
    }

    @GetMapping("/person-list")
    public String getMovieList(Model model, @RequestParam(required = false) String personName, @RequestParam(required = false) String filmoName) {
        PersonSearchListResponseDto personSearchListResponseDto = personSearchListService.getPersonSearchList(personName,filmoName);
        model.addAttribute("personSearchListResponseDto",personSearchListResponseDto);
        return "person/person-list";
    }
}
