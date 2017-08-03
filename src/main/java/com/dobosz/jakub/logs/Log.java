package com.dobosz.jakub.logs;

class Log {
    private Level level;
    private String file;
    private int code;

    Level getLevel() {
        return level;
    }

    void setLevel(Level level) {
        this.level = level;
    }

    String getFile() {
        return file;
    }

    void setFile(String file) {
        this.file = file;
    }

    int getCode() {
        return code;
    }

    void setCode(int code) {
        this.code = code;
    }
}
