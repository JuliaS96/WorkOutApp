package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new WorkOutAppUI();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
