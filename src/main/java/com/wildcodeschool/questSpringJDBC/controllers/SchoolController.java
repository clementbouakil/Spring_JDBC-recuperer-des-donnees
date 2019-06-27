package com.wildcodeschool.questSpringJDBC.controllers;

import java.util.List;

import com.wildcodeschool.questSpringJDBC.repositories.SchoolRepository;
import com.wildcodeschool.questSpringJDBC.entities.School;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ResponseBody
public class SchoolController {

    @GetMapping("/api/schools")
    public List<School> getSchools(@RequestParam(defaultValue = "%") String country) {
        return SchoolRepository.selectByCountry(country);
    }

    @PostMapping("/api/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public School store(
        @RequestParam String name,
        @RequestParam int capacity,
        @RequestParam String country
    ) {
        int idGeneratedByInsertion = SchoolRepository.insert(name, capacity, country);
        return SchoolRepository.selectById(idGeneratedByInsertion);
    }

}
