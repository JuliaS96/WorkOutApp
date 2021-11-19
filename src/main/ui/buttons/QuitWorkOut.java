package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class QuitWorkOut extends Button {

    public QuitWorkOut(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Quit ");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        parent.getExercisePlayer().setVisible(false);
        workOutAppUI.initializeGraphics(1);
        JOptionPane box = new JOptionPane();
        box.setBackground(parent.getBackgroundColor());
        box.showMessageDialog(parent, "Better luck next time!", "Notification",
                JOptionPane.INFORMATION_MESSAGE); // could add image here


    }


}
