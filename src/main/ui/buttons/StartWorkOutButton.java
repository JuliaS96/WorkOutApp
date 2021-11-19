package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class StartWorkOutButton extends Button {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 80;


    public StartWorkOutButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Start WorkOut!");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        workOutAppUI.initializeGraphics(5);
    }
}
