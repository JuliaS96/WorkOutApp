package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class LoadButton extends Button {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 80;

    public LoadButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Load ");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        parent.loadProgress();
        JOptionPane box = new JOptionPane();
        box.setBackground(parent.getBackgroundColor());
        box.showMessageDialog(parent,"Data loaded from file.", "Notification", JOptionPane.PLAIN_MESSAGE);
        workOutAppUI.initializeGraphics(0);

    }


}
