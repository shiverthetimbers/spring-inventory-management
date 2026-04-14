package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddPartController {
    private final PartService partService;

    public AddPartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/showPartFormForUpdate")
    public String showPartFormForUpdate(@RequestParam("partID") int theId, Model theModel){

        Part currPart = partService.findById(theId);

        if (currPart instanceof InhousePart inhousePart) {

            theModel.addAttribute("inhousepart", inhousePart);
            return "inhousePartForm";
        }

        if (currPart instanceof OutsourcedPart outsourcedPart) {

            theModel.addAttribute("outsourcedpart", outsourcedPart);
            return "outsourcedPartForm";
        }

        return "errorGeneral";

    }

    @GetMapping("/deletepart")
    public String deletePart(@Valid @RequestParam("partID") int theId){

        Part part = partService.findById(theId);

        if (part.getProducts().isEmpty()){

            partService.deleteById(theId);
            return "confirmationDeletePart";
        }

        return "errorNegative";
    }

}
