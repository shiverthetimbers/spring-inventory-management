package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 *
 *
 *
 */
@Controller
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/parts/{partID}/edit")
    public String editPart(@PathVariable("partID") long partId, Model model){

        Part currPart = partService.findById(partId);

        if (currPart instanceof InhousePart inhousePart) {

            model.addAttribute("inhousepart", inhousePart);
            return "partFormInhouse";
        }

        if (currPart instanceof OutsourcedPart outsourcedPart) {

            model.addAttribute("outsourcedpart", outsourcedPart);
            return "partFormOutsourced";
        }

        return "errorGeneral";

    }

    @GetMapping("/parts/{partID}/delete")
    public String deletePart(@PathVariable("partID") long partId, RedirectAttributes redirectAttributes){

        boolean allowed = partService.deleteIfUnused(partId);

        if (!allowed) {
            redirectAttributes.addFlashAttribute("errorMessage", "This part is associated with at least one recipe and cannot be deleted");
        }

        return "redirect:/mainscreen";
    }

}
