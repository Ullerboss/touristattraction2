package com.example.touristguide3.controller;

import com.example.touristguide3.model.Tag;
import com.example.touristguide3.model.TouristAttraction;
import com.example.touristguide3.service.AttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("attractions")
public class AttractionController {
    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping()
    public String getAttractions(Model model) {
        model.addAttribute("attractions", attractionService.getAttractions());
        return "attractions";
    }

    @GetMapping("{name}")
    public String getAttractionFromName(@PathVariable String name, Model model) {
        model.addAttribute("attraction", attractionService.getAttractionFromName(name));
        return "attraction";
    }

    @GetMapping("{name}/tags")
    public String getAttractionTagsFromName(@PathVariable String name, Model model) {
        model.addAttribute("attraction", attractionService.getAttractionFromName(name));
        return "tags";
    }

    @GetMapping("add")
    public String addAttractionForm(Model model) {
        model.addAttribute("attraction", new TouristAttraction());
        model.addAttribute("allTags", Tag.values());
        return "addAttractionForm";
    }

    @PostMapping("save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        attractionService.saveAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("{name}/edit")
    public String editAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = attractionService.getAttractionFromName(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("allTags", Tag.values());
        return "updateAttraction";
    }

    @PostMapping("update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        attractionService.updateAttraction(attraction);
        return "redirect:/attractions";
    }

    @PostMapping("{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        attractionService.deleteAttraction(name);
        return "redirect:/attractions";
    }
}