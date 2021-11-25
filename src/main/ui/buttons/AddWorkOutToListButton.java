package ui.buttons;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import ui.WorkOutAppUI;

import javax.swing.*;
import java.util.ArrayList;

// button that adds workout to the list of workouts
public class AddWorkOutToListButton extends Button {

    // EFFECTS: creates a new AddWorkOutToListButton and adds it to parent
    public AddWorkOutToListButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Exercise Selector ");
        button = customizeButton(button);
    }

    // MODIFIES: parent
    // EFFECT: adds workout to the list of workouts if the name is not blank
    //        otherwise do nothing
    @Override
    public void performAction(WorkOutAppUI parent) {
        WorkOutAppUI workOutAppUI = parent;
        String name = parent.getWorkOutNameToAdd().getText();
        if (!name.equals("")) {
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
            WorkOut workOutToAdd = new WorkOut(name);
            data.getWorkouts().add(workOutToAdd);
        }

    }
}
