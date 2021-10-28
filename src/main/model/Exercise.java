package model;

import org.json.JSONObject;
import persistence.Writeable;

// Represents a single exercise
// an exercise contains:
// - a name
// - description
// - number of repetitions and
// - number of sets
public class Exercise implements Writeable {
    private String exerciseName;       // the name of the exercise
    private String description;        // short exercise description
    private int reps;                  // number of reps to perform for the exercise
    private int sets;                  // number of sets to perform for the exercise

    // REQUIRES: name and description have non-zero length AND
    // reps and sets are positive integers
    // EFFECTS: name of exercise is set to name
    //        description of exercise is set to description
    //        number of reps are set to reps
    public Exercise(String name, String description, int reps, int sets) {
        exerciseName = name;
        this.description = description;
        this.reps = reps;
        this.sets = sets;
    }

    // REQUIRES: positive numbers of sets and reps
    // EFFECTS: calculated the difficulty of the exercise
    public String exerciseDifficulty() {
        int calc = this.reps * this.sets;
        String output;
        if (calc < 15) {
            output = "Easy";
        } else if (calc < 30) {
            output = "Moderate";
        } else {
            output = "Difficult";
        }
        return output;
    }

    // EFFECTS: returns exercise name
    public String getName() {
        return exerciseName;
    }

    // EFFECTS: returns exercise description
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns exercise reps
    public int getReps() {
        return reps;
    }

    // EFFECTS: returns exercise sets
    public int getSets() {
        return sets;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", exerciseName);
        json.put("description", description);
        json.put("reps", reps);
        json.put("sets", sets);
        return json;
    }

}
