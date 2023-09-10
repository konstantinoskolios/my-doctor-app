package com.example.mydoctorapp.controllers;

import com.example.mydoctorapp.services.CitizenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/citizens")
@RequiredArgsConstructor
@Slf4j
public class CitizenController {

    private final CitizenService citizenService;

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public String getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "10") int size,
                         @RequestParam(name = "sortField", defaultValue = "firstName") String sortField,
                         @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                         @RequestParam(name = "searchBy", required = false) String searchBy,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        return citizenService.getAllCitizens(page, size, sortField, sortDir, model, searchBy);
    }


}
