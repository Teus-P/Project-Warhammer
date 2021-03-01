package com.mpolec.project.warhammer.dao;

import com.mpolec.project.warhammer.entity.AdversaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdversaryRepository extends JpaRepository<AdversaryEntity, Integer> {
}
