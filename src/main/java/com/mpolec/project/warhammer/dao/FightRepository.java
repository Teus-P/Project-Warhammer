package com.mpolec.project.warhammer.dao;

import com.mpolec.project.warhammer.entity.AdversaryEntity;
import com.mpolec.project.warhammer.entity.FightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightRepository extends JpaRepository<FightEntity, Integer> {
}
