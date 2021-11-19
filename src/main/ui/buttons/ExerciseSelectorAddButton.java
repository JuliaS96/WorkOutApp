package ui.buttons;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import ui.WorkOutAppUI;

import javax.swing.*;
import java.util.ArrayList;

public class ExerciseSelectorAddButton extends Button {

    // EFFECTS: creates a new ExerciseSelectorAddButton and adds it to parent
    public ExerciseSelectorAddButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Add Selected ");
        button = customizeButton(button);
    }

    // MODIFIES: parent
    // EFFECT: adds selected exercise to the current workout
    @Override
    public void performAction(WorkOutAppUI parent) {
        parent.getExerciseSelector().setVisible(true);
        Exercise currentExercise;
        WorkOutAppUI workOutAppUI = parent;
        AllWorkOutData data = workOutAppUI.getData();
        ArrayList<WorkOut> workOuts = data.getWorkouts();
        ArrayList<Exercise> exercises = data.getExercises();
        String currentWorkOutName = parent.getWorkOutNameToAdd().getText();
        JList<String> exercisesInJList = parent.getExercisesInJList();
        String currentExerciseName = (String)(exercisesInJList.getSelectedValue());

        for (Exercise e : exercises) {
            if (e.getName().equals(currentExerciseName)) {
                currentExercise = e;
                for (WorkOut w : workOuts) {
                    if (w.getWorkOutName().equals(currentWorkOutName)) {
                        w.addExercise(currentExercise);
                    }
                }
            }
        }

    }
}

