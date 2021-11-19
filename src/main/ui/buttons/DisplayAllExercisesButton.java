package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class DisplayAllExercisesButton extends Button {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 80;


    // EFFECTS: creates a new DisplayAllExercisesButton and adds it to parent
    public DisplayAllExercisesButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("  Exercises Available  ");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        JTabbedPane tabbedPane = parent.getTabs();
        workOutAppUI.initializeGraphics(3);


    }
}
