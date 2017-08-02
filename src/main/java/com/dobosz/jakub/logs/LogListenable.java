package com.dobosz.jakub.logs;

import java.util.ArrayList;
import java.util.List;

class LogListenable implements Listenable {
    private List<Listener> listeners;
    private Log log;

    LogListenable() {
        listeners = new ArrayList<>();
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void notifyListeners() {
        listeners.parallelStream().forEach(l -> l.update(log));
    }

    void setLog(Log log) {
        this.log = log;
        notifyListeners();
    }
}
