package persistence;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {


    @Test
    void testWriterInvalidFile() {
        try {
            AllWorkOutData wr = new AllWorkOutData();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyData() {
        try {
            AllWorkOutData data = new AllWorkOutData();
            JsonWriter writer = new JsonWriter("./data/emptyTestDataWriter.json");
            writer.open();
            writer.write(data);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyTestDataWriter.json");
            data = reader.read();
            assertEquals(0, data.getPersonStats().getCompletedWorkouts());
            assertEquals(0, data.getPersonStats().getCompletedReps());
            assertEquals(0, data.getWorkouts().size());
            assertEquals(0, data.getExercises().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterUpdatedData() {
        try {
            AllWorkOutData data;
            data = testInit();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(data);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            data = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }


    public AllWorkOutData testInit() {
        AllWorkOutData data = new AllWorkOutData();
        data.updatePS(55,5);

        Exercise crunches = new Exercise("Crunches",
                "Lie down and sit half-way up", 4, 1);
        Exercise sitUps = new Exercise("Sit-ups",
                "Lie down and sit up", 5, 2);
        Exercise squats = new Exercise("Squats",
                "Sit down as if there is an imaginary chair behind you",
                4, 2);
        Exercise test = new Exercise("Exercise Test",
                "Test",
                6, 3);

        Exercise defaultExercise = new Exercise("Default Exercise", "Jump! Jump! Jump!", 3, 2);
        WorkOut fullBody = new WorkOut("Default Full-Body Workout");
        fullBody.addExercise(crunches);
        fullBody.addExercise(sitUps);
        fullBody.addExercise(squats);


        WorkOut testWorkOut = new WorkOut("New Test");
        testWorkOut.addExercise(test);
        testWorkOut.addExercise(squats);
        testWorkOut.addExercise(squats);
        testWorkOut.addExercise(sitUps);
        testWorkOut.addExercise(defaultExercise);

        data.getWorkouts().add(fullBody);
        data.getWorkouts().add(testWorkOut);
        data.getExercises().add(squats);
        data.getExercises().add(crunches);
        data.getExercises().add(sitUps);
        data.getExercises().add(test);

        return data;
    }
}


