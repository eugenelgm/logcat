package com.eugene.logcatlib;

public class Logcat {

    private static Logger logger;

    private static String _packageName;

    private static final String NEW_LINE = "\n";

    public static void initialize(boolean debug, String packageName) {
        _packageName = packageName;
        if (debug) {
            logger = new DebugLogger(packageName);
        } else {
            logger = new ReleaseLogger();
        }
    }


    public static void debug(String message) {
        logger.debug(message);
    }

    public static void debug(String message, Object... args) {
        logger.debug(message, args);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Object... args) {
        logger.info(message, args);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Object... args) {
        logger.error(message, args);
    }

    public static void error(Throwable t, String message, Object... args) {
        logger.error(t, message, args);
    }

    public static void error(Throwable t) {
        logger.error(t, t.getClass().getName(), t.getMessage());
    }

    public static String getStackTrace(Throwable t) {
        StringBuilder sb = new StringBuilder();

        for (StackTraceElement e : t.getStackTrace()) {
            sb.append(e).append("\n");
        }
        return sb.toString();
    }

    public static void printStackTrace() {
        StringBuilder sb = new StringBuilder("--- ");

        for (StackTraceElement e : Thread.currentThread().getStackTrace()) {
            if (e.getClassName().startsWith(_packageName) && !e.getClassName().equals(Logcat.class.getName())) {
                sb.append(e.toString()).append(NEW_LINE);
            }
        }
        debug(sb.toString());
    }
}
