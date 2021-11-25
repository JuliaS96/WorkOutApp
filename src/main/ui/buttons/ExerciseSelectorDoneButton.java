package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

// button that closes pane when workout has been added and displays message
public class ExerciseSelectorDoneButton extends Button {

    // EFFECTS: creates a new ExerciseSelectorDoneButton and adds it to parent
    public ExerciseSelectorDoneButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Done ");
        button = customizeButton(button);
    }

    // MODIFIES: parent
    // EFFECT: closes pane when workout has been added and displays message
    @Override
    public void performAction(WorkOutAppUI parent) {
        parent.getExerciseSelector().setVisible(false);
        workOutAppUI.initializeGraphics(2);
        JOptionPane box = new JOptionPane();
        box.setBackground(parent.getBackgroundColor());
        box.showMessageDialog(parent,"Workout added.", "Notification", JOptionPane.PLAIN_MESSAGE);

    }


}
