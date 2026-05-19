package ru.mephi.vikingdemo.gui;

import ru.mephi.vikingdemo.service.ServiceLambda;
import ru.mephi.vikingdemo.service.VikingService;

import javax.swing.*;
import java.awt.*;

public class SupportFrame extends JFrame {

    private final VikingTableModel tableModel1 = new VikingTableModel();
    private final VikingTableModel tableModel2 = new VikingTableModel();
    private final VikingTableModel tableModel3 = new VikingTableModel();

    private final VikingService vikingService;
    private final ServiceLambda serviceLambda;

    public SupportFrame(
            VikingService vikingService,
            ServiceLambda serviceLambda
    ){
        this.vikingService = vikingService;
        this.serviceLambda = serviceLambda;
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

    public void refresh() {
        tableModel1.editVikings(serviceLambda.randomTallViking(vikingService.findAll()));
        tableModel2.editVikings(serviceLambda.legendary(vikingService.findAll()));
        tableModel3.editVikings(serviceLambda.redBeards(vikingService.findAll()));
    }
}