package com.wmpn.context;

public class BaseContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setCurrentFileName(String fileName) {
        threadLocal.set(fileName);
    }



    public static String getCurrentFileName() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
