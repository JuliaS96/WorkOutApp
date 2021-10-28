package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

// Represents a full workout
// a Workout contains:
// - a Workout name
// - a list of exercises

public class WorkOut implements Writeable {
    private String workOutName;        // the name of the Workout
    private ArrayList<Exercise> exercises;       // a list of Exercises

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

//    // REQUIRES: exerciseName is in the Workout
//    // MODIFIES: this
//    // EFFECTS: removes all instances of exercise from workout
//    public void removeExercise(Exercise exerciseName) {
//        exercises.remove(exerciseName);
//    }
//

    // EFFECTS: returns workout name
    public String getWorkOutName() {
        return workOutName;
    }

    // EFFECTS: returns workout exercises
    public ArrayList getExercises() {
        return exercises;
    }

    @Override
     public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        json.put("name", workOutName);
        for (Exercise e : exercises) {
            jsonArray.put(e.toJson());
        }
        json.put("exercises", jsonArray);
        return json;
    }
}
