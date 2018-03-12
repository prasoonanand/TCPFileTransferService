package com.fts.server.commands;

import com.fts.server.CommandRunner;
import com.fts.server.response.DefaultResponses;
import com.fts.server.response.DIRResponse;
import com.fts.server.response.ServerResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasoonanand on 10/03/18.
 */
public class DIRCommandRunner extends CommandRunner {
    private String location;

    public DIRCommandRunner(String location) {
        this.location = location;
    }

    @Override
    public ServerResponse runCommand() {
        if(!isAbsolutePath(location)){
            location = workingDirectory+SEPARATOR+location;
        }
        List<String> fileList = new ArrayList<>();
        try {
            Files.list(new File(location).toPath())
                    .forEach(path -> {
                        fileList.add(path.toFile().getName());
                    });
        } catch (IOException e) {
            return new ServerResponse(DefaultResponses.SERVER_ERROR.getCode(),
                    DefaultResponses.SERVER_ERROR.getMessage());
        }
        return new DIRResponse(DefaultResponses.OK.getCode(), DefaultResponses.OK.getMessage(), fileList);
    }
}
