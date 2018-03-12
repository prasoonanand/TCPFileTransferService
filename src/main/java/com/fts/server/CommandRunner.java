package com.fts.server;

import com.fts.server.response.ServerResponse;

import java.io.File;

/**
 * Created by prasoonanand on 11/03/18.
 */
public abstract class CommandRunner {
    protected static String workingDirectory = System.getProperty("user.dir");
    protected static final String SEPARATOR = "/";
    public abstract ServerResponse runCommand();

    protected boolean isAbsolutePath(String path){
        return new File(path).isAbsolute();
    }
}
