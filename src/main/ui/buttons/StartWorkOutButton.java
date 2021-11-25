package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

// button that goes to start workout pane
public class StartWorkOutButton extends Button {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 80;

    // EFFECTS: creates a new StartWorkOutButton and adds it to parent
    public StartWorkOutButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Start WorkOut! ");
        button = customizeButton(button);
    }


    // EFFECT: switches to start workout pane
    @Override
    public void performAction(WorkOutAppUI parent) {
        workOutAppUI.initializeGraphics(5);
    }
}
