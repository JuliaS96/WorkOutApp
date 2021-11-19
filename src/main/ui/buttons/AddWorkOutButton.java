package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class AddWorkOutButton extends Button {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 80;

    // EFFECTS: creates a new AddWorkOutButton and adds it to parent
    public AddWorkOutButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Add a WorkOut ");
        button = customizeButton(button);
    }


    // EFFECT: goes to add new workout pane
    @Override
    public void performAction(WorkOutAppUI parent) {
        JTabbedPane tabbedPane = parent.getTabs();
        tabbedPane.setSelectedIndex(2);
    }
}
