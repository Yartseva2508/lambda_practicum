package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class VikingService {
    // каждый раз при изменении создаётся новая копия списка 
    private final CopyOnWriteArrayList<Viking> vikings = new CopyOnWriteArrayList<>();
    private final VikingFactory vikingFactory;
    @Autowired
    public VikingService(VikingFactory vikingFactory) {
        this.vikingFactory = vikingFactory;
    }
    
    public List<Viking> findAll() {
        return List.copyOf(vikings);
    }

    public int removeVik(int id) {
        vikings.remove(id);
        return id;
    }

    public Viking createViking(
            String name,
            int age,
            int heightCm,
            HairColor hairColor,
            BeardStyle beardStyle) {

        Viking viking = vikingFactory.createViking(name, age, heightCm, hairColor, beardStyle);

        vikings.add(viking);
        return viking;
    }

    public Viking editViking(
            int id,
            String name,
            Integer age,
            Integer heightCm,
            HairColor hairColor,
            BeardStyle beardStyle) {


        Viking viking = vikingFactory.createViking(
                name == null ? vikings.get(id).name() : name,
                age == null ? vikings.get(id).age() : age,
                heightCm == null ? vikings.get(id).heightCm() : heightCm,
                hairColor == null ? vikings.get(id).hairColor() : hairColor,
                beardStyle == null ? vikings.get(id).beardStyle() : beardStyle,
                vikings.get(id).equipment());

        vikings.set(id, viking);
        return viking;
    }

    public Viking createRandomViking() {

        Viking viking = vikingFactory.createRandomViking();

        vikings.add(viking);
        return viking;
    }

    public List<Viking> createVikings(){

        List<Viking> viks = new ArrayList<>();

        IntStream.range(0, 10).forEach(x -> {
            viks.add(vikingFactory.createRandomViking());
            vikings.add(viks.getLast());
        });

        return vikings;
    }
}
