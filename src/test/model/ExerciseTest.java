package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {
    private Exercise testExercise;
    private Exercise testExercise1;
    private Exercise testExercise0;
    private Exercise testExercise7;
    private Exercise testExercise15;
    private Exercise testExercise23;
    private Exercise testExercise29;
    private Exercise testExercise30;
    private Exercise testExercise31;
    private Exercise testExercise38;
    private Exercise testExercise16;
    private Exercise testExercise14;


    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Sit-ups",
                "Lie down and sit up", 10, 5);
        testExercise1 = new Exercise("Sit-ups",
                "Lie down and sit up", 1, 1);
        testExercise0 = new Exercise("Sit-ups",
                "Lie down and sit up", 0, 0);
        testExercise7 = new Exercise("Sit-ups",
                "Lie down and sit up", 7, 1);
        testExercise14 = new Exercise("Sit-ups",
                "Lie down and sit up", 2, 7);
        testExercise15 = new Exercise("Sit-ups",
                "Lie down and sit up", 3, 5);
        testExercise16 = new Exercise("Sit-ups",
                "Lie down and sit up", 4, 4);
        testExercise23 = new Exercise("Sit-ups",
                "Lie down and sit up", 23, 1);
        testExercise29 = new Exercise("Sit-ups",
                "Lie down and sit up", 1, 29);
        testExercise30 = new Exercise("Sit-ups",
                "Lie down and sit up", 3, 10);
        testExercise31 = new Exercise("Sit-ups",
                "Lie down and sit up", 31, 1);
        testExercise38 = new Exercise("Sit-ups",
                "Lie down and sit up", 2, 19);

    }

    @Test
    void testConstructor() {
        assertEquals("Sit-ups", testExercise.getName());
        assertEquals("Lie down and sit up", testExercise.getDescription());
        assertEquals(10, testExercise.getReps());
        assertEquals(5, testExercise.getSets());
    }

    @Test
    void testExerciseDifficulty() {
        assertEquals("Easy", testExercise0.exerciseDifficulty());
        assertEquals("Easy", testExercise1.exerciseDifficulty());
        assertEquals("Easy", testExercise7.exerciseDifficulty());
        assertEquals("Easy", testExercise14.exerciseDifficulty());
        assertEquals("Moderate", testExercise15.exerciseDifficulty());
        assertEquals("Moderate", testExercise16.exerciseDifficulty());
        assertEquals("Moderate", testExercise23.exerciseDifficulty());
        assertEquals("Moderate", testExercise29.exerciseDifficulty());
        assertEquals("Difficult", testExercise30.exerciseDifficulty());
        assertEquals("Difficult", testExercise31.exerciseDifficulty());
        assertEquals("Difficult", testExercise38.exerciseDifficulty());





    }


}
