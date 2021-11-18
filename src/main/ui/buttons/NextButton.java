package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class NextButton extends Button {

    public NextButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Next");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        parent.getExercisePlayer().setVisible(false);
        workOutAppUI.initializeGraphics(1);
        JOptionPane box = new JOptionPane();
        box.showMessageDialog(parent, "Better luck next time!", "Notification", JOptionPane.PLAIN_MESSAGE);


    }

}
