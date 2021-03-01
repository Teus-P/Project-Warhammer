package com.mpolec.project.warhammer.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "fight")
public class FightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fight_name")
    private String fightName;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "adversary_fight",
            joinColumns = { @JoinColumn(name = "fight_id") } ,
            inverseJoinColumns = { @JoinColumn(name = "adversary_id") }
    )
    Collection<AdversaryEntity> adversaries;
}
