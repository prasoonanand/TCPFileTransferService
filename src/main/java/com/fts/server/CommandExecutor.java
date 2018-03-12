package com.fts.server;

import com.fts.server.response.DefaultResponses;
import com.fts.server.commands.CDCommandRunner;
import com.fts.server.commands.DIRCommandRunner;
import com.fts.server.response.ServerResponse;
import com.fts.server.commands.INFOCommandRunner;
import com.fts.server.commands.PWDCommandRunner;

/**
 * Created by prasoonanand on 10/03/18.
 */
public class CommandExecutor {
    private static final byte CLIENT_ARGUMENTS_SUPPORTED = 1;
    private static final byte COMMAND_LOCATION = 0;
    private static final byte ARGUMENT_LOCATION = 1;

    private static String[] commandArgument;

    public static ServerResponse executeCommand(String line) {
        commandArgument = line.split(" ");
        int length = commandArgument.length;
        ServerResponse serverResponse = new ServerResponse(DefaultResponses.ARGUMENT_SUPPORT_ERROR.getCode(),
                DefaultResponses.ARGUMENT_SUPPORT_ERROR.getMessage());
        switch (commandArgument[COMMAND_LOCATION].toUpperCase()){
            case SupportedCommands.DIR :
                if(checkArgument(length)){
                    serverResponse = new DIRCommandRunner(commandArgument[ARGUMENT_LOCATION]).runCommand();
                }
                break;
            case SupportedCommands.INFO :
                if(checkArgument(length)){
                    serverResponse = new INFOCommandRunner(commandArgument[ARGUMENT_LOCATION]).runCommand();
                }
                break;
            case SupportedCommands.PWD :
                serverResponse = new PWDCommandRunner().runCommand();
                break;
            case SupportedCommands.CD :
                if(checkArgument(length)) {
                    serverResponse = new CDCommandRunner(commandArgument[ARGUMENT_LOCATION])
                            .runCommand();
                }
                break;
            default:
                serverResponse = new ServerResponse(DefaultResponses.UNKNOWN_COMAND.getCode(),
                        DefaultResponses.UNKNOWN_COMAND.getMessage());
        }
        return serverResponse;
    }

    private static boolean checkArgument(int length){
        return length == CLIENT_ARGUMENTS_SUPPORTED + 1;
    }
}
