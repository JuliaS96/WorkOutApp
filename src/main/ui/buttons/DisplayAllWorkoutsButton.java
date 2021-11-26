package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

// button that goes to list of workouts pane
public class DisplayAllWorkoutsButton extends Button {


    // EFFECTS: creates a new DisplayAllWorkoutsButton and adds it to parent
    public DisplayAllWorkoutsButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("  Workouts Available  ");
        button = customizeButton(button);
    }


    // EFFECT: goes to list of workouts pane
    @Override
    public void performAction(WorkOutAppUI parent) {
        JTabbedPane tabbedPane = parent.getTabs();
        workOutAppUI.initializeGraphics(4);
    }
}
