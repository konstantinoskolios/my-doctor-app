package com.example.mydoctorapp.controllers;

import com.example.mydoctorapp.model.DateRangeModel;
import com.example.mydoctorapp.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.mydoctorapp.constants.Constants.ADMIN_VIEW;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/view")
    public String doctorView(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute("fullName", user.getFullName());
        return ADMIN_VIEW;
    }

    @PostMapping("metrics/business")
    @ResponseBody
    public String getBusinessMetrics(@AuthenticationPrincipal OidcUser user, @RequestBody DateRangeModel dateRangeModel) {
        return adminService.getBusinessMetrics(dateRangeModel);
    }
}
