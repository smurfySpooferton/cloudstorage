package com.udacity.jwdnd.course1.cloudstorage.util;

public class Constants {
    public static final int FILES = 0;
    public static final int NOTES = 1;
    public static final int CREDENTIALS = 2;
    public static final String SUCCESS_KEY = "appSuccess";
    public static final String ERROR_KEY = "appError";
    public static final String TAB_KEY = "tab";
    public static final String FILES_KEY = "files";
    public static final String NOTES_KEY = "notes";
    public static final String CREDENTIALS_KEY = "credentials";

    public enum OperationStatus {
        SUCCESS_INSERT, SUCCESS_EDIT, SUCCESS_DELETE, ERROR_INSERT, ERROR_EDIT, ERROR_DELETE, ERROR_GET
    }
}
