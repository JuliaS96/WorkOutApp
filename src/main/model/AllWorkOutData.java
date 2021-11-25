package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

// Represents all WorkOut App Data available
// WorkOut Data Contains:
// - List of Exercises
// - List of Workouts
// - PersonStats
public class AllWorkOutData implements Writeable {
    private ArrayList<WorkOut> allWorkouts;
    private ArrayList<Exercise> allExercises;
    private PersonStats personStats;

    public AllWorkOutData() {
        this.personStats = new PersonStats();
        this.allExercises = new ArrayList<>();
        this.allWorkouts = new ArrayList<>();
    }

    public ArrayList getWorkouts() {
        return allWorkouts;
    }

    public ArrayList getExercises() {
        return allExercises;
    }

    public PersonStats getPersonStats() {
        return personStats;
    }

    // REQUIRES: r, w > 0
    // MODIFIES: this
    // EFFECTS: updates personal stats
    public void updatePS(int r,  int w) {
        personStats.setCompletedWorkOuts(w);
        personStats.setCompletedReps(r);
    }

    // MODIFIES: this
    // EFFECTS: updates workout list
    public void updateWO(ArrayList<WorkOut> workOutList) {
        allWorkouts = workOutList;
    }

    // MODIFIES: this
    // EFFECTS: updates workout list
    public void updateWO(WorkOut workOut) {
        allWorkouts.add(workOut);
        EventLog.getInstance().logEvent(new Event("A workout named "
                + workOut.getWorkOutName() + " was added to your workout list."));
    }


    // MODIFIES: this
    // EFFECTS: updates workout list
    public void updateEx(ArrayList<Exercise> exerciseList) {
        allExercises = exerciseList;
    }

    // MODIFIES: this
    // EFFECTS: updates exercise list
    public void updateEx(Exercise exercise) {
        allExercises.add(exercise);
        EventLog.getInstance().logEvent(new Event("An exercise named "
                + exercise.getName() + " was added to your exercise list."));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Personal Stats", personStatsToJson());
        json.put("WorkOuts", workoutsToJson());
        json.put("Exercises", exercisesToJson());
        return json;
    }

    // EFFECTS: returns person stats in the workout data as a JSON array
    private JSONArray personStatsToJson() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(personStats.toJson());
        return jsonArray;
    }


    // EFFECTS: returns Workouts in the workout data as a JSON array
    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (WorkOut t : allWorkouts) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns Exercises in the workout data as a JSON array
    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise t : allExercises) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

}
