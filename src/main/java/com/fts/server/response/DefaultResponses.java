package com.fts.server.response;

/**
 * Created by prasoonanand on 10/03/18.
 */
public enum DefaultResponses {
    SERVER_ERROR(503, "Server Error"),
    ARGUMENT_SUPPORT_ERROR(500, "Invalid command. Missing parameters"),
    SUCCESS(200, "Welcome to File Information Service"),
    OK(200, "OK"),
    GOODBYE(200, "GOODBYE"),
    FILE_NOT_FOUND(501, "File not found"), FOLDER_NOT_EXIST(501, "Location does not exist"),
    UNKNOWN_COMAND(503, "Unknown Command");

    private final int code;
    private final String message;

    DefaultResponses(int code, String message){
        this.message = message;
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
