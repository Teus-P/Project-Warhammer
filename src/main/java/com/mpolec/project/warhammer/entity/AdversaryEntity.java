package com.mpolec.project.warhammer.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "adversary")
public class AdversaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "characteristics_id", referencedColumnName = "id")
    private CharacteristicsEntity characteristics;

//    @ToString.Exclude
//    @ManyToMany(mappedBy = "adversaries")
//    private Collection<FightEntity> fights;
}
