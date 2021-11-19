package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;

public class QuitButton extends Button {

    // EFFECTS: creates a new QuitButton and adds it to parent
    public QuitButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Quit ");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        System.exit(0);


    }


}
