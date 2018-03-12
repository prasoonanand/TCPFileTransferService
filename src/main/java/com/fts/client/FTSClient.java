package com.fts.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by prasoonanand on 10/03/18.
 */
public class FTSClient {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting FTS Client...");
        Socket clientSocket = new Socket("localhost", 6655);
        PrintWriter os = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(is.readLine());
        while(true) {
            System.out.println("Please Enter the command: ");
            String line = br.readLine();
            os.print(line + "\r\n");
            os.flush();
            System.out.println(is.readLine().replaceAll("\\|","\n"));
            if(line.equalsIgnoreCase("QUIT")){
                os.close();
                is.close();
                br.close();
                clientSocket.close();
                break;
            }
        }
    }
}
