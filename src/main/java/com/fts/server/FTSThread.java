package com.fts.server;

import com.fts.server.response.DefaultResponses;
import com.fts.server.response.ServerResponse;

import java.io.*;
import java.net.Socket;

/**
 * Created by prasoonanand on 11/03/18.
 */
public class FTSThread extends Thread {
    private final Socket sock;

    public FTSThread(Socket socket){
        sock = socket;
    }

    public void run(){
        System.out.println("Accepted connection : " + sock);
        try(PrintWriter os = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()))){
            os.print(new ServerResponse(DefaultResponses.SUCCESS.getCode(),
                DefaultResponses.SUCCESS.getMessage()) + "\r\n");
            os.flush();
            receiveCommand(br, os);
            os.print(new ServerResponse(DefaultResponses.GOODBYE.getCode(),
                    DefaultResponses.GOODBYE.getMessage()) + "\r\n");
            os.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread Dying : " +  sock);
    }

    private void receiveCommand(BufferedReader br, PrintWriter os) throws IOException {
        try{
            while (true) {
                String line = br.readLine();
                if(line != null){
                    if(line.equalsIgnoreCase(SupportedCommands.QUIT)){
                        break;
                    }
                    ServerResponse sr = CommandExecutor.executeCommand(line);
                    os.print(sr + "\r\n");
                    os.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            os.print(new ServerResponse(DefaultResponses.SERVER_ERROR.getCode(),
                    DefaultResponses.SERVER_ERROR.getMessage())+"\r\n");
        }
    }
}
