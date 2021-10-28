package persistence;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderInvalidFile() {
        JsonReader reader = new JsonReader("./data/randomFileName.json");
        try {
            AllWorkOutData data = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }
    @Test
    void testReaderEmptyData() {
        JsonReader reader = new JsonReader("./data/emptyTestDataReader.json");
        try {
            AllWorkOutData data = reader.read();
            assertEquals(0, data.getPersonStats().getCompletedWorkouts());
            assertEquals(0, data.getPersonStats().getCompletedReps());
            assertEquals(0, data.getWorkouts().size());
            assertEquals(0, data.getExercises().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderDefaultData() {
        JsonReader reader = new JsonReader("./data/defaultTestData.json");
        try {
            AllWorkOutData data = reader.read();
            WorkOut workOut1 = (WorkOut) data.getWorkouts().get(0);
            Exercise exercise2 = (Exercise) data.getExercises().get(2);
            assertEquals(0, data.getPersonStats().getCompletedWorkouts());
            assertEquals(0, data.getPersonStats().getCompletedReps());
            assertEquals(1, data.getWorkouts().size());
            assertEquals(3, data.getExercises().size());
            assertEquals("Default Full-Body Workout", workOut1.getWorkOutName());
            assertEquals(3, workOut1.getExercises().size());
            assertEquals("Sit-ups", exercise2.getName());
            assertEquals("Lie down and sit up", exercise2.getDescription());
            assertEquals(5, exercise2.getReps());
            assertEquals(2, exercise2.getSets());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderUpdatedData() {
        JsonReader reader = new JsonReader("./data/updatedTestDataReader.json");
        try {
            AllWorkOutData data = reader.read();
            WorkOut workOut1 = (WorkOut) data.getWorkouts().get(1);
            Exercise exercise2 = (Exercise) data.getExercises().get(1);
            assertEquals(5, data.getPersonStats().getCompletedWorkouts());
            assertEquals(55, data.getPersonStats().getCompletedReps());
            assertEquals(2, data.getWorkouts().size());
            assertEquals("New Test", workOut1.getWorkOutName());
            assertEquals(5, workOut1.getExercises().size());
            assertEquals(4, data.getExercises().size());
            assertEquals("Crunches", exercise2.getName());
            assertEquals("Lie down and sit half-way up", exercise2.getDescription());
            assertEquals(4, exercise2.getReps());
            assertEquals(1, exercise2.getSets());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}
