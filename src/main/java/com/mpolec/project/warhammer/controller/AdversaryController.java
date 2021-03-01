package com.mpolec.project.warhammer.controller;

import com.mpolec.project.warhammer.entity.AdversaryEntity;
import com.mpolec.project.warhammer.entity.CharacteristicsEntity;
import com.mpolec.project.warhammer.service.AdversaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adversary")
public class AdversaryController {

    private final AdversaryService adversaryService;

    public AdversaryController(AdversaryService adversaryService) {
        this.adversaryService = adversaryService;
    }

    @GetMapping("/list")
    public String listAdversary(Model model) {

        List<AdversaryEntity> adversaries = adversaryService.findAll();
        model.addAttribute("adversaries", adversaries);

        return "adversary/adversary-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        AdversaryEntity adversary = new AdversaryEntity();
        CharacteristicsEntity characteristics = new CharacteristicsEntity();

        adversary.setCharacteristics(characteristics);

        model.addAttribute("adversary", adversary);

        return "adversary/adversary-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("adversaryId") int id, Model model){

        AdversaryEntity adversary = adversaryService.findById(id);
        model.addAttribute("adversary", adversary);

        return "adversary/adversary-form";
    }

    @PostMapping("/save")
    public String saveAdversary(@ModelAttribute("adversary") AdversaryEntity adversary){
        adversaryService.save(adversary);

        return "redirect:/adversary/list";
    }
}
