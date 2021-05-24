package com.mpolec.project.warhammer.model;

import com.mpolec.project.warhammer.entity.AdversaryEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
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

    public AdversaryModel getAdversaryById(Integer id){
        return adversaries.stream().filter(adversary -> id.equals(adversary.getId())).findFirst().orElse(null);
    }

    public void updateAdversaryInAdversariesList(AdversaryModel adversaryModel) {
        getAdversaryById(adversaryModel.getId()).getCharacteristics().setWounds(adversaryModel.getCharacteristics().getWounds());
    }

    public static AdversariesList createAdversariesList(ArrayList<AdversaryEntity> adversariesEntity){
        AdversariesList adversariesList = new AdversariesList();

        int i = 0;
        HashMap<String, Integer> nameMap = new HashMap<>();

        for (AdversaryEntity adversaryEntity : adversariesEntity) {
            AdversaryModel adversary = new AdversaryModel();
            adversary.setId(i);
            adversary.setCharacteristics(adversaryEntity.getCharacteristics());
            adversary.setInitiative(0);
            setAdversaryName(nameMap, adversaryEntity, adversary);
            adversariesList.addAdversary(adversary);
            i++;
        }

        checkAdversariesNames(adversariesList, nameMap);

        return adversariesList;
    }

    private static void checkAdversariesNames(AdversariesList adversariesList,
                                              HashMap<String, Integer> nameMap) {
        nameMap.forEach((name, counter) -> {
            if (counter > 1) {
                adversariesList.getAdversaries().forEach(adversaryModel -> {
                    if(adversaryModel.getName().equals(name)){
                        adversaryModel.setName(name + " 1");
                    }
                });
            }
        });
    }

    private static void setAdversaryName(HashMap<String, Integer> nameMap,
                                         AdversaryEntity adversaryEntity,
                                         AdversaryModel adversary) {
        String name = adversaryEntity.getName();
        if(nameMap.containsKey(name)) {
            nameMap.put(name, nameMap.get(name) + 1);
            adversary.setName(adversaryEntity.getName() + " " + nameMap.get(name));
        }
        else {
            nameMap.put(name, 1);
            adversary.setName(adversaryEntity.getName());
        }
    }
}
