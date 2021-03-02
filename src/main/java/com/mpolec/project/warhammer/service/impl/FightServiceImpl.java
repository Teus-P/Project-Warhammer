package com.mpolec.project.warhammer.service.impl;

import com.mpolec.project.warhammer.dao.FightRepository;
import com.mpolec.project.warhammer.entity.FightEntity;
import com.mpolec.project.warhammer.service.FightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FightServiceImpl implements FightService {

    private final FightRepository fightRepository;

    @Autowired
    public FightServiceImpl(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }

    @Override
    public List<FightEntity> findAll() {
        return fightRepository.findAll();
    }

    @Override
    public void save(FightEntity fight) {
        fightRepository.save(fight);
    }

    @Override
    public FightEntity findById(int id) {
        Optional<FightEntity> result = fightRepository.findById(id);

        FightEntity fight = null;

        if(result.isPresent()){
            fight = result.get();
        }
        else {
            throw new RuntimeException("Did not find fight id - " + id);
        }

        return fight;
    }

}
