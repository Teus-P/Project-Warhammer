package com.mpolec.project.warhammer.service;

import com.mpolec.project.warhammer.entity.AdversaryEntity;

import java.util.List;

public interface AdversaryService {
    List<AdversaryEntity> findAll();

    void save(AdversaryEntity character);

    AdversaryEntity findById(int id);
}
