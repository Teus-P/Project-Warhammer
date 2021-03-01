package com.mpolec.project.warhammer.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AdversariesList {
    private List<AdversaryModel> adversaries;

    public AdversariesList() {
        this.adversaries = new ArrayList<>();
    }

    public void addAdversary(AdversaryModel adversary) {
        this.adversaries.add(adversary);
    }
}
