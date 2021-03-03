package com.mpolec.project.warhammer.model;

import lombok.Data;

import static com.mpolec.project.warhammer.utils.FightUtils.calculateAttack;

@Data
public class AttackModel {

    private int attackerId;
    private String attackerName;
    private int defenderId;
    private String defenderName;
    private int attackerRoll;
    private int defenderRoll;
    private String result;
    private int damage;
    private int calculatedDamage;

    public static AttackModel prepareAttackModel(int attackerId, AdversariesList adversariesList){
        AttackModel attackModel = new AttackModel();
        attackModel.setAttackerId(attackerId);
        attackModel.setAttackerName(adversariesList.getAdversaryById(attackerId).getName());
        return attackModel;
    }

    public void updateAttackModel( AdversariesList adversariesList){
        AdversaryModel attacker = adversariesList.getAdversaryById(getAttackerId());
        AdversaryModel defender = adversariesList.getAdversaryById(getDefenderId());

        int damage = calculateAttack(attacker, defender, this);

        setAttackerName(attacker.getName());
        setDefenderName(defender.getName());
        setDamage(damage);

        calculateResultOfFight(adversariesList, defender, damage);
    }

    private void calculateResultOfFight(AdversariesList adversariesList, AdversaryModel defender, int damage) {
        if(damage > 0) {
            setResult("Wygrywa atakujący");

            calculateDamage(adversariesList, defender, damage);
        }
        else {
            setResult("Wygrywa obrońca");
        }
    }

    private void calculateDamage(AdversariesList adversariesList, AdversaryModel defender, int damage) {
        int defenderWounds = defender.getCharacteristics().getWounds();
        int defenderToughnessBonus = defender.getCharacteristics().getToughness() / 10;

        setCalculatedDamage(damage - defenderToughnessBonus);

        if(getCalculatedDamage() <= 0) {
            setCalculatedDamage(1);
        }

        defender.getCharacteristics().setWounds(defenderWounds - getCalculatedDamage());
        adversariesList.updateAdversaryInAdversariesList(defender);
    }
}
