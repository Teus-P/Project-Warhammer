package com.mpolec.project.warhammer.utils;

import com.mpolec.project.warhammer.model.AdversariesList;
import com.mpolec.project.warhammer.model.AdversaryModel;
import com.mpolec.project.warhammer.model.AttackModel;

public class FightUtils {

    public static int calculateSuccessLevel(int skillRoll, int skillNumber) {
        return (skillNumber / 10) - (skillRoll / 10);
    }

    public static int compareAttackerAndDefenderSuccessLevels(int attackerSuccessLevel, int defenderSuccessLevel){
        return attackerSuccessLevel - defenderSuccessLevel;
    }

    public static int calculateAttack(AdversaryModel attacker, AdversaryModel defender, AttackModel attackModel){

        int attackerSkillNumber = attacker.getCharacteristics().getWeaponSkill();
        int defenderSkillNumber = defender.getCharacteristics().getWeaponSkill();

        int attackSuccessLevel = calculateSuccessLevel(attackModel.getAttackerRoll(), attackerSkillNumber);
        int defenderSuccessLevel = calculateSuccessLevel(attackModel.getDefenderRoll(), defenderSkillNumber);

        int levelSuccessDifference = compareAttackerAndDefenderSuccessLevels(attackSuccessLevel, defenderSuccessLevel);

        if(levelSuccessDifference > 0) {
            return levelSuccessDifference;
        }
        else if (levelSuccessDifference == 0) {
            if(attackerSkillNumber > defenderSkillNumber) {
                return 1;
            } else {
                return 0;
            }
        }
        else {
            return 0;
        }
    }

    public static void calculateInitiative(AdversariesList adversariesList) {

        for (AdversaryModel adversary : adversariesList.getAdversaries()) {
            if(adversary.getInitiative() <= 10) {
                adversary.setInitiative(adversary.getInitiative() + adversary.getCharacteristics().getInitiative());
            }
        }

        adversariesList.getAdversaries().sort(new InitiativeSorter());
    }
}
