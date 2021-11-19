package ui.buttons;

import model.AllWorkOutData;
import model.Exercise;
import ui.WorkOutAppUI;

import javax.swing.*;

public class AddExerciseButton extends Button {



    public AddExerciseButton(WorkOutAppUI workOutAppUI, JComponent parent) {
        super(workOutAppUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Exercise");
        button = customizeButton(button);
    }

    @Override
    public void performAction(WorkOutAppUI parent) {
        WorkOutAppUI workOutAppUI = parent;
        String name = workOutAppUI.getExerciseName().getText();
        String description = workOutAppUI.getExerciseDesc().getText();
        int reps = (int) workOutAppUI.getModelReps().getValue();
        int sets = (int) workOutAppUI.getModelSets().getValue();
        Exercise exercise = new Exercise(name, description, reps, sets);
        AllWorkOutData data = workOutAppUI.getData();
        data.getExercises().add(exercise);
        System.out.println("done");
        JOptionPane box = new JOptionPane();
        box.setBackground(parent.getBackgroundColor());
        box.showMessageDialog(parent,"Exercise added.", "Notification", JOptionPane.PLAIN_MESSAGE);
        workOutAppUI.initializeGraphics(1);

    }

}
