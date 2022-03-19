package com.udacity.jwdnd.course1.cloudstorage.util;

import org.javatuples.Pair;

import static com.udacity.jwdnd.course1.cloudstorage.util.Constants.*;

public class SuccessBuilder {
    public static Pair<String, String> buildMessage(OperationStatus status, int type) {
        String message;
        String key;
        switch (status) {
            case SUCCESS_DELETE:
                message = buildDeleteSuccess(type);
                key = SUCCESS_KEY;
                break;
            case SUCCESS_EDIT:
                message = buildEditSuccess(type);
                key = SUCCESS_KEY;
                break;
            case SUCCESS_INSERT:
                message = buildInsertSuccess(type);
                key = SUCCESS_KEY;
                break;
            case ERROR_DELETE:
                message = buildDeleteError(type);
                key = ERROR_KEY;
                break;
            case ERROR_EDIT:
                message = buildEditError(type);
                key = ERROR_KEY;
                break;
            case ERROR_INSERT:
                message = buildInsertError(type);
                key = ERROR_KEY;
                break;
            default:
                message = "Error!";
                key = ERROR_KEY;
                break;
        }
        return new Pair(key, message);
    }

    private static String typeToString(int type) {
        switch (type) {
            case FILES:
                return " file.";
            case NOTES:
                return " note.";
            case CREDENTIALS:
                return " credentials";
            default:
                return "!";
        }
    }

    private static String buildDeleteSuccess(int type) {
        return "Successfully deleted" + typeToString(type);
    }

    private static String buildEditSuccess(int type) {
        return "Successfully edited" + typeToString(type);
    }

    private static String buildInsertSuccess(int type) {
        return "Successfully added" + typeToString(type);
    }

    private static String buildDeleteError(int type) {
        return "Error deleting" + typeToString(type);
    }

    private static String buildEditError(int type) {
        return "Error editing" + typeToString(type);
    }

    private static String buildInsertError(int type) {
        return "Error adding" + typeToString(type);
    }
}
