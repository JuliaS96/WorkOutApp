package model;
// Represents a single exercise
// an exercise contains:
// - a name
// - description
// - how many repetitions and sets

public class Exercise {
    private String exerciseName;       // the name of the exercise
    private String description;        // short exercise description
    private int reps;                  // number of reps to perform for the exercise
    private int sets;                  // number of sets to perform for the exercise

    // REQUIRES: name and description have non-zero length AND
    // reps and sets are positive integers
    // EFFECTS: name of exercise is set to name
    //          description of exercise is set to description
    //          number of reps are set to reps
    //          number of sets are set to sets
    public Exercise(String name, String description, int reps, int sets) {
        exerciseName = name;
        this.description = description;
        this.reps = reps;
        this.sets = sets;
    }

    // REQUIRES: reps >= 0
    // MODIFIES: this
    // EFFECTS: one rep is removed from the remaining reps
    public int performRep() {
        if (reps > 0) {
            reps = reps - 1;
        }
        return reps;
    }

    // REQUIRES: sets >= 0
    // MODIFIES: this
    // EFFECTS: one set is removed from the remaining sets
    public int performSet() {
        if (sets > 0) {
            sets = sets - 1;
        }
        return sets;
    }

    public String getName() {
        return exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

}
