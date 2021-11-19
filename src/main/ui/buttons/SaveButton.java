package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class SaveButton extends Button {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 80;

    // EFFECTS: creates a new SaveButton and adds it to parent
    public SaveButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Save ");
        button = customizeButton(button);
    }

    // MODIFIES: parent
    // EFFECT: saves current data
    @Override
    public void performAction(WorkOutAppUI parent) {
        parent.saveProgress();
        JOptionPane box = new JOptionPane();
        box.setBackground(parent.getBackgroundColor());
        box.showMessageDialog(parent,"Data saved to file.", "Notification", JOptionPane.PLAIN_MESSAGE);


    }

}
