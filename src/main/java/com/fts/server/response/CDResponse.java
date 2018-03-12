package com.fts.server.response;

/**
 * Created by prasoonanand on 11/03/18.
 */
public class CDResponse extends ServerResponse {
    private final String path;

    public CDResponse(int code, String msg, String path){
        super(code, msg);
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }

    @Override
    public String toString() {
        return getCode() + " " + getMessage() + " Current directory is " + path;
    }
}
