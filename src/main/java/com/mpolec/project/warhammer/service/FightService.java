package com.mpolec.project.warhammer.service;

import com.mpolec.project.warhammer.entity.FightEntity;

import java.util.List;

public interface FightService {
    List<FightEntity> findAll();

    void save(FightEntity fight);

    FightEntity findById(int id);
}
