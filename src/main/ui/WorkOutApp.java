package ui;

import model.Exercise;
import model.WorkOut;
import model.PersonStats;

import java.nio.file.LinkPermission;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class WorkOutApp {
    private Exercise crunches;
    private Exercise sitUps;
    private Exercise squats;
    private WorkOut fullBody;
    private Scanner input;
    private PersonStats personStats = new PersonStats();
    private ArrayList allWorkouts = new ArrayList();
    private ArrayList allExercises = new ArrayList();

    // EFFECTS: runs the WorkOut application
    public WorkOutApp() {
        runWorkOutApp();
    }

    // Code used from runTeller() in Teller App Code and modified as needed
    // MODIFIES: this
    // EFFECTS:
    private void runWorkOutApp() {
        boolean keepGoing = true;
        String command = null;

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
    // EFFECTS: creates exercises and workouts initially available
    private void init() {
        crunches = new Exercise("Crunches",
                "Lie down and sit half-way up", 8, 3);
        sitUps = new Exercise("Sit-ups",
                "Lie down and sit up", 10, 3);
        squats = new Exercise("Squats",
                "Sit down as if there is an imaginary chair behind you",
                12, 3);
        fullBody = new WorkOut("Full-body Workout");
        fullBody.addExercise(crunches);
        fullBody.addExercise(sitUps);
        fullBody.addExercise(squats);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // Display menu to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add workout");
        System.out.println("\tb -> add exercise");
        System.out.println("\tc -> see stats");
        System.out.println("\td -> start workout");
        System.out.println("\tq -> quit");
    }

    // REQUIRED: Must add valid entries to workout
    private void addWorkout() {
        String selection = "";
        System.out.println("Enter workout name: ");
        String workoutName = input.next();
        allWorkouts.add(workoutName);
        WorkOut newWorkout = new WorkOut(workoutName);
        System.out.println("Enter exercise name or press d if done.");
        while (!selection.equals("d")) {
            System.out.println("Enter exercise:");
            String exerciseName = input.next();
            selection = exerciseName;
            newWorkout.addExercise(squats);
            if (selection.equals("d")) {
                System.out.println("Back to main menu.");
            } else {
                System.out.println("Exercise added!");
            }

        }

    }

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

    private void startWorkout() {
        WorkOut chosenWorkOut;
        System.out.println("Type the name of your Workout:");
        String typedWorkOut = input.next();
        System.out.println("Starting " + chosenWorkout + "in \n");
        System.out.println("3...\n");
        System.out.println("2...\n");
        System.out.println("1...\n");
        chosenWorkOut = allWorkouts.get(0);




    }
    // prints stats of the person
    private void seeStats() {
        System.out.println("You have completed " + personStats.getCompletedWorkouts() + " workouts.");
        System.out.println("You have completed " + personStats.getCompletedReps() + " reps.");
    }


    // !!!
    // to be added in next phase
    private void deleteWorkout() {

    }
    // !!!
    // to be added in next phase
    private void deleteExercise() {

    }

}

