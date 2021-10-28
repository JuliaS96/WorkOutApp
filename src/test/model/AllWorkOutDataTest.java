package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllWorkOutDataTest {
    private AllWorkOutData data;
    private Exercise testExercise;
    private Exercise testExercise1;

    private WorkOut testWorkout;
    private WorkOut testWorkout2;
    private ArrayList<WorkOut> workouts = new ArrayList<WorkOut>();
    private ArrayList<WorkOut> workouts2 = new ArrayList<WorkOut>();
    private ArrayList<Exercise> exercises = new ArrayList<Exercise>();
    private ArrayList<Exercise> exercises2 = new ArrayList<Exercise>();


    @BeforeEach
    void runBefore() {
        data = new AllWorkOutData();
        testWorkout = new WorkOut("Full-body Work Out");
        testWorkout2 = new WorkOut("Workout 2");
        testExercise = new Exercise("Sit-ups",
                "Lie down and sit up", 10, 5);
        testExercise1 = new Exercise("Sit-ups",
                "Lie down and sit up", 1, 3);
        testWorkout.addExercise(testExercise);
        testWorkout.addExercise(testExercise1);
        workouts2.add(testWorkout);
        workouts2.add(testWorkout2);
        exercises2.add(testExercise);
        exercises2.add(testExercise1);
    }

    @Test
    void testConstructor() {
        assertEquals(0, data.getPersonStats().getCompletedWorkouts());
        assertEquals(0, data.getPersonStats().getCompletedReps());
        assertTrue(data.getWorkouts().isEmpty());
        assertTrue(data.getExercises().isEmpty());

    }

    @Test
    void testUpdatePS() {
        data.updatePS(3,4);
        assertEquals(4, data.getPersonStats().getCompletedWorkouts());
        assertEquals(3, data.getPersonStats().getCompletedReps());

        data.updatePS(20,6);
        assertEquals(6, data.getPersonStats().getCompletedWorkouts());
        assertEquals(20, data.getPersonStats().getCompletedReps());


    }

    @Test
    void testUpdateEx() {
        data.updateEx(exercises2);
        assertEquals(2, data.getExercises().size());
        Exercise ex = (Exercise) data.getExercises().get(0);
        assertEquals("Sit-ups", ex.getName());
        assertEquals(10, ex.getReps());
        assertEquals(5, ex.getSets());

        data.updateEx(exercises);
        assertEquals(0, data.getExercises().size());
    }

    @Test
    void testUpdateWO() {
        data.updateWO(workouts2);
        assertEquals(2, data.getWorkouts().size());
        WorkOut workOut1 = (WorkOut) data.getWorkouts().get(0);
        assertEquals("Full-body Work Out", workOut1.getWorkOutName());
        WorkOut workOut2 = (WorkOut) data.getWorkouts().get(1);
        assertEquals("Workout 2", workOut2.getWorkOutName());
        assertEquals(0, workOut2.getExercises().size());

        data.updateWO(workouts);
        assertEquals(0, data.getWorkouts().size());

    }

    @Test
    void testToJson()  {
        data.updateEx(exercises2);
        data.updateWO(workouts2);
        data.updatePS(3,4);
        data.toJson();

        JSONArray stats = data.toJson().getJSONArray("Personal Stats");
        JSONArray workOuts = data.toJson().getJSONArray("WorkOuts");
        JSONArray exercises = data.toJson().getJSONArray("Exercises");

        assertEquals(stats.length(),1);
        assertEquals(workOuts.length(), 2);
        assertEquals(exercises.length(), 2);

    }

    @Test
    void testPersonStatsToJson()  {
        data.updatePS(3,4);
        data.toJson();
        JSONArray stats = data.toJson().getJSONArray("Personal Stats");
        assertEquals(stats.length(),1);
    }

    @Test
    void testWorkoutsToJson()  {
        data.updateWO(workouts2);
        data.toJson();
        JSONArray workOuts = data.toJson().getJSONArray("WorkOuts");
        assertEquals(workOuts.length(),2);
    }

    @Test
    void testExercisesToJson()  {
        data.updateEx(exercises2);
        data.toJson();
        JSONArray exercises = data.toJson().getJSONArray("Exercises");
        assertEquals(exercises.length(),2);

    }


}
