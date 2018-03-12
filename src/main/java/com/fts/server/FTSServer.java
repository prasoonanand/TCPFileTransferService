package com.fts.server;

import java.net.ServerSocket;
import java.net.Socket;

public class FTSServer {
    private static final int PORT = 6655;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSock = new ServerSocket(PORT);
        while (true) {
            System.out.println("Waiting...");
            Socket sock = serverSock.accept();
            new FTSThread(sock).start();
        }
    }


}