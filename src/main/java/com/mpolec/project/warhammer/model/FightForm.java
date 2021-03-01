package com.mpolec.project.warhammer.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class FightForm {

    private int id;
    private String fightName;
    private ArrayList<Integer> adversariesId;

}
