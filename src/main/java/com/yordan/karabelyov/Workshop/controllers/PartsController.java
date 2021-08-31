package com.yordan.karabelyov.Workshop.controllers;

import com.yordan.karabelyov.Workshop.model.SparePart;
import com.yordan.karabelyov.Workshop.service.SparePartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class PartsController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SparePartService sparePartService;


    @PostMapping("/parts/management/editPart")
    public String editPart(SparePart part, Model model) {

        SparePart sparePart = sparePartService.findByCode(part.getCode());

        if (sparePart != null) {
            model.addAttribute("part", sparePart);
            return "/parts/management/edit-part";
        }
        return "parts/does-not-exists";
    }

    @RequestMapping("/parts/management/searchPart")
    public String search(Model model) {

        model.addAttribute("part", new SparePart());

        return "parts/management/search-part";
    }

    @PostMapping("/parts/management/save")
    public String savePart(@Valid SparePart part, Errors errors, Model model) {
        if (sparePartService.exists(part.getCode())) {
            model.addAttribute("error", "Already exists");
            model.addAttribute("part", part);
            return "parts/management/new-part";
        }
        if (errors.hasErrors()) {
            logger.info("Part controller has errors");
            return "parts/management/new-part";
        }
        sparePartService.save(part);
        return "redirect:/";
    }

    @PostMapping("/parts/management/edit")
    public String editPart(@Valid SparePart part, Errors errors, Model model) {

        if (errors.hasErrors()) {
            logger.info("Part controller has errors");
            return "parts/management/new-part";
        }
        sparePartService.save(part);
        return "redirect:/";
    }

    @GetMapping("/parts/management/newPart")
    public String addPart(Model model) {

        model.addAttribute("part", new SparePart());

        return "parts/management/new-part";
    }


    @GetMapping("/parts/management/delete")
    public String toDeletePart(Model model) {
            model.addAttribute("part", new SparePart());
            return "parts/management/delete-part";
    }

    @PostMapping("/parts/management/partDelete")
    public String deletePart(SparePart part, Model model) {
        if (!sparePartService.exists(part.getCode())){
            model.addAttribute("error", "This part does not exist");
            model.addAttribute("part", part);
            return "parts/management/delete-part";
        }
        SparePart sparePart = sparePartService.findByCode(part.getCode());
        sparePartService.delete(sparePart);
        return "redirect:/";
    }

}
