package ui.buttons;

import model.Exercise;
import model.PersonStats;
import model.WorkOut;
import ui.WorkOutAppUI;

import javax.swing.*;

public class NextButton extends Button {

    public NextButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Start!");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        int exercisesDone = parent.getExercisesDone();
        int setsDone = parent.getSetsDone();
        int setsTotal = parent.getSetsTotal();
        PersonStats ps = parent.getData().getPersonStats();

        parent.getExercisePlayer().setVisible(true);
        WorkOut currentWorkout = parent.getCurrentWorkout();
        int exercisesTotal = currentWorkout.getExercises().size();
        Exercise currentExercise = (Exercise) currentWorkout.getExercises().get(0);
        parent.resetSetsTotal(currentExercise.getSets());
        JPanel currentPanel;
        parent.getPlayerPanel().removeAll();

        if (exercisesTotal == exercisesDone) {
            parent.resetSetsDone();
            parent.resetExercisesDone();
            parent.getExercisePlayer().setVisible(false);
            JOptionPane box = new JOptionPane();
            box.showMessageDialog(parent, "Great job completing this workout!",
                    "Notification", JOptionPane.PLAIN_MESSAGE);
            ps.addCompletedWorkout();
        } else if (setsTotal - 1 > parent.getSetsDone()) {
            currentExercise = (Exercise) currentWorkout.getExercises().get(exercisesDone);
            parent.resetSetsTotal(currentExercise.getSets());
            currentPanel = parent.exerciseHelper(currentExercise, setsDone);
            parent.increaseSetsDone();
            parent.getPlayerPanel().add(currentPanel, 0);
            ps.addCompletedReps(currentExercise.getReps());
        } else {
            parent.resetSetsDone();
            currentExercise = (Exercise) currentWorkout.getExercises().get(exercisesDone);
            parent.resetSetsTotal(currentExercise.getSets());
            parent.increaseExercisesDone();
            setsTotal = currentExercise.getSets();
            currentPanel = parent.exerciseHelper(currentExercise, setsDone);
            parent.getPlayerPanel().add(currentPanel, 0);
        }

        parent.getExercisePlayer().pack();
        System.out.println(currentExercise.getName() + exercisesDone + " "
                + exercisesTotal + " " + " " + setsDone + " " + setsTotal);
    }

}


