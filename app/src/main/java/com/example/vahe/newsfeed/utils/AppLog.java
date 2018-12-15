package com.example.vahe.newsfeed.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vahe.newsfeed.BuildConfig;


public class AppLog {
    private static final boolean isDebug = BuildConfig.DEBUG;

    public static int v(String tag, String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.v(tag, st + ": " + msg);
        }
        return 0;
    }

    public static int v(String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.v(st, msg);
        }
        return 0;
    }

    public static int d(String tag, String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.d(tag, st + ": " + msg);
        }
        return 0;
    }

    public static int d(String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.d(st, msg);
        }
        return 0;
    }

    public static int i(String tag, String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.i(tag, st + ": " + msg);
        }
        return 0;
    }

    public static int i(String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.i(st, msg);
        }
        return 0;
    }

    public static int w(String tag, String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.w(tag, st + ": " + msg);
        }
        return 0;
    }

    public static int w(String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.w(st, msg);
        }
        return 0;
    }

    public static int e(String tag, String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.e(tag, st + ": " + msg);
        }
        return 0;
    }

    public static int e(String msg) {
        if (isDebug) {
            final StackTraceElement stackTrace = new Exception().getStackTrace()[1];
            String st = getStackTraceInfo(stackTrace);
            return Log.e(st, msg);
        }
        return 0;
    }

    @NonNull
    private static String getStackTraceInfo(StackTraceElement stackTrace) {
        String fileName = stackTrace.getFileName();
        if (fileName == null) {
            fileName = "";  // It is necessary if you want to use proguard obfuscation.
        }
        return "(" + fileName + ":" + stackTrace.getLineNumber() + ")";
    }
}
