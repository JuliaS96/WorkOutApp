package model;

// Person stats includes a list of:
// - how many workouts a person has completed
// - how many reps

public class PersonStats {
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
    // EFFECTS: adds a rep to completed reps
    public void addCompletedReps() {
        completedReps = completedReps + 1;
    }

    public int getCompletedWorkouts() {
        return completedWorkouts;
    }

    public int getCompletedReps() {
        return completedReps;
    }
}
