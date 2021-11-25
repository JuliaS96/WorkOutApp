package ui;

import java.io.File;
import java.io.FileWriter;

import model.EventLog;
import model.Event;

import java.io.IOException;

public class LogPrinter {

    /**
     * Constructor creates logprinter.
     */
    public LogPrinter() {

    }

    // EFFECTS: prints event log el
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
            System.out.println("\n");
        }

    }

}

