package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class ExerciseSelectorAddButton extends Button {

    public ExerciseSelectorAddButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Selected");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {

    }


}
