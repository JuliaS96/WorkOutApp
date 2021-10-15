package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;
import model.Exercise;


// Represents a full workout
// a Workout contains:
// - a Workout name
// - a list of exercises

public class WorkOut {
    private String workOutName;        // the name of the Workout
    private ArrayList exercises;       // a list of Exercises

    public WorkOut(String name) {
        workOutName = name;
        exercises = new ArrayList();
    }

    // REQUIRES: exerciseName is in list of Exercises
    // MODIFIES: this
    // EFFECTS: adds exercise to WorkOut
    public void addExercise(Exercise exerciseName) {
        exercises.add(exerciseName);
    }

    // REQUIRES: exerciseName is in the Workout
    // MODIFIES: this
    // EFFECTS: removes all instances of exercise from workout
    public void removeExercise(Exercise exerciseName) {
        exercises.remove(exerciseName);
    }

    public String getWorkOutName() {
        return workOutName;
    }

    public ArrayList getExercises() {
        return exercises;
    }
}
