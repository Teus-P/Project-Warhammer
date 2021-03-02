package com.mpolec.project.warhammer.controller;

import com.mpolec.project.warhammer.entity.AdversaryEntity;
import com.mpolec.project.warhammer.entity.FightEntity;
import com.mpolec.project.warhammer.model.AdversaryModel;
import com.mpolec.project.warhammer.model.AdversariesList;
import com.mpolec.project.warhammer.model.FightForm;
import com.mpolec.project.warhammer.service.AdversaryService;
import com.mpolec.project.warhammer.service.FightService;
import com.mpolec.project.warhammer.utils.InitiativeSorter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fight")
public class FightController {

    private final FightService fightService;
    private final AdversaryService adversaryService;

    public FightController(FightService fightService, AdversaryService adversaryService) {
        this.fightService = fightService;
        this.adversaryService = adversaryService;
    }

    @GetMapping("/list")
    public String listFight(Model model) {

        List<FightEntity> fights = fightService.findAll();
        model.addAttribute("fights", fights);

        return "fight/fight-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model, HttpSession session) {

        FightForm fightForm = new FightForm();
        model.addAttribute("fightForm", fightForm);

        List<AdversaryEntity> adversaries = adversaryService.findAll();
        session.setAttribute("adversaries", adversaries);

        return "fight/fight-form";
    }

    @PostMapping("/save")
    public String saveFight(@ModelAttribute("fightForm") FightForm fightForm){

        FightEntity fight = new FightEntity();
        fight.setId(fightForm.getId());
        fight.setFightName(fightForm.getFightName());

        ArrayList<AdversaryEntity> adversaries = new ArrayList<>();

        for (Integer adversaryId : fightForm.getAdversariesId()) {
            adversaries.add(adversaryService.findById(adversaryId));
        }

        fight.setAdversaries(adversaries);
        fightService.save(fight);

        return "redirect:/fight/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("fightId") int id, Model model){

        FightEntity fight = fightService.findById(id);
        model.addAttribute("fight", fight);

        List<AdversaryEntity> adversaries = adversaryService.findAll();
        model.addAttribute("adversaries", adversaries);

        return "fight/fight-form";
    }

    @GetMapping("/startFight")
    public String startFight(@RequestParam("fightId") int id, HttpSession session, Model model) {

        FightEntity fight = fightService.findById(id);
        session.setAttribute("fight", fight);

        ArrayList<AdversaryEntity> adversariesEntity = new ArrayList<>(fight.getAdversaries());
        AdversariesList adversariesList = new AdversariesList();

        for (AdversaryEntity adversaryEntity : adversariesEntity) {
            AdversaryModel adversary = new AdversaryModel();
            adversary.setId(adversary.getId());
            adversary.setName(adversaryEntity.getName());
            adversary.setCharacteristics(adversaryEntity.getCharacteristics());
            adversary.setInitiative(0);
            adversariesList.addAdversary(adversary);
        }

        model.addAttribute("adversariesList", adversariesList);
        session.setAttribute("adversariesList", adversariesList);

        return "fight/start-fight";
    }

    @GetMapping("/updateFight")
    public String updateFight(HttpSession session, Model model){

        AdversariesList adversariesList = (AdversariesList) session.getAttribute("adversariesList");
        model.addAttribute("adversariesList", adversariesList);

        return "fight/start-fight";
    }

    @PostMapping("/initiativeRoll")
    public String initiativeRoll(@ModelAttribute("adversariesList") AdversariesList adversaries, HttpSession session){

        for (AdversaryModel adversary : adversaries.getAdversaries()) {
            adversary.setInitiative(adversary.getInitiative() + adversary.getCharacteristics().getInitiative());
        }

        adversaries.getAdversaries().sort(new InitiativeSorter());
        session.setAttribute("adversariesList", adversaries);

        return "redirect:/fight/updateFight";
    }
}
