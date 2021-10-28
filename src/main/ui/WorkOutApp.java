package ui;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// WorkOutApp references code from TellerApp
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Additional detail provided in the referenced parts

// WorkOut Application
public class WorkOutApp {
    private static final String JSON_STORE = "./data/workoutData.json";
    private Exercise defaultExercise;
    private WorkOut fullBody;
    private Scanner input;
    private AllWorkOutData data;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the WorkOut application
    public WorkOutApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runWorkOutApp();

    }

    // Code used from runTeller() in Teller App Code and modified as needed
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runWorkOutApp() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;

            } else {
                processCommand(command);
            }
        }
        System.out.println("\n Workout Terminated.");
    }

    // Code used from processCommand() in Teller App Code and modified as needed
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addWorkout();
        } else if (command.equals("b")) {
            addExercise();
        } else if (command.equals("c")) {
            seeStats();
        } else if (command.equals("d")) {
            startWorkout();
        } else if (command.equals("e")) {
            displayAllWorkouts();
        } else if (command.equals("f")) {
            displayAllExercises();
        } else if (command.equals("s")) {
            saveProgress();
        } else if (command.equals("l")) {
            loadProgress();
        } else {
            System.out.println("Please select valid input.");
        }
    }

    // Code used from init() in Teller App Code and modified as needed
    // MODIFIES: this
    // EFFECTS: initializes default exercises and workout
    private void init() {
        data = new AllWorkOutData();
        Exercise crunches = new Exercise("Crunches",
                "Lie down and sit half-way up", 4, 1);
        Exercise sitUps = new Exercise("Sit-ups",
                "Lie down and sit up", 5, 2);
        Exercise squats = new Exercise("Squats",
                "Sit down as if there is an imaginary chair behind you",
                4, 2);
        defaultExercise = new Exercise("Default Exercise", "Jump! Jump! Jump!", 3, 2);
        fullBody = new WorkOut("Default Full-Body Workout");
        fullBody.addExercise(crunches);
        fullBody.addExercise(sitUps);
        fullBody.addExercise(squats);
        data.getWorkouts().add(fullBody);
        data.getExercises().add(squats);
        data.getExercises().add(crunches);
        data.getExercises().add(sitUps);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // Code used from displayMenu() in Teller App Code and modified as needed
    // EFFECTS: Display menu to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add workout");
        System.out.println("\tb -> add exercise");
        System.out.println("\tc -> see stats");
        System.out.println("\td -> start workout");
        System.out.println("\te -> display all workouts");
        System.out.println("\tf -> display all exercises");
        System.out.println("\ts -> save progress");
        System.out.println("\tl -> load progress");
        System.out.println("\tq -> quit");
    }

    // REQUIRES: Exercises added to Workout must be in the allExercises Array
    // MODIFIES: this
    // EFFECTS: creates a new Workout and adds it to the allWorkouts Array
    private void addWorkout() {
        String selection = "";
        System.out.println("Enter workout name: ");
        String workoutName = input.next();
        WorkOut newWorkout = new WorkOut(workoutName);
        data.getWorkouts().add(newWorkout);
        System.out.println("Enter exercise name or press d if done.");
        while (!selection.equals("d")) {
            Exercise addExercise;
            System.out.println("Enter exercise:");
            String exerciseName = input.next();
            selection = exerciseName;
            if (selection.equals("d")) {
                System.out.println("Back to main menu.");
            } else {
                addExercise = searchExerciseArray(data.getExercises(), exerciseName);
                newWorkout.addExercise(addExercise);
                System.out.println("Exercise added if it is in directory!");
            }
        }

    }

    // REQUIRES: exercise name and description must be strings; reps and sets must be positive integers
    // MODIFIES: this
    // EFFECTS: creates a new Exercise and adds it to the allExercises Array
    private void addExercise() {
        Exercise exercise;
        System.out.println("Enter exercise name:");
        String exerciseName = input.next();
        System.out.println("Enter exercise description:");
        String exerciseDescription = input.next();
        System.out.println("Enter exercise reps:");
        int exerciseReps = input.nextInt();
        System.out.println("Enter exercise sets:");
        int exerciseSets = input.nextInt();
        exercise = new Exercise(exerciseName, exerciseDescription, exerciseReps, exerciseSets);
        data.getExercises().add(exercise);
        System.out.println("Exercise added to directory!");
    }

    // MODIFIES: personStats
    // EFFECTS: starts chosen workout. If the workout typed is not in allWorkouts
    //          array, then it will start the default workouts.
    //          Once completed, a workout is added to the personStats
    private void startWorkout() {
        WorkOut chosenWorkOut;
        System.out.println("Type the name of your Workout:");
        String typedWorkOut = input.next();
        chosenWorkOut = searchWorkOutArray(data.getWorkouts(), typedWorkOut);
        System.out.println("Starting " + chosenWorkOut.getWorkOutName() + " in \n");
        System.out.println("3...\n");
        System.out.println("2...\n");
        System.out.println("1...\n");
        ArrayList<Exercise> allExercises;
        allExercises = chosenWorkOut.getExercises();
        for (int i = 0; i < allExercises.size(); i++) {
            playExercise(allExercises.get(i));
            if (i < (allExercises.size() - 1)) {
                System.out.println("Next exercise!");
            } else {
                data.getPersonStats().addCompletedWorkout();
                System.out.println("Great job completing this workout!");
            }
        }
    }

    // EFFECTS: prints out the number of completed Workouts and Reps
    private void seeStats() {
        System.out.println("You have completed " + data.getPersonStats().getCompletedWorkouts() + " workouts.");
        System.out.println("You have completed " + data.getPersonStats().getCompletedReps() + " reps.");
    }

    // EFFECTS: searches the allWorkouts array and produces the workout.
    //          If it is not in the array, it produces the default workout.
    private WorkOut searchWorkOutArray(ArrayList<WorkOut> workOut, String workOutName) {
        WorkOut chosenOne = fullBody;
        for (WorkOut out : workOut) {
            if (out.getWorkOutName().equals(workOutName)) {
                chosenOne = out;
            }
        }
        return chosenOne;
    }

    // EFFECTS: searches the exercise array and produces the exercise.
    //          If it is not in the array, it produces the default exercise.
    private Exercise searchExerciseArray(ArrayList<Exercise> exercise, String exerciseName) {
        Exercise chosenOne = defaultExercise;
        for (Exercise value : exercise) {
            if (value.getName().equals(exerciseName)) {
                chosenOne = value;
            }
        }
        return chosenOne;

    }

    // MODIFIES: personStats, allWorkoutData
    // EFFECTS: starts playing the chosen exercise. Adds one to reps in
    //          PersonStats with each completed rep.
    private void playExercise(Exercise exercise) {
        System.out.println(exercise.getDescription());
        int st = exercise.getSets();
        System.out.println("Starting first set of " + exercise.getName() + "!");
        System.out.println("This exercise is: " + exercise.exerciseDifficulty() + ".");
        for (int j = 0; j < exercise.getSets(); j++) {
            int rp = exercise.getReps();
            playExerciseHelper(exercise, rp);
            System.out.println((j + 1) + " sets done! " + (st - j - 1) + " sets left!");
            if ((st - j - 1) == 0) {
                System.out.println("All sets done.");

            } else {
                System.out.println("Starting next set...");
            }
        }
    }

    // REQUIRES: rp > 0 and i > 0
    // MODIFIES: personStats
    // EFFECTS: helper function for playExercise. Starts playing the chosen exercise.
    //          Adds one to reps in PersonStats with each completed rep.
    private void playExerciseHelper(Exercise exercise, int rp) {
        System.out.println("Perform " + rp + " reps of " + exercise.getName() + ".");
        System.out.println("Press any key when done or q to quit.");
        String nextRep = input.next();
        if (nextRep.equals("q")) {
            System.out.println("Better luck next time!");
            java.lang.System.exit(0);
        } else {
            for (int i = 0; i < exercise.getReps(); i++) {
                data.getPersonStats().addCompletedReps();
            }
        }

    }

    // EFFECTS: allows you to view the names of available exercises
    private void displayAllExercises() {
        System.out.println("Exercises available:");
        for (int i = 0; i < data.getExercises().size(); i++) {
            ArrayList<Exercise> exercises = data.getExercises();
            System.out.println(exercises.get(i).getName());
        }
    }

    // EFFECTS: allows you to view the names of available workouts
    private void displayAllWorkouts() {
        System.out.println("Workouts available:");
        for (int i = 0; i < data.getWorkouts().size(); i++) {
            ArrayList<WorkOut> workouts = data.getWorkouts();
            System.out.println(workouts.get(i).getWorkOutName());
        }
    }

    // EFFECTS: saves the workroom to file
    // Used saveWorkRoom from JSONSerializationDemo
    // Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveProgress() {
        try {
            jsonWriter.open();
            jsonWriter.write(data);
            jsonWriter.close();
            System.out.println("Saved progress to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    // Used loadWorkRoom from JSONSerializationDemo
    // Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void loadProgress() {
        try {
            data = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

