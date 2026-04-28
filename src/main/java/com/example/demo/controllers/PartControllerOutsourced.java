package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class PartControllerOutsourced {
    private final OutsourcedPartService outsourcedPartService;

    public PartControllerOutsourced(OutsourcedPartService outsourcedPartService) {
        this.outsourcedPartService = outsourcedPartService;
    }

    @GetMapping("/parts/outsourced/add")
    public String addOutsourcedPart(Model model){

        OutsourcedPart part = new OutsourcedPart();

        model.addAttribute("outsourcedpart", part);

        return "partFormOutsourced";
    }

    @PostMapping("/parts/outsourced/add")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, Errors errors, Model model){

        if(errors.hasErrors()){

            model.addAttribute("outsourcedpart",part);

            return "partFormOutsourced";
        }

        outsourcedPartService.save(part);

        return "redirect:/mainscreen";
    }
}
