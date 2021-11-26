package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

// button that goes to add exercise pane
public class AddNewExerciseButton extends Button {

    // EFFECTS: creates a new AddNewExerciseButton and adds it to parent
    public AddNewExerciseButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Add New Exercise ");
        button = customizeButton(button);
    }

    // EFFECT: goes to add new exercise pane
    @Override
    public void performAction(WorkOutAppUI parent) {
        JTabbedPane tabbedPane = parent.getTabs();
        tabbedPane.setSelectedIndex(1);

    }


}
