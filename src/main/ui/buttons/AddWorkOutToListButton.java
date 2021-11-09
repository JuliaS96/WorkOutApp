package ui.buttons;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import ui.WorkOutAppUI;

import javax.swing.*;

public class AddWorkOutToListButton extends Button {

    public AddWorkOutToListButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Exercise Selector");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        WorkOutAppUI workOutAppUI = parent;
        String name = workOutAppUI.getExerciseName().getText();
        String description = workOutAppUI.getExerciseDesc().getText();
        AllWorkOutData data = workOutAppUI.getData();
        WorkOut workOutToAdd = new WorkOut("");
        data.getWorkouts().add(workOutToAdd);
        System.out.println("done");
        workOutAppUI.initializeGraphics(1);
        // working on this !!!! still got play exercise after to do....
    }
}
