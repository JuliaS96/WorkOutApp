package ui.buttons;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import ui.WorkOutAppUI;

import javax.swing.*;
import java.util.ArrayList;

public class AddWorkOutToListButton extends Button {

    public AddWorkOutToListButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Exercise Selector ");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        WorkOutAppUI workOutAppUI = parent;
        AllWorkOutData data = workOutAppUI.getData();
        ArrayList<String> allExercisesNames = new ArrayList<>();
        for (Object e : data.getExercises()) {
            Exercise e1 = (Exercise) e;
            String currName = e1.getName();
            allExercisesNames.add(currName);
        }
        String[] str = new String[allExercisesNames.size()];
        JList<String> names = new JList<String>(allExercisesNames.toArray(str));
        parent.exerciseSelectorPane(names);
        WorkOut workOutToAdd = new WorkOut(parent.getWorkOutNameToAdd().getText());
        data.getWorkouts().add(workOutToAdd);


    }
}
