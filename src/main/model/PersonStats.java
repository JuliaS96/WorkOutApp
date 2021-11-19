package model;

// Person stats includes a list of:
// - how many workouts a person has completed
// - how many reps

import org.json.JSONObject;
import persistence.Writeable;

public class PersonStats implements Writeable {
    private int completedWorkouts;
    private int completedReps;

    public PersonStats() {
        completedWorkouts = 0;
        completedReps = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds a workout to completed workouts
    public void addCompletedWorkout() {
        completedWorkouts = completedWorkouts + 1;
    }

    // MODIFIES: this
    // EFFECTS: adds i reps to completed reps
    public void addCompletedReps(int reps) {
        completedReps = completedReps + reps;
    }

    // EFFECTS: returns completed workouts
    public int getCompletedWorkouts() {
        return completedWorkouts;
    }

    // EFFECTS: returns completed reps
    public int getCompletedReps() {
        return completedReps;
    }

    // MODIFIES: this
    // EFFECTS: sets completed reps to i
    public void setCompletedReps(int i) {
        completedReps = i;
    }


    // MODIFIES: this
    // EFFECTS: sets completed workouts to i
    public void setCompletedWorkOuts(int i) {
        completedWorkouts = i;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Reps", completedReps);
        json.put("WorkOuts", completedWorkouts);
        return json;
    }

}
