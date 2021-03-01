package com.mpolec.project.warhammer.utils;

import com.mpolec.project.warhammer.model.AdversaryModel;

import java.util.Comparator;

public class InitiativeSorter implements Comparator<AdversaryModel> {

    @Override
    public int compare(AdversaryModel o1, AdversaryModel o2) {
        return o2.getInitiative().compareTo(o1.getInitiative());
    }
}
