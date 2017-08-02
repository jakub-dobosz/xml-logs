package com.dobosz.jakub.logs;

interface Listenable {
    void addListener(Listener listener);
    void notifyListeners();
}
