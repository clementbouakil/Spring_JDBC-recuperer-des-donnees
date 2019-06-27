package com.wildcodeschool.questSpringJDBC.controllers;

import java.util.List;

import com.wildcodeschool.questSpringJDBC.repositories.SchoolRepository;
import com.wildcodeschool.questSpringJDBC.entities.School;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class SchoolController {

    @GetMapping("/api/schools")
    public List<School> getSchools(@RequestParam(defaultValue = "%") String country) {
        return SchoolRepository.selectByCountry(country);
    }
}
