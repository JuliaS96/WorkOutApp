package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


}