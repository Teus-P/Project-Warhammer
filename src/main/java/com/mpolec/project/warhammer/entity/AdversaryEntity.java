package com.mpolec.project.warhammer.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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
}
