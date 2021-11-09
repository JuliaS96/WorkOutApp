package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class ExerciseSelectorDoneButton extends Button {

    public ExerciseSelectorDoneButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }


    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Done");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {

    }


}
