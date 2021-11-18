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
        parent.getExerciseSelector().setVisible(false);
        workOutAppUI.initializeGraphics(2);
        JOptionPane box = new JOptionPane();
        box.showMessageDialog(parent,"Workout added.", "Notification", JOptionPane.PLAIN_MESSAGE);

    }


}
