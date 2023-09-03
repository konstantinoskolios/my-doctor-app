package com.example.mydoctorapp.controllers;

import com.example.mydoctorapp.models.Citizen;
import com.example.mydoctorapp.services.DummyEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

@Controller
@RequestMapping("/weird")
@RequiredArgsConstructor
@Slf4j
public class DummyController {

    private final DummyEntityService dummyEntityService;

    @GetMapping(value = "/dummy/save")
    @ResponseStatus(HttpStatus.OK)
    public String testSave(Model model) {

        var dummyEntity = dummyEntityService.dummySave();
        model.addAttribute("userId", dummyEntity.getId());
        model.addAttribute("userName", dummyEntity.getName());

        var citizens = new ArrayList<Citizen>();
        citizens.add(new Citizen("1", "2", "3", "4", "5"));
        citizens.add(new Citizen("2", "2", "3", "4", "5"));
        citizens.add(new Citizen("3", "2", "3", "4", "5"));
        citizens.add(new Citizen("4", "2", "3", "4", "5"));

        model.addAttribute("citizens", citizens);
        return "hello";
    }
}
