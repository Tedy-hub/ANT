package com.company;

import com.company.ant.Ant;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Client extends Thread {
    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private ObjectOutputStream writeObj;
    private ObjectInputStream readObj;
    private String command = " ";
    private AntExample scr;

    public Client(AntExample scr){
        try {
            socket = new Socket("127.0.0.1", 4000);
            writer = new OutputStreamWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeObj = new ObjectOutputStream(socket.getOutputStream());
            readObj = new ObjectInputStream(socket.getInputStream());
            this.scr = scr;
        }
        catch(IOException e) {e.printStackTrace();}
    }

    @Override
    public void run() {
        while(true) {
            try {
                getClients();

                if (reader != null && reader.ready()) {
                    String request = reader.readLine();
                    if (request.equals("giveMeAnObject")) {
                        System.out.println("Клиент получил запрос на отправку рандомных муровьев");
                        Vector<Ant> ants = AntExample.list;
                        writer.write("wantToSend\n");
                        writer.flush();
                        writeObj.writeObject(ants);
                        writeObj.flush();
                        System.out.println("Клиент отправил рандомных муровьев");
                    }
                    if (request.equals("ants")) {
                        System.out.println("GOT ANTS!\n");
                        System.out.println(AntExample.list.toString());
                        try {
                            Vector<Ant> ants = (Vector<Ant>) readObj.readObject();
                            System.out.println(ants.toString());
                            AntExample.list.addAll(ants);
                            System.out.println(AntExample.list.toString());


                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if(command.equals("getObjects")){
                    getObjects();
                }

                Thread.sleep(200);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void readMessage(){
        try {
            if(reader.ready()){
                String request = reader.readLine();
                if (request.equals("giveMeAnObject")) {
                    System.out.println("hhhhhhh");
                    System.out.println("Клиент получил запрос на отправку рандомных муровьев");
                    Vector<Ant> ants = AntExample.list;
                    writer.write("wantToSend\n");
                    writer.flush();
                    writeObj.writeObject(ants);
                    System.out.println("Клиент отправил рандомных муровьев");
                }
                if(request.equals("ants")){
                    System.out.println("GOT ANTS!\n");
                    System.out.println(AntExample.list.toString());
                    try {
                        Vector<Ant> ants = (Vector<Ant>) readObj.readObject();
                        System.out.println(ants.toString());
                        AntExample.list.addAll(ants);
                        System.out.println(AntExample.list.toString());


                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getClients() throws IOException {

        writer.write("userList\n");
        writer.flush();
        AntExample.serverClients.clear();
        int usersAmount = reader.read();
        for (int i = 0; i < usersAmount; i++) {
           AntExample.serverClients.add(reader.readLine());
        }
        scr.setText(AntExample.serverClients);
    }

    public void getObjects(){
        try {
            writer.write("getObjects\n");
            writer.flush();
            System.out.println("отправили запрос на сервер");
        } catch (IOException e) {
            e.printStackTrace();
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

    public void setCommand(String command) {
        this.command = command;
    }
}
