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

    @Test
    void testPerformRep() {
        testExercise.performRep();
        assertEquals(9, testExercise.getReps());
        testExercise.performRep();
        testExercise.performRep();
        assertEquals(7, testExercise.getReps());
        testExerciseLow.performRep();
        assertEquals(0, testExerciseLow.getReps());
        testExerciseLow.performRep();
        assertEquals(0, testExerciseLow.getReps());
    }

    @Test
    void testPerformSet() {
        testExercise.performSet();
        assertEquals(4, testExercise.getSets());
        testExercise.performSet();
        testExercise.performSet();
        assertEquals(2, testExercise.getSets());
        testExerciseLow.performSet();
        assertEquals(0, testExerciseLow.getSets());
        testExerciseLow.performSet();
        assertEquals(0, testExerciseLow.getSets());

    }
}