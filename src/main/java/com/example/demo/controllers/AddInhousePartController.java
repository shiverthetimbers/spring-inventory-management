//package com.example.demo.controllers;
//
//import com.example.demo.domain.InhousePart;
//import com.example.demo.service.InhousePartService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//
///**
// *
// *
// *
// *
// */
//@Controller
//public class AddInhousePartController{
//
//    private final InhousePartService inhousePartService;
//
//    public AddInhousePartController(InhousePartService inhousePartService) {
//        this.inhousePartService = inhousePartService;
//    }
//
//    @GetMapping("/showFormAddInPart")
//    public String showFormAddInhousePart(Model theModel){
//        InhousePart inhousepart = new InhousePart();
//        theModel.addAttribute("inhousepart",inhousepart);
//        return "inhousePartForm";
//    }
//
//    @PostMapping("/showFormAddInPart")
//    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult bindingResult, Model model){
//
//        if(bindingResult.hasErrors()){
//
//            model.addAttribute("inhousepart",part);
//            return "inhousePartForm";
//        }
//
//        InhousePart existingPart = inhousePartService.findById((int) part.getId());
//        if (existingPart != null) {
//
//            // Preserve existing product associations when updating a part
//            part.setProducts(existingPart.getProducts());
//        }
//
//        inhousePartService.save(part);
//        return "confirmationAddPart";
//    }
//
//}
