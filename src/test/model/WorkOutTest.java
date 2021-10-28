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
        assertEquals("Full-body Work Out", testWorkout.getWorkOutName() );
        assertTrue(testWorkout.getExercises().isEmpty());
    }

    @Test
    void testAddExercise() {
        testWorkout.addExercise(sitUps);
        assertEquals(sitUps, testWorkout.getExercises().get(0));
        assertEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(crunches);
        testWorkout.addExercise(squats);
        assertEquals(crunches, testWorkout.getExercises().get(1) );
        assertEquals(squats, testWorkout.getExercises().get(2));
        assertEquals(3, testWorkout.getExercises().size());

    }

    @Test
    void testToJson()  {
        testWorkout.addExercise(crunches);
        testWorkout.addExercise(squats);
        assertEquals("Full-body Work Out", testWorkout.toJson().get("name"));
        JSONArray exercises = testWorkout.toJson().getJSONArray("exercises");
        assertEquals(2, exercises.length());
        JSONObject exercise1 = (JSONObject) exercises.get(0);
        JSONObject exercise2 = (JSONObject) exercises.get(1);
        assertEquals("Crunches", exercise1.get("name"));
        assertEquals(("Squats"), exercise2.get("name"));
        assertEquals("Lie down and sit half-way up", exercise1.get("description"));
        assertEquals("Sit down as if there is an imaginary chair behind you",
                exercise2.get("description"));
        assertEquals(3, exercise1.get("sets"));
        assertEquals(3, exercise2.get("sets"));
        assertEquals(8, exercise1.get("reps"));
        assertEquals(12, exercise2.get("reps"));

    }


}