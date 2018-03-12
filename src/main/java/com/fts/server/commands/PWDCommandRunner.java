package com.fts.server.commands;

import com.fts.server.response.PWDResponse;
import com.fts.server.response.ServerResponse;
import com.fts.server.CommandRunner;
import com.fts.server.response.DefaultResponses;

/**
 * Created by prasoonanand on 11/03/18.
 */
public class PWDCommandRunner extends CommandRunner {
    public ServerResponse runCommand() {
        return new PWDResponse(DefaultResponses.OK.getCode(), DefaultResponses.OK.getMessage(), workingDirectory);
    }
}
