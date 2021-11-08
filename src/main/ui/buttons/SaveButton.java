package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class SaveButton extends Button {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 80;


    public SaveButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        parent.saveProgress();
        JOptionPane box = new JOptionPane();
        box.showMessageDialog(parent,"Data saved to file.", "Notification", JOptionPane.PLAIN_MESSAGE);

    }

}
