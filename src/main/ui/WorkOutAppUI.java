package ui;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.buttons.*;
import ui.buttons.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Represents the main window in which the work-out app functions
// Includes buttons and screen for workouts

public class WorkOutAppUI extends JFrame {
    private static final int WIDTH = 850;
    private static final int HEIGHT = 800;
    private static final String JSON_STORE = "./data/workoutData.json";
    private Exercise defaultExercise;
    private WorkOut fullBody;
    private Scanner input;
    private AllWorkOutData data;
    private Button activeButton;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private AddNewExerciseButton addNewExerciseButton;
    private StartWorkOutButton startWorkOutButton;
    private DisplayAllWorkoutsButton displayAllWorkoutsButton;
    private DisplayAllExercisesButton displayAllExercisesButton;
    private SeeStatsButton seeStatsButton;
    private SaveButton saveButton;
    private LoadButton loadButton;
    private AddWorkOutButton addWorkOutButton;
    private JTabbedPane tabbedPane;


    // EFFECTS: Constructor sets up window, and button panel.
    public WorkOutAppUI() throws FileNotFoundException {
        super("WorkOut App");
        init();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeGraphics();
        initializeInteraction();
        //addKeyListener(new KeyHandler());
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

    private void initializeInteraction() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
        };
        addMouseListener(mouseAdapter);
    }

    // EFFECTS: draws the JFrame window where the DrawingEditor will operate, and populates the menu buttons
    public void initializeGraphics() {
        desktop = new JDesktopPane();
        desktop.setLayout(new BorderLayout());
        setContentPane(desktop);
        setSize(WIDTH, HEIGHT);
        createButtons();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
        createTabs();
    }

    //Code used from the AlarmSystem application
    //Effects: Centres main app window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    public void createTabs() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Your Stats", null, statsPane(), null);
        tabbedPane.addTab("Add Exercise", null, statsPane(), null);
        tabbedPane.addTab("Add Workout", null, statsPane(), null);
        tabbedPane.addTab("Exercises", null, exercisesPane(), null);
        tabbedPane.addTab("WorkOuts", null, workOutsPane(), null);
        tabbedPane.addTab("Play!", null, statsPane(), null);
        tabbedPane.setSelectedIndex(0);
        add(tabbedPane);
    }

    public JPanel statsPane() {
        JPanel personStatsPanel = new JPanel();
        int reps = data.getPersonStats().getCompletedReps();
        int workouts = data.getPersonStats().getCompletedWorkouts();
        personStatsPanel.setLayout(new BoxLayout(personStatsPanel,
                BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Your Stats:", JLabel.CENTER);
        JLabel labelReps = new JLabel("You have completed " + String.valueOf(reps) + " repetitions.",
                JLabel.CENTER);
        JLabel labelWorkouts = new JLabel("You have completed " + String.valueOf(workouts) + " workouts.",
                JLabel.CENTER);
        personStatsPanel.add(label);
        personStatsPanel.add(labelReps);
        personStatsPanel.add(labelWorkouts);
        return personStatsPanel;
    }

    public JPanel exercisesPane() {
        ArrayList<Exercise> exercises = data.getExercises();
        JPanel exercisesPane = new JPanel();
        for (Exercise e : exercises) {
            JLabel label = new JLabel(e.getName());
            exercisesPane.add(label);
        }
        return exercisesPane;
    }


    public JPanel workOutsPane() {
        ArrayList<WorkOut> workOuts = data.getWorkouts();
        JPanel exercisesPane = new JPanel();
        for (WorkOut w : workOuts) {
            JLabel label = new JLabel(w.getWorkOutName());
            exercisesPane.add(label);
        }
        return exercisesPane;
    }

    public void createButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(0, 0));
        add(buttonArea, BorderLayout.WEST);
        seeStatsButton = new SeeStatsButton(this, buttonArea);
        startWorkOutButton = new StartWorkOutButton(this, buttonArea);
        addNewExerciseButton = new AddNewExerciseButton(this, buttonArea);
        addWorkOutButton = new AddWorkOutButton(this, buttonArea);
        displayAllExercisesButton = new DisplayAllExercisesButton(this, buttonArea);
        displayAllWorkoutsButton = new DisplayAllWorkoutsButton(this, buttonArea);
        loadButton = new LoadButton(this, buttonArea);
        saveButton = new SaveButton(this, buttonArea);
        seeStatsButton.activate();
    }


    // EFFECTS: if activeTool != null, then mouseClicked
    public void handleMouseClicked(MouseEvent e) {
        if (activeButton != null) {
            activeButton.mouseClicked(e, this);
        }
        repaint();
        activeButton.performAction(this);
    }


    // Based from SimpleDrawingPlayer
    // MODIFIES: this
    // EFFECTS:  sets the given tool as the activeTool
    public void setActiveButton(Button button) {
        if (activeButton != null) {
            activeButton.deactivate();
        }
        button.activate();
        activeButton = button;
        activeButton.performAction(this);
    }

    private class WorkOutMouseListener extends MouseAdapter {

        // EFFECTS:Forward mouse clicked event to the active button
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(e);
        }

    }

    public AllWorkOutData getData() {
        return data;
    }

    // EFFECTS: saves the workroom to file
    // Used saveWorkRoom from JSONSerializationDemo
    // Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void saveProgress() {
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
    public void loadProgress() {
        try {
            data = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public JTabbedPane getTabs() {
        return tabbedPane;
    }


}
