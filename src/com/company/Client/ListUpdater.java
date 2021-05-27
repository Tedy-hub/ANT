package com.company.Client;

import com.company.AntExample;
import com.company.ant.Ant;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ListUpdater extends Thread{

    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private ObjectOutputStream writeObj;
    private ObjectInputStream readObj;
    ArrayList<String> clients;

    private AntExample scr;

    ListUpdater(AntExample scr){
        try {
            socket = new Socket("127.0.0.1", 4000);
            writer = new OutputStreamWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeObj = new ObjectOutputStream(socket.getOutputStream());
            readObj = new ObjectInputStream(socket.getInputStream());
            clients = AntExample.serverClients;
            this.scr = scr;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                writer.write("userList\n");
                writer.flush();

                if(clients != null) clients.clear();
                int usersAmount = reader.read();
                for (int i = 0; i < usersAmount; i++) {
                    clients.add(reader.readLine());
                }

                scr.setText(clients);

                Thread.sleep(500);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                close();
                break;
            }
        }
    }

    public void close(){
        try {
            if(writer != null) {
                writer.write("Exit\n");
                writer.flush();

                socket.close();
                reader.close();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
