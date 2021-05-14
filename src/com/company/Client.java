package com.company;

import com.company.ant.Ant;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Client {
    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private ObjectOutputStream writeObj;

    public Client(){
        try {
            socket = new Socket("127.0.0.1", 4000);
            writer = new OutputStreamWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeObj = new ObjectOutputStream(socket.getOutputStream());
        }
        catch(IOException e) {e.printStackTrace();}
    }

    public void readMessage(){
        try {
            if(reader.readLine().equals("giveMeAnObject")){
                System.out.println("hhhhhhh");
                System.out.println("Клиент получил запрос на отправку рандомных муровьев");
                Vector<Ant> ants = AntExample.list;
                writer.write("wantToSend\n");
                writer.flush();
                writeObj.writeObject(ants);
                System.out.println("Клиент отправил рандомных муровьев");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public boolean getObject(){
        try {
            writer.write("getObjects\n");
            writer.flush();
            System.out.println("отправили запрос на сервер");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
