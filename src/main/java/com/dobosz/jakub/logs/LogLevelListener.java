package com.dobosz.jakub.logs;

import java.util.EnumMap;
import java.util.Map;

class LogLevelListener implements Listener {
    private Listenable listenable;
    private Map<Level, Integer> logLevelMap;

    LogLevelListener(Listenable listenable) {
        this.listenable = listenable;
        this.listenable.addListener(this);
        this.logLevelMap = new EnumMap<>(Level.class);
    }

    public void update(Log log) {
        logLevelMap.merge(log.getLevel(), 1, (a, b) -> a + b);
    }

    public void display() {
        logLevelMap.forEach((key, value) -> System.out.println("Level: " + key + " - " + value + " occurrences"));
        System.out.println();
    }
}
