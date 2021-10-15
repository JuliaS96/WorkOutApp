package ui;

import model.Exercise;
import model.WorkOut;
import model.PersonStats;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkOutApp {
    private Exercise crunches;
    private Exercise defaultExercise;
    private Exercise sitUps;
    private Exercise squats;
    private WorkOut fullBody;
    private Scanner input;
    private PersonStats personStats = new PersonStats();
    private ArrayList<WorkOut> allWorkouts = new ArrayList();
    private ArrayList<Exercise> allExercises = new ArrayList();

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
                "Lie down and sit half-way up", 4, 1);
        sitUps = new Exercise("Sit-ups",
                "Lie down and sit up", 5, 2);
        squats = new Exercise("Squats",
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
        chosenWorkOut = searchWorkOutArray(allWorkouts, typedWorkOut);
        System.out.println("Starting " + chosenWorkOut.getWorkOutName() + " in \n");
        System.out.println("3...\n");
        System.out.println("2...\n");
        System.out.println("1...\n");
        ArrayList<Exercise> allExercises = chosenWorkOut.getExercises();
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

    // prints stats of the person
    private void seeStats() {
        System.out.println("You have completed " + personStats.getCompletedWorkouts() + " workouts.");
        System.out.println("You have completed " + personStats.getCompletedReps() + " reps.");
    }

    private WorkOut searchWorkOutArray(ArrayList<WorkOut> workOut, String workOutName) {
        WorkOut chosenOne = fullBody;
        for (int i = 0; i < workOut.size(); i++) {
            if (workOut.get(i).getWorkOutName().equals(workOutName)) {
                chosenOne = workOut.get(i);
            }
        }
        return chosenOne;
    }

    private Exercise searchExerciseArray(ArrayList<Exercise> exercise, String exerciseName) {
        Exercise chosenOne = defaultExercise;
        for (int i = 0; i < exercise.size(); i++) {
            if (exercise.get(i).getName().equals(exerciseName)) {
                chosenOne = exercise.get(i);
            }
        }
        return chosenOne;

    }

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


    // !!!
    // to be added in next phase
    private void deleteWorkout() {

    }

    // !!!
    // to be added in next phase
    private void deleteExercise() {

    }

}

