package com.eugene.logcatlib;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DebugLogger implements Logger {

    private static final int CALL_STACK_INDEX = 3;
    private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");

    private String packageName;

    DebugLogger(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public void debug(String message) {
        Log.d(getTag(), message);
    }

    @Override
    public void debug(String message, Object... args) {
        Log.d(getTag(), String.format(message, args));
    }

    @Override
    public void info(String message) {
        Log.i(getTag(), message);
    }

    @Override
    public void info(String message, Object... args) {
        Log.i(getTag(), String.format(message, args));
    }

    @Override
    public void error(String message) {
        Log.e(getTag(), message);
    }

    @Override
    public void error(String message, Object... args) {
        Log.e(getTag(), message);
    }

    @Override
    public void error(Throwable t, String message, Object... args) {
        Log.e(getTag(), message, t);
    }

    private String getTag() {
        String threadName = String.format("[%s]", Thread.currentThread().getName());
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int count = stackTrace.length;
        if (count <= CALL_STACK_INDEX) {
            throw new IllegalStateException(
                    "Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }
        for (int i = CALL_STACK_INDEX; i < count; i++) {
            StackTraceElement el = stackTrace[i];
            String clsName = el.getClassName();
            if (clsName.startsWith(packageName)) {
                return threadName + createStackElementTag(el);
            }
        }
        return threadName + createStackElementTag(stackTrace[CALL_STACK_INDEX]);
    }

    private String createStackElementTag(StackTraceElement element) {
        String tag = element.getClassName();
        Matcher m = ANONYMOUS_CLASS.matcher(tag);
        if (m.find()) {
            tag = m.replaceAll("");
        }
        return tag.substring(tag.lastIndexOf('.') + 1) + "(" + element.getLineNumber() + ")";
    }
}
