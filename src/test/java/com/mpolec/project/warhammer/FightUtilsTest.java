package com.mpolec.project.warhammer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mpolec.project.warhammer.utils.FightUtils.calculateSuccessLevel;
import static com.mpolec.project.warhammer.utils.FightUtils.compareAttackerAndDefenderSuccessLevels;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FightUtilsTest {

    @Test
    void shouldReturnTrueForCalculationOfSuccessLevelFromSuccessRoll(){
        int skillRoll = 15;
        int skill = 40;

        int result = calculateSuccessLevel(skillRoll, skill);

        assertEquals(3, result);
    }

    @Test
    void shouldReturnTrueForCalculationOfSuccessLevelFromFailedRoll(){
        int skillRoll = 89;
        int skill = 40;

        int result = calculateSuccessLevel(skillRoll, skill);

        assertEquals(-4, result);
    }

    @Test
    void shouldReturnTrueForSuccessfullyComparedSuccessLevelsWhenAttackerWin() {
        int attackerSuccessLevel = 4;
        int defenderSuccessLevel = 2;

        int result = compareAttackerAndDefenderSuccessLevels(attackerSuccessLevel, defenderSuccessLevel);

        assertEquals(2, result);
    }

    @Test
    void shouldReturnTrueForSuccessfullyComparedSuccessLevelsWhenDefenderWin() {
        int attackerSuccessLevel = 2;
        int defenderSuccessLevel = 4;

        int result = compareAttackerAndDefenderSuccessLevels(attackerSuccessLevel, defenderSuccessLevel);

        assertEquals(-2, result);
    }
}