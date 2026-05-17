/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mephi.vikingdemo.gui.VikingDesktopFrame;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.service.VikingService;

/**
 *
 * @author test2023
 */
@Component
public class VikingListener {
    private VikingService service;
    private VikingDesktopFrame gui;

    @Autowired
    public VikingListener(VikingService service) {
        this.service = service;
    }
    
    public void setGui(VikingDesktopFrame gui){
        this.gui = gui;
    }

    void testAdd() {
        gui.addNewViking(service.createRandomViking());
    }

    void testAdd(
            String name,
            int age,
            int heightCm,
            HairColor hairColor,
            BeardStyle beardStyle) {

        gui.addNewViking(service.createViking(name, age, heightCm, hairColor, beardStyle));
    }

    void testEdit(
            int id,
            String name,
            Integer age,
            Integer heightCm,
            HairColor hairColor,
            BeardStyle beardStyle) {

        gui.editViking(id, service.editViking(id, name, age, heightCm, hairColor, beardStyle));
    }

    void manyAdd()
    {
        gui.addNewVikings(service.createVikings());
    }

    void testDelete(int id) { gui.deleteViking(service.removeVik(id)); }
}
