package ui.buttons;

import model.PersonStats;
import ui.WorkOutAppUI;
import javax.swing.*;

// button that goes to stats pane
public class SeeStatsButton extends Button {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;
    private PersonStats personStats;

    // EFFECTS: creates a new SeeStatsButton and adds it to parent
    public SeeStatsButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("See Progress");
        button = customizeButton(button);
    }

    // EFFECT: switches to stats pane
    @Override
    public void performAction(WorkOutAppUI parent) {
        workOutAppUI.initializeGraphics(0);

    }

}
