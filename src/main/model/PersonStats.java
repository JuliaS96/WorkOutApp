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

    public void addCompletedWorkout() {
        completedWorkouts = completedWorkouts + 1;
    }

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
