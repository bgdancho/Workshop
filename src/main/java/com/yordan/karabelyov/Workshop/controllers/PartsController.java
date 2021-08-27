package com.yordan.karabelyov.Workshop.controllers;

import com.yordan.karabelyov.Workshop.model.SparePart;
import com.yordan.karabelyov.Workshop.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PartsController {
    @Autowired
    SparePartService sparePartService;


    @PostMapping("/parts/management/editPart")
    public String editPart(SparePart part, Model model){

        SparePart sparePart = sparePartService.findByCode(part.getCode());

        if (sparePart != null){
            model.addAttribute("part", sparePart);
            return "/parts/management/edit-part";
        }
        return "parts/does-not-exists";
    }

    @RequestMapping("/parts/management/searchPart")
    public String search(Model model){

        model.addAttribute("part",new SparePart());

            return "parts/management/search-part";
    }
    @PostMapping("/parts/management/save")
    public String savePart(SparePart part){
       sparePartService.save(part);
        return "redirect:/";
    }
}
