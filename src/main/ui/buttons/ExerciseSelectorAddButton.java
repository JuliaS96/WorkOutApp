package ui.buttons;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import ui.WorkOutAppUI;

import javax.swing.*;
import java.util.ArrayList;

public class ExerciseSelectorAddButton extends Button {

    public ExerciseSelectorAddButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Add Selected ");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        parent.getExerciseSelector().setVisible(true);
        Exercise currentExercise;
        WorkOutAppUI workOutAppUI = parent;
        AllWorkOutData data = workOutAppUI.getData();
        ArrayList<WorkOut> workOuts = data.getWorkouts();
        ArrayList<Exercise> exercises = data.getExercises();
        String currentWorkOutName = parent.getWorkOutNameToAdd().getText();
        JList<String> exercisesInJList = parent.getExercisesinJList();
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

