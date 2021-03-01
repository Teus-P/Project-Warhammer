package com.mpolec.project.warhammer.model;

import com.mpolec.project.warhammer.entity.CharacteristicsEntity;
import lombok.Data;

@Data
public class AdversaryModel {

    private int id;
    private String name;
    private CharacteristicsEntity characteristics;
    private Integer initiative;

}
