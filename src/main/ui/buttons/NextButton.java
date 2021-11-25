package ui.buttons;

import model.Exercise;
import model.PersonStats;
import model.WorkOut;
import ui.WorkOutAppUI;

import javax.swing.*;
import java.awt.*;

// button that goes through workout
public class NextButton extends Button {

    // EFFECTS: creates a new NextButton and adds it to parent
    public NextButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a new custom button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(" Start! ");
        button = customizeButton(button);
    }

    // MODIFIES: parent
    // EFFECT: starts playing workout;
    //         adds reps to reps completed and adds workout when done
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
        parent.getPlayerPanel().removeAll();

        if (exercisesTotal == exercisesDone) {
            closeWindowAndReset(parent, ps);
        } else if (setsTotal - 1 > parent.getSetsDone()) {
            currentExercise = (Exercise) currentWorkout.getExercises().get(exercisesDone);
            playNextSet(parent, setsDone, ps, currentExercise);
        } else {

            parent.resetSetsDone();
            currentExercise = (Exercise) currentWorkout.getExercises().get(exercisesDone);
            playNextExerciseSet(parent, setsDone, currentExercise);
        }

        parent.getExercisePlayer().setMinimumSize(parent.getExercisePlayer().getSize());
        parent.getExercisePlayer().pack();

    }

    // MODIFIES: parent
    // EFFECT: adds reps to reps completed and moves on to next exercise
    private int playNextExerciseSet(WorkOutAppUI parent, int setsDone, Exercise currentExercise) {
        JPanel currentPanel;
        int setsTotal;
        parent.resetSetsTotal(currentExercise.getSets());
        parent.increaseExercisesDone();
        setsTotal = currentExercise.getSets();
        currentPanel = parent.exerciseHelper(currentExercise, setsDone);
        parent.getPlayerPanel().add(currentPanel);
        parent.getData().getPersonStats().addCompletedReps(currentExercise.getReps());
        return setsTotal;
    }

    // MODIFIES: parent
    // EFFECT: adds reps to reps completed and moves on to next set
    private void playNextSet(WorkOutAppUI parent, int setsDone, PersonStats ps, Exercise currentExercise) {
        JPanel currentPanel;
        parent.resetSetsTotal(currentExercise.getSets());
        currentPanel = parent.exerciseHelper(currentExercise, setsDone);
        parent.increaseSetsDone();
        parent.getPlayerPanel().add(currentPanel);
        ps.addCompletedReps(currentExercise.getReps());
    }

    // MODIFIES: parent
    // EFFECT: closes the window if the workout has been completed and adds a workout
    //         to completed workouts
    private void closeWindowAndReset(WorkOutAppUI parent, PersonStats ps) {
        parent.resetSetsDone();
        parent.resetExercisesDone();
        parent.getExercisePlayer().setVisible(false);
        JOptionPane box = new JOptionPane();
        box.setBackground(new Color(205,205,180));
        box.showMessageDialog(parent, "Great job completing this workout!",
                "Notification", JOptionPane.PLAIN_MESSAGE);
        ps.addCompletedWorkout();
        parent.setVisibility(50);
    }

}


