package com.dobosz.jakub.logs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class LogSourceFileListener implements Listener {
    private static final int DISPLAY_LIMIT = 5;
    private Listenable listenable;
    private Map<String, Integer> logSourceMap;

    LogSourceFileListener(Listenable listenable) {
        this.listenable = listenable;
        this.logSourceMap = new HashMap<>();
        listenable.addListener(this);
    }

    public void update(Log log) {
        logSourceMap.merge(log.getFile(), 1, (a, b) -> a + b);
    }

    public void display() {
        displayTheMostCommonSourceFiles(DISPLAY_LIMIT);
        System.out.println();
    }

    private void displayTheMostCommonSourceFiles(int displayLimit) {
        logSourceMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(displayLimit)
                .forEach(this::displayEntry);
    }

    private void displayEntry(Map.Entry<String, Integer> entry) {
        System.out.println("File: " + entry.getKey() + " - " + entry.getValue() + " occurrences");
    }
}
