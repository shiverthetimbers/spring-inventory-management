package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class AddOutsourcedPartController {
    private final OutsourcedPartService outsourcedPartService;

    public AddOutsourcedPartController(OutsourcedPartService outsourcedPartService) {
        this.outsourcedPartService = outsourcedPartService;
    }

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel){
        Part part = new OutsourcedPart();
        theModel.addAttribute("outsourcedpart", part);
        return "outsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult bindingResult, Model theModel){

        if(bindingResult.hasErrors()){

            theModel.addAttribute("outsourcedpart",part);
            return "outsourcedPartForm";
        }

        OutsourcedPart existingPart = outsourcedPartService.findById((int)part.getId());
        if (existingPart !=null) {

            // Preserve existing product associations when updating a part
            part.setProducts(existingPart.getProducts());
        }

        outsourcedPartService.save(part);
        return "confirmationAddPart";
    }

}
