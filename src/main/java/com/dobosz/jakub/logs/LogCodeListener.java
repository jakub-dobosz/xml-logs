package com.dobosz.jakub.logs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class LogCodeListener implements Listener {
    private static final int DISPLAY_LIMIT = 5;
    private Listenable listenable;
    private Map<Integer, Integer> logCodeMap;

    LogCodeListener(Listenable listenable) {
        this.listenable = listenable;
        this.logCodeMap = new HashMap<>();
        listenable.addListener(this);
    }

    public void update(Log log) {
        logCodeMap.merge(log.getCode(), 1, (a, b) -> a + b);
    }

    public void display() {
        displayTheMostCommonCodes(DISPLAY_LIMIT);
        System.out.println();
    }

    private void displayTheMostCommonCodes(int displayLimit) {
        logCodeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(displayLimit)
                .forEach(this::displayEntry);
    }

    private void displayEntry(Map.Entry<Integer, Integer> entry) {
        System.out.println("Code: " + entry.getKey() + " - " + entry.getValue() + " occurrences");
    }
}
