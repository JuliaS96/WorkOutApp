package ui;

import model.AllWorkOutData;
import model.Exercise;
import model.WorkOut;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.buttons.*;
import ui.buttons.Button;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.Math.min;

// Represents the main window in which the work-out app functions
// Includes buttons and screen for workouts

public class WorkOutAppUI extends JFrame {
    private static final int WIDTH = 530;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/workoutData.json";
    private Exercise defaultExercise;
    private WorkOut fullBody;
    private Scanner input;
    private Color backgroundColor = new Color(122, 158, 159, 255);
    private Color mediumColor = new Color(184, 216, 216, 255);
    private Color lightColor = new Color(238, 245, 219, 255);
    int visibility = 255;
    int bananaHeight = (int) Math.round(HEIGHT * 0.9);
    int bananaWidth = (int) Math.round(WIDTH * 0.95);
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
    private JTextField workOutToAddNameField;
    private JDialog exerciseSelector;
    private JScrollPane exercisesScroll;
    private JList<String> exercisesInJList;
    private JScrollPane pickExercise;
    private JList<String> namesToPlay;
    private JDialog exercisePlayer;
    private WorkOut currentWorkout = new WorkOut("default");
    private JPanel textPane;
    private int setsTotal = 0;
    private int exercisesTotal = 0;
    private int setsDone = 0;
    private int exercisesDone = 0;
    private JPanel playerPanel;


    // EFFECTS: Constructor sets up window, and button panel.
    public WorkOutAppUI() throws FileNotFoundException {
        super("WorkOut App");
        uiManager();
        init();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeGraphics(0);
        initializeInteraction();
    }


    // MODIFIES: this
    // EFFECTS: sets the theme of the UI
    public void uiManager() {
        UIManager ui = new UIManager();
        ui.put("OptionPane.background", backgroundColor);
        ui.put("OptionPane.font", (new Font("Arial", Font.PLAIN, 14)));
        ui.put("Panel.background", backgroundColor);
        ui.put("Panel.font", (new Font("Arial", Font.PLAIN, 14)));
        ui.put("TextPane.font", (new Font("Arial", Font.PLAIN, 14)));
        ui.put("TextPane.background", backgroundColor);
        ui.put("TextArea.background", backgroundColor);
        ui.put("TextArea.font", (new Font("Arial", Font.PLAIN, 14)));
        ui.put("Button.font", (new Font("Arial", Font.PLAIN, 14)));
        ui.put("TextField.background", lightColor);
        ui.put("TableHeader.background", lightColor);
        ui.put("TableHeader.foreground", backgroundColor);
        ui.put("Table.background", lightColor);
        ui.put("Table.gridColor", lightColor);

        setUndecorated(true);
    }

