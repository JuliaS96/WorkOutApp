package ui.buttons;

import model.EventLog;
import ui.LogPrinter;
import ui.WorkOutAppUI;

import javax.swing.*;

// button that quits app
public class QuitButton extends Button {

    // EFFECTS: creates a new QuitButton and adds it to parent
    public QuitButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Quit ");
        button = customizeButton(button);
    }


    // EFFECT: quits app
    @Override
    public void performAction(WorkOutAppUI parent) {
        LogPrinter lp = new LogPrinter();
        lp.printLog(EventLog.getInstance());
        System.exit(0);


    }


}
