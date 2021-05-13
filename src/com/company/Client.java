package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;

    public Client(){
        try {
            socket = new Socket("127.0.0.1", 4000);
            writer = new OutputStreamWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException e) {e.printStackTrace();}
    }

    public ArrayList<String> getClients() throws IOException {
        ArrayList<String> users = new ArrayList<>();

        writer.write("userList\n");
        writer.flush();

        int usersAmount = reader.read();
        for (int i = 0; i < usersAmount; i++) {
            users.add(reader.readLine());
        }

        return users;
    }

    public void close(){
        try {
            writer.write("Exit\n");
            writer.flush();
            socket.close();
            reader.close();
            writer.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void shutdown(){
        try {
            socket.close();
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
