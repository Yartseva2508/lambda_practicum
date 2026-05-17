package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;

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
                .filter(equipmentItem -> equipmentItem.name() == "Axe").count())).toList();
    }

    public List<Integer> filterEven (List<Integer> numbers)
    {
        return  numbers.stream().filter(x -> x % 2 == 0).toList();
    }

    public Integer filterLast (List<Integer> numbers)
    {
        return  numbers.reversed().stream().limit(1).toList().getFirst();
    }

}
