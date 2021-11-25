package ui.buttons;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import ui.WorkOutAppUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// button that initializes the workout pane with current workout
public class PlaySelectedButton extends Button {

    // EFFECTS: creates a new PlaySelectedButton and adds it to parent
    public PlaySelectedButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Play Selected ");
        button = customizeButton(button);
    }

    // MODIFIES: parent
    // EFFECT: initializes the workout pane with current workout if one is selected
    //         otherwise do nothing
    @Override
    public void performAction(WorkOutAppUI parent) {
        WorkOut currentWorkOut = new WorkOut("no workout selected");
        WorkOutAppUI workOutAppUI = parent;
        AllWorkOutData data = workOutAppUI.getData();
        ArrayList<WorkOut> availableWorkOuts = data.getWorkouts();
        JList<String> workOuts = parent.getNamesToPlay();
        String pickedWorkOut = workOuts.getSelectedValue();

        for (WorkOut w : availableWorkOuts) {
            if (w.getWorkOutName().equals(pickedWorkOut)) {
                currentWorkOut = w;
            }
        }

        if (!currentWorkOut.getWorkOutName().equals("no workout selected")) {
            parent.exercisePlayer(currentWorkOut);

        }


    }


}


