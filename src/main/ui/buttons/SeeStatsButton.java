package ui.buttons;

import model.PersonStats;
import ui.WorkOutAppUI;
import javax.swing.*;

public class SeeStatsButton extends Button {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;
    private PersonStats personStats;

    public SeeStatsButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("See Your Progress");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        JTabbedPane tabbedPane = parent.getTabs();
        tabbedPane.setSelectedIndex(0);

    }

}
