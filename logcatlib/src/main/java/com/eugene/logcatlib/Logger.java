package com.eugene.logcatlib;

interface Logger {

    void debug(String message);

    void debug(String message, Object... args);

    void info(String message);

    void info(String message, Object... args);

    void error(String message);

    void error(String message, Object... args);

    void error(Throwable t, String message, Object... args);
}