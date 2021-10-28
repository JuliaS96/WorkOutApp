package persistence;

import model.AllWorkOutData;
import model.Exercise;
import model.PersonStats;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.WorkOut;
import org.json.*;

// !!!! Represents a reader that reads workout app data from JSON data stored in file
// Used JsonReader from JSONSerializationDemo to model program
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AllWorkOutData read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAllWorkOutData(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses all workout data from JSON object and returns it
    private AllWorkOutData parseAllWorkOutData(JSONObject jsonObject) {
        AllWorkOutData data = new AllWorkOutData();
        addWorkOutData(data, jsonObject);
        return data;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addWorkOutData(AllWorkOutData data, JSONObject jsonObject) {
        JSONArray jsonArrayPS = jsonObject.getJSONArray("Personal Stats");
        JSONArray jsonArrayEx = jsonObject.getJSONArray("Exercises");
        JSONArray jsonArrayWO = jsonObject.getJSONArray("WorkOuts");
        ArrayList<WorkOut> workOuts = new ArrayList<>();
        ArrayList<Exercise> exercises = new ArrayList<>();
        for (Object json : jsonArrayPS) {
            JSONObject nextPS = (JSONObject) json;
            addPersonStats(data, nextPS);
        }
        for (Object json : jsonArrayWO) {
            JSONObject nextWO = (JSONObject) json;
            WorkOut workout = makeWorkout(nextWO);
            workOuts.add(workout);
        }
        for (Object json : jsonArrayEx) {
            JSONObject nextExercise = (JSONObject) json;
            Exercise exercise = makeExercise(nextExercise);
            exercises.add(exercise);
        }
        data.updateEx(exercises);
        data.updateWO(workOuts);
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addPersonStats(AllWorkOutData data, JSONObject jsonObject) {
        int reps = jsonObject.getInt("Reps");
        int workOuts = jsonObject.getInt("WorkOuts");
        data.updatePS(reps, workOuts);
    }

    private WorkOut makeWorkout(JSONObject jsonObject) {
        WorkOut workOut1;
        String name = jsonObject.getString("name");
        workOut1 = new WorkOut(name);
        JSONArray exerciseArray = jsonObject.getJSONArray("exercises");
        for (Object json : exerciseArray) {
            JSONObject nextExercise = (JSONObject) json;
            Exercise exercise = makeExercise(nextExercise);
            workOut1.addExercise(exercise);
        }
        return workOut1;
    }

    private Exercise makeExercise(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        Exercise exercise = new Exercise(name, description, reps, sets);
        return exercise;
    }
}

