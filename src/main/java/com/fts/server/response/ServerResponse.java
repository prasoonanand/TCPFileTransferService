package com.fts.server.response;

import java.io.Serializable;

/**
 * Created by prasoonanand on 10/03/18.
 */
public class ServerResponse implements Serializable{

    public ServerResponse(int code,String message){
        this.message = message;
        this.code = code;
    }

    private final String message;
    private final int code;

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString() {
        return code + "  " + message;
    }
}
