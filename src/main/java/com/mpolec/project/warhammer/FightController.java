package com.mpolec.project.warhammer;

public class FightController {

    public int calculateSuccessLevel(int skillRoll, int skillNumber) {
        return (skillNumber / 10) - (skillRoll / 10);
    }

    public int compareAttackerAndDefenderSuccessLevels(int attackerSuccessLevel, int defenderSuccessLevel){
        return attackerSuccessLevel - defenderSuccessLevel;
    }
}
