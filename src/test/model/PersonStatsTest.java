package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonStatsTest {
    private PersonStats testPersonStats;

    @BeforeEach
    void runBefore() {
        testPersonStats = new PersonStats();
    }


    @Test
    void testContructor() {
        assertEquals(0, testPersonStats.getCompletedReps());
        assertEquals(0, testPersonStats.getCompletedWorkouts());
    }

    @Test
    void testAddCompletedReps() {
        testPersonStats.addCompletedReps();
        assertEquals(1, testPersonStats.getCompletedReps());
        testPersonStats.addCompletedReps();
        testPersonStats.addCompletedReps();
        assertEquals(3, testPersonStats.getCompletedReps());
    }

    @Test
    void testAddCompletedWorkout() {
        testPersonStats.addCompletedWorkout();
        assertEquals(1, testPersonStats.getCompletedWorkouts());
        testPersonStats.addCompletedWorkout();
        testPersonStats.addCompletedWorkout();
        assertEquals(3, testPersonStats.getCompletedWorkouts());
    }

}