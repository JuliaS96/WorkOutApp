package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {
    private Exercise testExercise;
    private Exercise testExerciseLow;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Sit-ups",
                "Lie down and sit up", 10, 5);
        testExerciseLow = new Exercise("Sit-ups",
                "Lie down and sit up", 1, 1);
    }

    @Test
    void testConstructor() {
        assertEquals("Sit-ups", testExercise.getName());
        assertEquals("Lie down and sit up", testExercise.getDescription());
        assertEquals(10, testExercise.getReps());
        assertEquals(5, testExercise.getSets());
    }


}
