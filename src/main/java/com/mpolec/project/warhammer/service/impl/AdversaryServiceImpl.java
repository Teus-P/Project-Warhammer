package com.mpolec.project.warhammer.service.impl;

import com.mpolec.project.warhammer.dao.AdversaryRepository;
import com.mpolec.project.warhammer.entity.AdversaryEntity;
import com.mpolec.project.warhammer.service.AdversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdversaryServiceImpl implements AdversaryService {

    private final AdversaryRepository adversaryRepository;

    @Autowired
    public AdversaryServiceImpl(AdversaryRepository adversaryRepository) {
        this.adversaryRepository = adversaryRepository;
    }

    @Override
    public List<AdversaryEntity> findAll() {
        return adversaryRepository.findAll();
    }

    @Override
    public void save(AdversaryEntity character) {
        adversaryRepository.save(character);
    }

    @Override
    public AdversaryEntity findById(int id) {
        Optional<AdversaryEntity> result = adversaryRepository.findById(id);

        AdversaryEntity adversary = null;

        if(result.isPresent()){
            adversary = result.get();
        }
        else {
            throw new RuntimeException("Did not find adversary id - " + id);
        }

        return adversary;
    }
}
