package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

// button that goes to add new workout pane
public class AddWorkOutButton extends Button {

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
