package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkOutTest {
    private WorkOut testWorkout;
    private Exercise crunches;
    private Exercise sitUps;
    private Exercise squats;

    @BeforeEach
    void runBefore() {
        testWorkout = new WorkOut("Full-body Work Out");
        crunches = new Exercise("Crunches",
                "Lie down and sit half-way up", 8, 3);
        sitUps = new Exercise("Sit-ups",
                "Lie down and sit up", 10, 3);
        squats = new Exercise("Squats",
                "Sit down as if there is an imaginary chair behind you",
                12, 3);

    }

    @Test
    void testConstructor() {
        assertEquals(testWorkout.getWorkOutName(), "Full-body Work Out");
        assertTrue(testWorkout.getExercises().isEmpty());
    }

    @Test
    void testAddExercise() {
        testWorkout.addExercise(sitUps);
        assertEquals(testWorkout.getExercises().get(0), sitUps);
        assertEquals(testWorkout.getExercises().size(),1);
        testWorkout.addExercise(crunches);
        testWorkout.addExercise(squats);
        assertEquals(testWorkout.getExercises().get(1), crunches);
        assertEquals(testWorkout.getExercises().get(2), squats);
        assertEquals(testWorkout.getExercises().size(),3);

    }

    @Test
    void testToJson()  {
        testWorkout.addExercise(crunches);
        testWorkout.addExercise(squats);
        assertEquals(testWorkout.toJson().get("name"), "Full-body Work Out");
        JSONArray exercises = testWorkout.toJson().getJSONArray("exercises");
        assertEquals(exercises.length(),2);
        JSONObject exercise1 = (JSONObject) exercises.get(0);
        JSONObject exercise2 = (JSONObject) exercises.get(1);
        assertEquals(exercise1.get("name"),"Crunches");
        assertEquals(exercise2.get("name"),"Squats");
        assertEquals(exercise1.get("description"),"Lie down and sit half-way up");
        assertEquals(exercise2.get("description"),
                "Sit down as if there is an imaginary chair behind you");
        assertEquals(exercise1.get("sets"),3);
        assertEquals(exercise2.get("sets"),3);
        assertEquals(exercise1.get("reps"),8);
        assertEquals(exercise2.get("reps"),12);

    }


}