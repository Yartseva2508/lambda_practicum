package ru.mephi.vikingdemo.gui;

import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.VikingService;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class SupportFrame extends JFrame {

    private final VikingTableModel tableModel1 = new VikingTableModel();
    private final VikingTableModel tableModel2 = new VikingTableModel();
    private final VikingTableModel tableModel3 = new VikingTableModel();

    private final VikingService vikingService;

    public SupportFrame(
            VikingService vikingService
    ){
        this.vikingService = vikingService;
        this.setVisible(true);
        this.setSize(1000, 420);
        this.setLayout(new GridLayout(3, 1));

        JTable vikingTable1 = new JTable(tableModel1);
        vikingTable1.setRowHeight(28);
        add(new JScrollPane(vikingTable1));

        JTable vikingTable2 = new JTable(tableModel2);
        vikingTable2.setRowHeight(28);
        add(new JScrollPane(vikingTable2));

        JTable vikingTable3 = new JTable(tableModel3);
        vikingTable3.setRowHeight(28);
        add(new JScrollPane(vikingTable3));
    }

    public void refresh()
    {
        tableModel1.editVikings(redBeards(vikingService.findAll()));
        tableModel2.editVikings(legendary(vikingService.findAll()));
        tableModel3.editVikings(randomTallViking(vikingService.findAll()));
    }

    public List<Viking> redBeards (List<Viking> vikings)
    {
        return vikings.stream().filter(viking -> viking.beardStyle() != BeardStyle.CLEAN_SHAVEN
                && viking.hairColor() == HairColor.Red).sorted((o1, o2) -> o1.age() - o2.age()).toList();
    }

    public List<Viking> legendary (List<Viking> vikings)
    {
        return vikings.stream().filter(vik -> vik.equipment().stream()
                .anyMatch(equipmentItem -> equipmentItem.quality() == "Legendary")).toList();
    }

    public List<Viking> randomTallViking (List<Viking> vikings)
    {
        return List.of(vikings.stream().filter(viking -> viking.heightCm() > 180).findAny().get());
    }


}
