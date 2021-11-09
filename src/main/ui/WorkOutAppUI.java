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
    private static final int HEIGHT = 400;
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
    private AddExerciseButton addExerciseButton;
    private SeeStatsButton seeStatsButton;
    private SaveButton saveButton;
    private LoadButton loadButton;
    private AddWorkOutButton addWorkOutButton;
    private JTabbedPane tabbedPane;
    private JTextField nameField;
    private JTextField descriptionField;
    private SpinnerNumberModel modelReps;
    private SpinnerNumberModel modelSets;
    JTextField workOutToAddNameField;


    // EFFECTS: Constructor sets up window, and button panel.
    public WorkOutAppUI() throws FileNotFoundException {
        super("WorkOut App");
        init();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeGraphics(0);
        initializeInteraction();
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
    public void initializeGraphics(int i) {
        desktop = new JDesktopPane();
        desktop.setLayout(new BorderLayout());
        setContentPane(desktop);
        setSize(WIDTH, HEIGHT);
        createButtons();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
        createTabs(i);
    }

    //Code used from the AlarmSystem application
    //Effects: Centres main app window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    public void createTabs(int i) {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Your Stats", null, statsPane(), null);
        tabbedPane.addTab("Add Exercise", null, addExercisePane(), null);
        tabbedPane.addTab("Add Workout", null, addWorkOutPane(), null);
        tabbedPane.addTab("Exercises", null, exercisesPane(), null);
        tabbedPane.addTab("WorkOuts", null, workOutsPane(), null);
        tabbedPane.addTab("Play!", null, playExercisePanel(), null);
        add(tabbedPane);
        tabbedPane.setSelectedIndex(i);
    }

    public JPanel statsPane() {
        JPanel personStatsPanel = new JPanel();
        int reps = data.getPersonStats().getCompletedReps();
        int workouts = data.getPersonStats().getCompletedWorkouts();
        personStatsPanel.setLayout(new BoxLayout(personStatsPanel,
                BoxLayout.Y_AXIS));
        personStatsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel label = new JLabel("Your Stats:", JLabel.CENTER);
        JLabel labelReps = new JLabel("You have completed " + String.valueOf(reps) + " repetitions.",
                JLabel.CENTER);
        JLabel labelWorkouts = new JLabel("You have completed " + String.valueOf(workouts) + " workouts.",
                JLabel.CENTER);
        personStatsPanel.add(Box.createVerticalStrut(5));
        personStatsPanel.add(label);
        personStatsPanel.add(Box.createVerticalStrut(5));
        personStatsPanel.add(labelReps);
        personStatsPanel.add(Box.createVerticalStrut(5));
        personStatsPanel.add(labelWorkouts);
        return personStatsPanel;
    }

    public JPanel addExercisePane() {
        JPanel addExercisePanel = new JPanel();
        JLabel name = new JLabel("Exercise name:");
        JLabel description = new JLabel("Description:");
        JLabel reps = new JLabel("Reps:");
        JLabel sets = new JLabel("Sets:");
        nameField = new JTextField(10);
        descriptionField = new JTextField(10);
        modelReps = new SpinnerNumberModel(1, 1, 30, 1);
        modelSets = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner repsField = new JSpinner(modelReps);
        JSpinner setsField = new JSpinner(modelSets);
        addExercisePanel.setLayout(new BoxLayout(addExercisePanel, BoxLayout.Y_AXIS));
        addExerciseSetUp(addExercisePanel, name, nameField, description, descriptionField, reps,
                repsField, sets, setsField);
        addExerciseButton = new AddExerciseButton(this, addExercisePanel);
        return addExercisePanel;

    }

    public void addExerciseSetUp(JPanel addExercisePane, JLabel name, JTextField nameField,
                                 JLabel description, JTextField descriptionField, JLabel reps,
                                 JSpinner repsField, JLabel sets, JSpinner setsField) {
        addExercisePane.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        addExercisePane.add(Box.createVerticalStrut(5));
        addExercisePane.add(name);
        addExercisePane.add(nameField);
        addExercisePane.add(Box.createVerticalStrut(5));
        addExercisePane.add(description);
        addExercisePane.add(descriptionField);
        addExercisePane.add(Box.createVerticalStrut(5));
        addExercisePane.add(reps);
        addExercisePane.add(repsField);
        addExercisePane.add(Box.createVerticalStrut(5));
        addExercisePane.add(sets);
        addExercisePane.add(setsField);
        addExercisePane.add(Box.createVerticalStrut(5));

    }

    // !!!!
    public JPanel addWorkOutPane() {
        JPanel addWorkOutPanel = new JPanel();
        JLabel name = new JLabel("Please enter a name for your work out first:");
        workOutToAddNameField = new JTextField(10);
        ArrayList<String> allExercisesNames = new ArrayList<>();
        for (Object e : data.getExercises()) {
            Exercise e1 = (Exercise) e;
            String currName = e1.getName();
            allExercisesNames.add(currName);
        }
        SpinnerListModel exercisesAvailable = new SpinnerListModel(allExercisesNames);
        JSpinner exerciseSelection = new JSpinner(exercisesAvailable);
        addWorkOutPanel.setLayout(new BoxLayout(addWorkOutPanel, BoxLayout.Y_AXIS));
        addWorkOutPanel.add(name);
        addWorkOutPanel.add(workOutToAddNameField);
        addWorkOutButton(addWorkOutPanel);
        return addWorkOutPanel;

    }

    public void addWorkOutButton(JPanel panel) {
        AddWorkOutToListButton addWorkOutButton = new AddWorkOutToListButton(this, panel);
        // exercise selector pops up and allows you to pick the exercises one at a time
        // exercise selector has two buttons: add exercise and add workout

    }

    public JPanel exercisesPane() {
        ArrayList<Exercise> exercises = data.getExercises();
        JPanel exercisesPane = new JPanel();
        exercisesPane.setLayout(new BoxLayout(exercisesPane, BoxLayout.Y_AXIS));
        exercisesPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        exercisesPane.add(Box.createVerticalStrut(5));
        for (Exercise e : exercises) {
            JLabel label = new JLabel(e.getName());
            exercisesPane.add(label);
            exercisesPane.add(Box.createVerticalStrut(5));
        }
        return exercisesPane;
    }

    public JPanel workOutsPane() {
        ArrayList<WorkOut> workOuts = data.getWorkouts();
        JPanel workOutsPane = new JPanel();
        workOutsPane.setLayout(new BoxLayout(workOutsPane, BoxLayout.Y_AXIS));
        workOutsPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        workOutsPane.add(Box.createVerticalStrut(5));
        for (WorkOut w : workOuts) {
            JLabel label = new JLabel(w.getWorkOutName());
            workOutsPane.add(label);
            workOutsPane.add(Box.createVerticalStrut(5));
        }
        return workOutsPane;
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

    // !!!!
    public JPanel playExercisePanel() {
        JPanel playPanel = new JPanel();
        return playPanel;

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

    // getter
    // EFFECTS: gets the all workout data array
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

    public JTextField getExerciseName() {
        return nameField;
    }

    public JTextField getExerciseDesc() {
        return descriptionField;
    }

    public SpinnerNumberModel getModelReps() {
        return modelReps;
    }

    public SpinnerNumberModel getModelSets() {
        return modelSets;
    }


}