    // Code used from init() in Teller App Code and modified as needed
    // MODIFIES: this
    // EFFECTS: initializes default exercises and workout
    private void init() {
        data = new AllWorkOutData();
        Exercise crunches = new Exercise("Crunches",
                "Lie down and sit half-way up", 4, 3);
        Exercise sitUps = new Exercise("Sit-ups",
                "Lie down and sit up", 5, 3);
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

    // MODIFIES: this
    // EFFECTS: initializes MouseAdapter to be used in the JFrame
    private void initializeInteraction() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
        };
        addMouseListener(mouseAdapter);
    }

    // the paint JDesktopPane section was based from stackoverflow :
    // https://stackoverflow.com/questions/13814704/how-to-change-jdesktoppane-default-background-image
    // MODIFIES: this
    // EFFECTS: draws the JFrame window where the DrawingEditor will operate, and populates the menu buttons
    public void initializeGraphics(int i) {
        desktop = new JDesktopPane() {
            ImageIcon icon = new ImageIcon("./data/banana.jpg");
            Image banana = icon.getImage();
            Image bananaScaled = banana.getScaledInstance(bananaWidth, bananaHeight,
                    Image.SCALE_SMOOTH);

            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(bananaScaled, 100, 30, this);
            }
        };

        desktop.setLayout(new BorderLayout());
        desktop.setBackground(backgroundColor);
        setContentPane(desktop);
        setSize(WIDTH, HEIGHT);
        createButtons();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        createTabs(i);
        setVisible(true);

    }

    // Code used from the AlarmSystem application
    // MODIFIES: this
    // EFFECTS: Centres main app window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }


    // MODIFIES: this
    // EFFECTS: creates tabs that are accessible in the JFrame
    public void createTabs(int i) {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("W",null, statsPane(), null);
        tabbedPane.addTab("O", null, addExercisePane(), null);
        tabbedPane.addTab("R", null, addWorkOutPane(), null);
        tabbedPane.addTab("K", null, exercisesPane(), null);
        tabbedPane.addTab("I", null, workOutsPane(), null);
        tabbedPane.addTab("T", null, playExercisePanel(), null);
        add(tabbedPane,  BorderLayout.CENTER);
        tabbedPane.setBackground(lightColor);
        tabbedPane.setForeground(backgroundColor);
        tabbedPane.setSelectedIndex(i);

    }

    // MODIFIES: this
    // EFFECTS: creates the See Progress tab
    public JPanel statsPane() {
        Color hiddenColor = new Color(184, 216, 216,  visibility);
        JPanel personStatsPanel = new JPanel();
        int reps = data.getPersonStats().getCompletedReps();
        int workouts = data.getPersonStats().getCompletedWorkouts();
        personStatsPanel.setLayout(new BoxLayout(personStatsPanel,
                BoxLayout.Y_AXIS));
        personStatsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        String label = "Your Stats:";
        String labelReps = "You have completed " + String.valueOf(reps) + " repetitions.";
        String labelWorkouts = "You have completed " + String.valueOf(workouts) + " workouts.";


        JTextPane textPane = new JTextPane();
        textPane.setSize(100, 30);

        textPane.setCharacterAttributes(setTextAttributes(), true);

        textPane.setText(label + "\n" + labelReps + "\n" + labelWorkouts);
        personStatsPanel.add(textPane);
        textPane.setBackground(hiddenColor);

        personStatsPanel.add(Box.createVerticalStrut(5));
        personStatsPanel.setBackground(hiddenColor);
        return personStatsPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates text attributes for the panes
    public SimpleAttributeSet setTextAttributes() {
        Color hiddenColor = new Color(184, 216, 216,  min(255, visibility + 50));
        SimpleAttributeSet textSettings = new SimpleAttributeSet();
        StyleConstants.setBackground(textSettings, hiddenColor);
        StyleConstants.setSpaceAbove(textSettings, 5);
        StyleConstants.setSpaceBelow(textSettings, 15);
        StyleConstants.setFontSize(textSettings, 14);
        StyleConstants.setLeftIndent(textSettings, 5);

        return textSettings;

    }

    // MODIFIES: this
    // EFFECTS: creates the Add Exercise tab
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
        spinnerEditor(repsField);
        spinnerEditor(setsField);

        addExercisePanel.setLayout(new BoxLayout(addExercisePanel, BoxLayout.Y_AXIS));
        addExercisePanel.setBackground(backgroundColor);
        addExerciseSetUp(addExercisePanel, name, nameField, description, descriptionField, reps,
                repsField, sets, setsField);
        addExerciseButton = new AddExerciseButton(this, addExercisePanel);
        return addExercisePanel;
    }

    // From StackOverFlow
    // https://stackoverflow.com/questions/22127833/swing-change-the-jspinner-back-and-fore-colors
    // MODIFIES: spinner
    // EFFECTS: sets background of spinners
    public void spinnerEditor(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        int n = editor.getComponentCount();
        for (int i = 0; i < n; i++) {
            Component c = editor.getComponent(i);
            if (c instanceof JTextField) {
                c.setBackground(lightColor);
            }
        }
    }

    // MODIFIES: scroller
    // EFFECTS: sets look of scrollers
    public void scrollEditor(JScrollPane scroller) {
        scroller.getViewport().getView().setBackground(lightColor);
        scroller.getViewport().getView().setForeground(backgroundColor);
        scroller.getViewport().getView().setFont(new Font("Arial",Font.BOLD,14));

    }


    // MODIFIES: this
    // EFFECTS: layout helper for the add exercise pane
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


    // MODIFIES: this
    // EFFECTS: creates the Add Workout tab
    public JPanel addWorkOutPane() {
        JPanel addWorkOutPanel = new JPanel();
        JLabel name = new JLabel("Please enter a name for your work out first:");
        workOutToAddNameField = new JTextField(10);
        addWorkOutPanel.setLayout(new BoxLayout(addWorkOutPanel, BoxLayout.Y_AXIS));
        addWorkOutPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        addWorkOutPanel.add(name);
        addWorkOutPanel.add(workOutToAddNameField);
        addWorkOutButton(addWorkOutPanel);
        addWorkOutPanel.setBackground(backgroundColor);
        return addWorkOutPanel;

    }

    // MODIFIES: panel
    // EFFECTS: adds workout to panel
    public void addWorkOutButton(JPanel panel) {
        AddWorkOutToListButton addWorkOutButton = new AddWorkOutToListButton(this, panel);

    }

    // MODIFIES: this
    // EFFECTS: creates the exercise selection pane in the add workout tab
    public JDialog exerciseSelectorPane(JList<String> exercises) {
        exercisesInJList = exercises;
        exerciseSelector = new JDialog();
        exerciseSelector.setLayout(new FlowLayout());
        exercisesScroll = new JScrollPane(exercises);
        JPanel pane = new JPanel();
        exerciseSelector.add(pane);
        scrollEditor(exercisesScroll);
        exerciseSelector.add(exercisesScroll);
        ExerciseSelectorAddButton addButton = new ExerciseSelectorAddButton(this, pane);
        ExerciseSelectorDoneButton doneButton = new ExerciseSelectorDoneButton(this, pane);
        exerciseSelector.pack();
        exerciseSelector.setLocationRelativeTo(null);

        exerciseSelector.setVisible(true);
        exerciseSelector.setBackground(backgroundColor);

        return exerciseSelector;

    }

    // MODIFIES: this
    // EFFECTS: creates the exercises tab
    public JPanel exercisesPane() {
        ArrayList<Exercise> exercises = data.getExercises();
        JPanel exercisesPane = new JPanel();
        exercisesPane.setLayout(new BoxLayout(exercisesPane, BoxLayout.Y_AXIS));
        exercisesPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        exercisesPane.add(Box.createVerticalStrut(5));

        JTable exerciseTable = getExerciseTable(exercises, exercisesPane);
        JScrollPane tablePane = new JScrollPane(exerciseTable);

        tablePane.getViewport().getView().setBackground(lightColor);

        exercisesPane.add(tablePane, BoxLayout.Y_AXIS);
        return exercisesPane;
    }


    // EFFECTS: creates a table with the given info
    private JTable getExerciseTable(ArrayList<Exercise> exercises, JPanel exercisesPane) {
        Vector<Vector<String>> tableVector = new Vector<Vector<String>>();

        for (Exercise e : exercises) {
            Vector<String> exercise = new Vector<>();
            exercise.add(e.getName());
            exercise.add(e.exerciseDifficulty());
            exercise.add(Integer.toString(e.getReps()));
            exercise.add(Integer.toString(e.getSets()));
            tableVector.add(exercise);
        }
        Vector<String> titles = new Vector<>(4);
        titles.add("Name");
        titles.add("Reps");
        titles.add("Sets");
        titles.add("Difficulty");
        exercisesPane.setBackground(backgroundColor);
        TableModel model = new DefaultTableModel(tableVector, titles);
        JTable exerciseTable = new JTable(model);
        return exerciseTable;
    }

    // MODIFIES: this
    // EFFECTS: creates the workout tab
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
        workOutsPane.setBackground(backgroundColor);
        return workOutsPane;
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons in the main app area
    public void createButtons() {
        JPanel buttonArea = new JPanel();
        QuitButton quitButton;
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(10, 0));
        add(buttonArea, BorderLayout.WEST);
        seeStatsButton = new SeeStatsButton(this, buttonArea);
        startWorkOutButton = new StartWorkOutButton(this, buttonArea);
        addNewExerciseButton = new AddNewExerciseButton(this, buttonArea);
        addWorkOutButton = new AddWorkOutButton(this, buttonArea);
        displayAllExercisesButton = new DisplayAllExercisesButton(this, buttonArea);
        displayAllWorkoutsButton = new DisplayAllWorkoutsButton(this, buttonArea);
        loadButton = new LoadButton(this, buttonArea);
        saveButton = new SaveButton(this, buttonArea);
        quitButton = new QuitButton(this, buttonArea);
        seeStatsButton.activate();

        buttonArea.setBackground(backgroundColor);


    }

    // MODIFIES: this
    // EFFECTS: creates the available workouts to play panel
    public JPanel playExercisePanel() {

        ArrayList<String> allWorkouts = new ArrayList<>();
        for (Object w : data.getWorkouts()) {
            WorkOut w1 = (WorkOut) w;
            String currName = w1.getWorkOutName();
            allWorkouts.add(currName);
        }

        String[] str = new String[allWorkouts.size()];
        namesToPlay = new JList<String>(allWorkouts.toArray(str));
        JPanel playPanel = new JPanel();
        playPanel.setLayout(new BoxLayout(playPanel, BoxLayout.Y_AXIS));
        playPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        pickExercise = new JScrollPane(namesToPlay);
        pickExercise.setOpaque(false);

        pickExercise.getViewport().getView().setBackground(lightColor);
        pickExercise.getViewport().getView().setFont(new Font("Arial",Font.BOLD,14));

        scrollEditor(pickExercise);


//        label.setBackground(lightColor);
//        label.setFont(new Font("Arial",Font.BOLD,14));
//        label.setBorder(new LineBorder(lightColor));
        playPanel.add(pickExercise);
        PlaySelectedButton play = new PlaySelectedButton(this, playPanel);
        playPanel.setBackground(backgroundColor);
        return playPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates the panel where you go through the workout
    public JDialog exercisePlayer(WorkOut workOut) {
        currentWorkout = workOut;
        exercisesTotal = workOut.getExercises().size();
        Exercise first = (Exercise) workOut.getExercises().get(0);
        setsTotal = first.getSets();
        exercisePlayer = new JDialog();
        JPanel layoutManager = new JPanel();
        layoutManager.setLayout(new BoxLayout(layoutManager, BoxLayout.Y_AXIS));
        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        layoutManager.add(playerPanel);

        textPane = getjPanel(workOut);

        JPanel buttonPane = getButtonPane();

        playerPanel.add(textPane);
        buttonPane.setBackground(backgroundColor);
        layoutManager.add(buttonPane);
        exercisePlayer.add(layoutManager);
        exercisePlayer.setSize(550,200);

        exercisePlayer.setVisible(true);
        exercisePlayer.setLocationRelativeTo(null);
        exercisePlayer.setBackground(backgroundColor);
        return exercisePlayer;
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons needed to progress through workout
    private JPanel getButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());

        NextButton nextButton = new NextButton(this, buttonPane);
        QuitWorkOut doneButton = new QuitWorkOut(this, buttonPane);
        return buttonPane;
    }

    // MODIFIES: this
    // EFFECTS: makes JPanel needed in the workout player
    private JPanel getjPanel(WorkOut workOut) {
        JLabel name = new JLabel(workOut.getWorkOutName());
        JLabel firstLine = new JLabel("Starting " + workOut.getWorkOutName() + " in \n");
        JLabel line3 = new JLabel("3...");
        JLabel line2 = new JLabel("2...");
        JLabel line1 = new JLabel("1...");

        JPanel textPane = new JPanel();
        textPane.setLayout(new BoxLayout(textPane, BoxLayout.Y_AXIS));
        textPane.add(name);
        textPane.add(firstLine);
        textPane.add(line3);
        textPane.add(line2);
        textPane.add(line1);
        textPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        textPane.setBackground(backgroundColor);
        return textPane;
    }

    // MODIFIES: this
    // EFFECTS: produces text in JPanel for each exercise set
    public JPanel exerciseHelper(Exercise exercise, int i) {
        JPanel performingPanel = new JPanel();
        performingPanel.setLayout(new BoxLayout(performingPanel, BoxLayout.Y_AXIS));
        String sets = i + " sets done. " + (exercise.getSets() - i) + " sets left.";
        JLabel name = new JLabel("Now performing " + exercise.getName() + ".");
        JLabel difficulty = new JLabel("This exercise is " + exercise.exerciseDifficulty() + "!");
        JLabel reps = new JLabel("Perform " + exercise.getReps() + " reps of " + exercise.getName() + ".");
        JLabel description = new JLabel(exercise.getDescription());
        JLabel setsSent = new JLabel(sets);

        performingPanel.add(name);
        performingPanel.add(difficulty);
        performingPanel.add(reps);
        performingPanel.add(description);
        performingPanel.add(setsSent);

        performingPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        performingPanel.setBackground(backgroundColor);
        return performingPanel;

    }


    // EFFECTS: if activeTool != null, then mouseClicked
    public void handleMouseClicked(MouseEvent e) {
        if (activeButton != null) {
            activeButton.mouseClicked(e, this);
        }
        repaint();
        activeButton.performAction(this);
    }

    // MODIFIES: this
    // EFFECTS: increases setsDone by 1
    public void increaseSetsDone() {
        setsDone += 1;
    }

    // MODIFIES: this
    // EFFECTS: increases exercisesDone by 1
    public void increaseExercisesDone() {
        exercisesDone += 1;
    }

    // MODIFIES: this
    // EFFECTS: resets setsDone
    public void resetSetsDone() {
        setsDone = 0;
    }

    // MODIFIES: this
    // EFFECTS: resets exercisesDone
    public void resetExercisesDone() {
        exercisesDone = 0;
    }

    // MODIFIES: this
    // EFFECTS: resets exercisesTotal to i
    public void resetExercisesTotal(int i) {
        exercisesTotal = i;
    }

    // MODIFIES: this
    // EFFECTS: resets setsTotal to i
    public void resetSetsTotal(int i) {
        setsTotal = i;
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

    // EFFECTS: defines the mouse listener to handle mouse click events
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

    // getters and setters

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

    public JTextField getWorkOutNameToAdd() {
        return workOutToAddNameField;
    }

    public JDialog getExerciseSelector() {
        return exerciseSelector;
    }

    public JScrollPane getScroll() {
        return exercisesScroll;
    }

    public JList getExercisesInJList() {
        return exercisesInJList;
    }

    public Color getBackgroundColor() {
        return backgroundColor;

    }

    public JScrollPane getPickExercise() {
        return pickExercise;
    }

    public JList<String> getNamesToPlay() {
        return namesToPlay;
    }

    public JDialog getExercisePlayer() {
        return exercisePlayer;
    }

    public WorkOut getCurrentWorkout() {
        return currentWorkout;
    }

    public JPanel getTextPane() {
        return textPane;
    }

    public JPanel getPlayerPanel() {
        return playerPanel;
    }

    public int getSetsDone() {
        return setsDone;
    }

    public int getSetsTotal() {
        return setsTotal;
    }

    public int getExercisesTotal() {
        return exercisesTotal;
    }

    public int getExercisesDone() {
        return exercisesDone;
    }

    public void setVisibility(int i) {
        visibility = i;
    }

    public Color getLightColor() {
        return lightColor;
    }




}
