package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

public class ImplController implements Controller {
    private static List<String> history = new LinkedList<>();
    private String currentString;
    public final void setNextString(final String stringa) {
        currentString = stringa;
    }

    public final String getNextString(final int i) {
        return currentString;
    }

    public final List<String> getHistory() {
        return history;
    }

    public final void printCurrentString() {
        if (currentString != null) {
            System.out.println(currentString);
            history.add(currentString);
        } else {
            throw new IllegalStateException();
        }
    }
}
