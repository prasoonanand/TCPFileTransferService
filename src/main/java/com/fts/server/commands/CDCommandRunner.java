package com.fts.server.commands;

import com.fts.server.response.CDResponse;
import com.fts.server.response.ServerResponse;
import com.fts.server.CommandRunner;
import com.fts.server.response.DefaultResponses;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasoonanand on 11/03/18.
 */
public class CDCommandRunner extends CommandRunner {
    private final String directory;

    public CDCommandRunner(String directory) {
        this.directory = directory;
    }

    @Override
    public ServerResponse runCommand() {
        String path = normalizePath(directory);
        if(path != null){
            workingDirectory = path;
            return new CDResponse(DefaultResponses.OK.getCode(),
                    DefaultResponses.OK.getMessage(), workingDirectory);
        }else{
            return new ServerResponse(DefaultResponses.FOLDER_NOT_EXIST.getCode(),
                    DefaultResponses.FOLDER_NOT_EXIST.getMessage());
        }
    }

    private String normalizePath(String path) {
        if(!isAbsolutePath(path)){
            path = workingDirectory + SEPARATOR + path;
        }
        List<String> parts = new ArrayList<>();
        for (String part : path.split(SEPARATOR)) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (parts.isEmpty()) {
                    continue;
                } else {
                    if (!parts.get(parts.size() - 1).equals("..")) {
                        parts.remove(parts.size() - 1);
                        continue;
                    }
                }
            }
            parts.add(part);
        }
        String prefix = System.getProperty("os.name").toLowerCase().indexOf("win") >= 0 ? "" : SEPARATOR;
        String newPath = prefix + String.join(SEPARATOR, parts);
        File file =  new File(newPath);
        boolean isFolder = file.exists() && file.isDirectory();
        return isFolder?newPath:null;
    }
}
