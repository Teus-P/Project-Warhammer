package com.mpolec.project.warhammer.utils;

import com.mpolec.project.warhammer.model.AdversariesList;
import com.mpolec.project.warhammer.model.AdversaryModel;

public class FightUtils {

    public int calculateSuccessLevel(int skillRoll, int skillNumber) {
        return (skillNumber / 10) - (skillRoll / 10);
    }

    public int compareAttackerAndDefenderSuccessLevels(int attackerSuccessLevel, int defenderSuccessLevel){
        return attackerSuccessLevel - defenderSuccessLevel;
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
