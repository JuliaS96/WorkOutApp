package ui;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

// initialized the WorkOut App
public class Main {
    public static void main(String[] args) {
        try {
            new WorkOutAppUI();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

    }
}

