package com.mpolec.project.warhammer.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "characteristics")
public class CharacteristicsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "weapon_skill")
    private int weaponSkill;

    @Column(name = "ballistic_skill")
    private int ballisticSkill;

    @Column(name = "strength")
    private int strength;

    @Column(name = "toughness")
    private int toughness;

    @Column(name = "initiative")
    private int initiative;

    @Column(name = "agility")
    private int agility;

    @Column(name = "dexterity")
    private int dexterity;

    @Column(name = "intelligence")
    private int intelligence;

    @Column(name = "willpower")
    private int willpower;

    @Column(name = "fellowship")
    private int fellowship;

    @Column(name = "wounds")
    private int wounds;

    @Column(name = "movement")
    private int movement;

//    @OneToOne(mappedBy = "characteristics")
//    private AdversaryEntity adversary;
}
