package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;
import java.util.Comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class ServiceLambda {

    private final VikingService vikingService;

    public ServiceLambda(VikingService vikingService) {
        this.vikingService = vikingService;
    }

    public List<Viking> filterByAgeOld (List<Viking> vikings)
    {
        return vikings.stream().filter(vik -> vik.age() > 20).toList();
    }

    public List<Viking> filterByAgeYoung (List<Viking> vikings)
    {
        return vikings.stream().filter(vik -> vik.age() < 30).toList();
    }

    public List<Viking> filterByAgeBetween (List<Viking> vikings)
    {
        return vikings.stream().filter(vik -> vik.age() > 20 && vik.age() < 50 ).toList();
    }

    public List<Viking> filterByAgeBetweenLess (List<Viking> vikings)
    {
        return vikings.stream().filter(vik -> vik.age() < 30 || vik.age() > 50 ).toList();
    }

    public List<Viking> filterByBeardAndColor (List<Viking> vikings)
    {
        return vikings.stream().filter(vik -> vik.beardStyle() == BeardStyle.LONG &&
                vik.hairColor() == HairColor.Gray ).toList();
    }

    public List<Viking> filterByAxe (List<Viking> vikings)
    {
        return vikings.stream().filter(vik -> List.of(1L, 2L).contains(vik.equipment().stream()
                .filter(equipmentItem -> "Axe".equals(equipmentItem.name())).count())).toList();
    }

    public Integer findMaxId(Integer[] ids) {
        return Arrays.stream(ids).max(Comparator.naturalOrder()).orElse(null);
    }

    public List<Integer> findAllEvenIds(Integer[] ids) {
        return Arrays.stream(ids).filter(id -> id % 2 == 0).toList();
    }

    public List<Viking> redBeards (List<Viking> vikings)
    {
        return vikings.stream().filter(viking -> viking.beardStyle() != BeardStyle.CLEAN_SHAVEN
                && viking.hairColor() == HairColor.Red).sorted((o1, o2) -> o1.age() - o2.age()).toList();
    }

    public List<Viking> legendary (List<Viking> vikings)
    {
        return vikings.stream().filter(vik -> vik.equipment().stream()
                .anyMatch(equipmentItem -> "Legendary".equals(equipmentItem.quality()))).toList();
    }

    public List<Viking> randomTallViking (List<Viking> vikings) {
        return vikings.stream().filter(viking -> viking.heightCm() > 180).findAny()
                .map(List::of).orElse(Collections.emptyList());
    }
}