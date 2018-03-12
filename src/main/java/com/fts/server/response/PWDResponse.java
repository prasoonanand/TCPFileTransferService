package com.fts.server.response;

/**
 * Created by prasoonanand on 11/03/18.
 */
public class PWDResponse extends ServerResponse{

    private final String pwd;

    public PWDResponse(int code, String msg, String pwd){
        super(code, msg);
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return pwd + "|" + getCode() + " " + getMessage();
    }
}
