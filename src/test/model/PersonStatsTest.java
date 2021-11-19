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
    void testConstructor() {
        assertEquals(0, testPersonStats.getCompletedReps());
        assertEquals(0, testPersonStats.getCompletedWorkouts());
    }

    @Test
    void testAddCompletedReps() {
        testPersonStats.addCompletedReps(0);
        assertEquals(0, testPersonStats.getCompletedReps());
        testPersonStats.addCompletedReps(1);
        testPersonStats.addCompletedReps(2);
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

    @Test
    void testSetCompletedReps() {
        testPersonStats.setCompletedReps(3);
        assertEquals(3, testPersonStats.getCompletedReps());
        testPersonStats.setCompletedReps(6);
        assertEquals(6, testPersonStats.getCompletedReps());
        testPersonStats.setCompletedReps(0);
        assertEquals(0, testPersonStats.getCompletedReps());
    }

    @Test
    void testSetCompletedWorkouts() {
        testPersonStats.setCompletedWorkOuts(4);
        assertEquals(4, testPersonStats.getCompletedWorkouts());
        testPersonStats.setCompletedWorkOuts(10);
        assertEquals(10, testPersonStats.getCompletedWorkouts());
        testPersonStats.setCompletedWorkOuts(0);
        assertEquals(0, testPersonStats.getCompletedWorkouts());
    }

    @Test
    void testToJson()  {
        assertEquals(0, testPersonStats.toJson().get("Reps"));
        assertEquals(0, testPersonStats.toJson().get("WorkOuts"));
        testPersonStats.setCompletedWorkOuts(10);
        testPersonStats.setCompletedReps(6);
        assertEquals(6, testPersonStats.toJson().get("Reps"));
        assertEquals(10, testPersonStats.toJson().get("WorkOuts"));
    }




}