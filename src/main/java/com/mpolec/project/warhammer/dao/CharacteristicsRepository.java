package com.mpolec.project.warhammer.dao;

import com.mpolec.project.warhammer.entity.CharacteristicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicsRepository extends JpaRepository<CharacteristicsEntity, Integer> {
}
