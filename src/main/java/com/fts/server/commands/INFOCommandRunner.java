package com.fts.server.commands;

import com.fts.server.bean.FileObject;
import com.fts.server.response.INFOResponse;
import com.fts.server.response.ServerResponse;
import com.fts.server.CommandRunner;
import com.fts.server.response.DefaultResponses;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by prasoonanand on 11/03/18.
 */
public class INFOCommandRunner extends CommandRunner {
    private String argument;

    public INFOCommandRunner(String arg) {
        argument = arg;
    }

    public ServerResponse runCommand() {
        if(!isAbsolutePath(argument)){
            argument = workingDirectory+SEPARATOR+argument;
        }
        File newFile = new File(argument);
        if(newFile.exists()){
            String type = newFile.isDirectory()? "Directory":"File";
            String size = newFile.length()/1024*1024+"";
            long time = newFile.lastModified();
            try {
                BasicFileAttributes attr = Files.readAttributes(newFile.toPath(), BasicFileAttributes.class);
                time = attr.creationTime().toMillis();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LocalDateTime date = LocalDateTime.ofEpochSecond(time/1000, (int)time%1000, ZoneOffset.UTC);
            String dateModified = date.getDayOfMonth() + "-" + date.getMonth().getValue() + "-" + date.getYear();
            FileObject fileObject = new FileObject(newFile.getName(), type, size, dateModified
                    );
            return new INFOResponse(DefaultResponses.OK.getCode(),DefaultResponses.OK.getMessage(),fileObject);
        }else{
            return new ServerResponse(DefaultResponses.FILE_NOT_FOUND.getCode(),
                    DefaultResponses.FILE_NOT_FOUND.getMessage());
        }
    }
}
