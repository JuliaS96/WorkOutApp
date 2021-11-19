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
        int exercisesTotal = parent.getExercisesTotal();
        int setsDone = parent.getSetsDone();
        int setsTotal = parent.getSetsTotal();
        PersonStats ps = parent.getData().getPersonStats();

        parent.getExercisePlayer().setVisible(true);
        WorkOut currentWorkout = parent.getCurrentWorkout();
        Exercise currentExercise = (Exercise) currentWorkout.getExercises().get(0);
        JPanel currentPanel;
        parent.getPlayerPanel().removeAll();

        if (setsTotal - 1 > parent.getSetsDone()) {
            currentExercise = (Exercise) currentWorkout.getExercises().get(exercisesDone);
            currentPanel = parent.exerciseHelper(currentExercise, setsDone);
            parent.increaseSetsDone();
            parent.getPlayerPanel().add(currentPanel,0);
            ps.addCompletedReps(currentExercise.getReps());
        } else if (exercisesTotal - 1 > exercisesDone) {
            parent.resetSetsDone();
            parent.increaseExercisesDone();
            currentExercise = (Exercise) currentWorkout.getExercises().get(exercisesDone);
            currentPanel = parent.exerciseHelper(currentExercise, setsDone);
            parent.getPlayerPanel().add(currentPanel,0);
        } else {
            parent.getExercisePlayer().setVisible(false);
            JOptionPane box = new JOptionPane();
            box.showMessageDialog(parent, "Great job completing this workout!",
                    "Notification", JOptionPane.PLAIN_MESSAGE);
            parent.resetSetsDone();
            parent.resetExercisesDone();
            ps.addCompletedWorkout();
        }

        parent.getPlayerPanel().repaint();
        System.out.println(currentExercise.getName() + exercisesDone + " "
                 + exercisesTotal + " " + " " + setsDone + " " + setsTotal);
    }

}


