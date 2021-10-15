package ui;

import model.Exercise;
import model.WorkOut;
import model.PersonStats;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkOutApp {
    private Exercise defaultExercise;
    private WorkOut fullBody;
    private Scanner input;
    private PersonStats personStats = new PersonStats();
    private ArrayList<WorkOut> allWorkouts = new ArrayList<>();
    private ArrayList<Exercise> allExercises = new ArrayList<>();

    // EFFECTS: runs the WorkOut application
    public WorkOutApp() {
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

    // Code used from init() in Teller App Code and modified as needed
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
        } else {
            System.out.println("Please select valid input.");
        }
    }

    // Code used from init() in Teller App Code and modified as needed
    // MODIFIES: this
    // EFFECTS: initializes default exercises and workout
    private void init() {
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
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: Display menu to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add workout");
        System.out.println("\tb -> add exercise");
        System.out.println("\tc -> see stats");
        System.out.println("\td -> start workout");
        System.out.println("\tq -> quit");
    }

    // REQUIRES: Exercises added to workout must be in the allExercises Array
    // MODIFIES: allWorkouts Array
    // EFFECTS: creates a new Workout and adds it to the allWorkouts Array
    private void addWorkout() {
        String selection = "";
        System.out.println("Enter workout name: ");
        String workoutName = input.next();
        WorkOut newWorkout = new WorkOut(workoutName);
        allWorkouts.add(newWorkout);
        System.out.println("Enter exercise name or press d if done.");
        while (!selection.equals("d")) {
            Exercise addExercise;
            System.out.println("Enter exercise:");
            String exerciseName = input.next();
            selection = exerciseName;
            addExercise = searchExerciseArray(allExercises, exerciseName);
            newWorkout.addExercise(addExercise);
            if (selection.equals("d")) {
                System.out.println("Back to main menu.");
            } else {
                System.out.println("Exercise added!");
            }
        }

    }

    // REQUIRES: exercise name and description must be strings; reps and sets must be positive integers
    // MODIFIES: allExercises Array
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
        allExercises.add(exercise);
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
        chosenWorkOut = searchWorkOutArray(allWorkouts, typedWorkOut);
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
                personStats.addCompletedWorkout();
                System.out.println("Great job completing this workout!");
            }
        }

    }

    // EFFECTS: prints out the number of completed Workouts and Reps
    private void seeStats() {
        System.out.println("You have completed " + personStats.getCompletedWorkouts() + " workouts.");
        System.out.println("You have completed " + personStats.getCompletedReps() + " reps.");
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

    // MODIFIES: personStats
    // EFFECTS: starts playing the chosen exercise. Adds one to reps in
    //          PersonStats with each completed rep.
    private void playExercise(Exercise exercise) {
        System.out.println(exercise.getDescription());
        int st = exercise.getSets();
        System.out.println("Starting first set of " + exercise.getName() + "!");
        System.out.println("This exercise is: " + exercise.exerciseDifficulty() + ".");
        for (int j = 0; j < exercise.getSets(); j++) {
            int rp = exercise.getReps();
            for (int i = 0; i < exercise.getReps(); i++) {
                playExerciseHelper(exercise, rp, i);
                rp = rp - 1;
            }
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

    private void playExerciseHelper(Exercise exercise, int rp, int i) {
        System.out.println("Perform 1 rep of " + exercise.getName() + ".");
        System.out.println("Press any key when done or q to quit.");
        String nextRep = input.next();
        if (nextRep.equals("q")) {
            System.out.println("Better luck next time!");
            java.lang.System.exit(0);
        } else {
            rp = rp - 1;
            System.out.println((i + 1) + " reps done, " + rp + " reps left.");
            personStats.addCompletedReps();

        }
    }

//    // !!!
//    // to be added in next phase
//    private void deleteWorkout() {
//
//    }
//
//    // !!!
//    // to be added in next phase
//    private void deleteExercise() {
//
//    }

}

