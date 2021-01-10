package com.eugene.logcatlib;

class ReleaseLogger implements Logger {

    @Override
    public void debug(String message) {}

    @Override
    public void debug(String message, Object... args) {}

    @Override
    public void info(String message) {}

    @Override
    public void info(String message, Object... args) {}

    @Override
    public void error(String message) {}

    @Override
    public void error(String message, Object... args) {}

    @Override
    public void error(Throwable t, String message, Object... args) {}
}
