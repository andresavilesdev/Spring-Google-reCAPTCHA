package com.google.employee.controller;

import com.google.employee.controller.dto.EmployeeDto;
import com.google.employee.entity.EmployeeEntity;
import com.google.employee.service.IEmployeeService;
import com.google.recaptcha.service.RecaptchaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    private final IEmployeeService employeeService;
    private final ModelMapper modelMapper;
    private final RecaptchaService recaptchaService;

    public EmployeeController(IEmployeeService employeeService, ModelMapper modelMapper, RecaptchaService recaptchaService) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.recaptchaService = recaptchaService;
    }

    @GetMapping({"/", "/all"})
    public String showAll(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "index";
    }

    @GetMapping("/create/form")
    public String createForm(Model model) {
        model.addAttribute("employee", new EmployeeEntity());
        return "form";
    }

    @PostMapping("/create/process")
    public String create(@ModelAttribute(name = "employee") EmployeeDto employeeDto, @RequestParam(name = "g-recaptcha-response")String captcha, Model model) {

        boolean captchaValid = recaptchaService.validateRecaptcha(captcha);

        if (captchaValid) {
            employeeService.save(modelMapper.map(employeeDto, EmployeeEntity.class));
            return "redirect:/all";
        }else{
            model.addAttribute("message", "Invalid Recaptcha Response");
            return "error";
        }

    }

}
