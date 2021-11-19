package ui.buttons;

import ui.WorkOutAppUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// Code based from SimpleDrawingEditor Tool class
// Buttons class sets up behaviour for adding buttons
public abstract class Button {
    protected JButton button;
    protected WorkOutAppUI workOutAppUI;
    private boolean active;

    public Button(WorkOutAppUI workOutAppUI, JComponent parent) {
        this.workOutAppUI = workOutAppUI;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
        button.setBorder(new LineBorder(workOutAppUI.getLightColor()));
        button.setForeground(workOutAppUI.getLightColor());
        button.setFont(new Font("Arial", Font.BOLD,16));

    }

    // Code based from SimpleDrawingEditor Tool class
    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // getters
    public boolean isActive() {
        return active;
    }

    // EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected void addListener() {
        button.addActionListener(new ButtonClickHandler());
    }

    public abstract void performAction(WorkOutAppUI parent);


    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: performs action when mouse is clicked
    public void mouseClicked(MouseEvent e, WorkOutAppUI parent) {
        performAction(parent);
    }

    private class ButtonClickHandler implements ActionListener {

        // EFFECTS: sets active button
        //          called by the framework when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            workOutAppUI.setActiveButton(Button.this);

        }



    }

}
