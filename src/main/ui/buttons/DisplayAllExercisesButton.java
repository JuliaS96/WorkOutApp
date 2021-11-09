package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class DisplayAllExercisesButton extends Button {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 80;


    public DisplayAllExercisesButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Display Exercise List");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        JTabbedPane tabbedPane = parent.getTabs();
        workOutAppUI.initializeGraphics(3);


    }
}
