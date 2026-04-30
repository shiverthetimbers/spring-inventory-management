package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.service.InhousePartService;
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
public class PartControllerInhouse {

    private final InhousePartService inhousePartService;

    public PartControllerInhouse(InhousePartService inhousePartService) {
        this.inhousePartService = inhousePartService;
    }

    @GetMapping("/parts/inhouse/add")
    public String addInhousePart(Model model){

        InhousePart part = new InhousePart();

        model.addAttribute("inhousepart", part);

        return "partFormInhouse";
    }

    @PostMapping("/parts/inhouse/add")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, Errors errors, Model model){

        if(errors.hasErrors()){

            model.addAttribute("inhousepart", part);

            return "partFormInhouse";
        }

        inhousePartService.save(part);

        return "redirect:/mainscreen";
    }

}
