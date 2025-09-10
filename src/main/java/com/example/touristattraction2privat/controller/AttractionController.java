package com.example.touristattraction2privat.controller;

import com.example.touristattraction2privat.model.Tag;
import com.example.touristattraction2privat.model.TouristAttraction;
import com.example.touristattraction2privat.service.AttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("attractions")
public class AttractionController {
    private AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }


    @GetMapping()
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractionList = attractionService.getAllAttractions();
        model.addAttribute("attractionList", attractionList);
        return "attractionList";
    }

    @GetMapping("{name}")
    public String getAttractionFromName(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = attractionService.getAttractionFromName(name);
        model.addAttribute("attraction", touristAttraction);
        return "singleAttraction";
    }

    @GetMapping("{name}/tags")
    public String getAttractionTagsFromName(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = attractionService.getAttractionFromName(name);
        model.addAttribute("attraction", touristAttraction);
        return "tags";
    }

    @GetMapping("addAttraction")
    public String addAttractionForm(Model model){
        TouristAttraction attraction = new TouristAttraction();
        model.addAttribute("attraction",attraction);
        model.addAttribute("tags", Tag.values());
        return "addAttractionForm";
    }

    public String saveAttraction (@ModelAttribute TouristAttraction touristAttraction){
        attractionService.saveAttraction(touristAttraction);
    }


}
