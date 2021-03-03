package com.mpolec.project.warhammer.controller;

import com.mpolec.project.warhammer.entity.AdversaryEntity;
import com.mpolec.project.warhammer.entity.FightEntity;
import com.mpolec.project.warhammer.model.AdversariesList;
import com.mpolec.project.warhammer.model.AdversaryModel;
import com.mpolec.project.warhammer.model.FightForm;
import com.mpolec.project.warhammer.model.AttackModel;
import com.mpolec.project.warhammer.service.AdversaryService;
import com.mpolec.project.warhammer.service.FightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.mpolec.project.warhammer.utils.FightUtils.*;

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

        int i = 0;
        for (AdversaryEntity adversaryEntity : adversariesEntity) {
            AdversaryModel adversary = new AdversaryModel();
            adversary.setId(i);
            adversary.setName(adversaryEntity.getName());
            adversary.setCharacteristics(adversaryEntity.getCharacteristics());
            adversary.setInitiative(0);
            adversariesList.addAdversary(adversary);
            i++;
        }

        AttackModel targetAdversary = new AttackModel();

        model.addAttribute("targetAdversary", targetAdversary);
        model.addAttribute("adversariesList", adversariesList);
        session.setAttribute("adversariesList", adversariesList);

        return "fight/fight-panel";
    }

    @GetMapping("/updateFight")
    public String updateFight(HttpSession session, Model model){

        AdversariesList adversariesList = (AdversariesList) session.getAttribute("adversariesList");
        model.addAttribute("adversariesList", adversariesList);

        return "fight/fight-panel";
    }

    @PostMapping("/initiativeRoll")
    public String initiativeRoll(@ModelAttribute("adversariesList") AdversariesList adversaries, HttpSession session){

        calculateInitiative(adversaries);
        session.setAttribute("adversariesList", adversaries);

        return "redirect:/fight/updateFight";
    }

    @GetMapping("/showFormForAttack")
    public String showFormForAttack(@RequestParam("attackerId") int id, Model model, HttpSession session){

        AdversariesList adversariesList = (AdversariesList) session.getAttribute("adversariesList");
        AttackModel attackModel = AttackModel.prepareAttackModel(id, adversariesList);
        model.addAttribute("attackModel", attackModel);

        return "fight/attack-form";
    }

    @PostMapping("/attack")
    public String attack(@ModelAttribute("attackModel") AttackModel attackModel, HttpSession session, Model model){

        AdversariesList adversariesList = (AdversariesList) session.getAttribute("adversariesList");
        attackModel.updateAttackModel(adversariesList);
        model.addAttribute("attackModel", attackModel);

        return "fight/attack-result";
    }
}
