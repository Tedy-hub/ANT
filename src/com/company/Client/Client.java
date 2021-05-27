package com.company.Client;

import com.company.AntExample;
import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Client extends Thread {
    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private ObjectOutputStream writeObj;
    private ObjectInputStream readObj;
    private String command = " ";
    private AntExample scr;
    private ListUpdater clientListUpdater;

    public Client(AntExample scr){
        try {
            socket = new Socket("127.0.0.1", 4000);
            writer = new OutputStreamWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeObj = new ObjectOutputStream(socket.getOutputStream());
            readObj = new ObjectInputStream(socket.getInputStream());
            this.scr = scr;
            clientListUpdater = new ListUpdater(scr);
            clientListUpdater.start();
        }
        catch(IOException e) {e.printStackTrace();}
    }

    @Override
    public void run() {
        while(true) {
            try {

                readMessage();

                if(command.equals("getObjects")){
                    getObjects();
                    command = " ";
                }
                if(command.equals("close")){
                    close();
                    command = " ";
                    break;
                }

                Thread.sleep(200);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
                close();
                break;
            }
        }
    }

    private void readMessage() throws IOException{
            if(reader != null && reader.ready()){
                String request = reader.readLine();
                if (request.equals("giveMeAnObject")) {
                    System.out.println("Клиент получил запрос на отправку рандомных муровьев");

                    Random rand = new Random();
                    int max = AntExample.list.size();
                    int amount = rand.nextInt(max+1);

                    Vector<Ant> ants = new Vector<>();
                    for(int i = 0; i < amount; i++)
                        ants.add(AntExample.list.get(rand.nextInt(max)));

                    writer.write("wantToSend\n");
                    writer.flush();
                    sendObj(ants);
                   // writeObj.writeObject(ants);
                    //writeObj.flush();
                    System.out.println("Клиент отправил рандомных муравьев");
                }

                if(request.equals("ants")){
                    System.out.println("GOT ANTS!\n");
                    receiveObj();
                }
            }
    }

    private void receiveObj(){
        int size = 0;
        try {
            size = reader.read();
            Vector<Ant> ants = new Vector<>();
            for(int i = 0; i < size; i++){
                Ant ant;
                String paramStr = reader.readLine();
                String[] params = paramStr.split(",");

                if(params[1].equals("Warrior Ant")) ant = new WarriorAnt();
                else ant = new WorkerAnt();

                ant.setName(params[1]);
                ant.setSize(Integer.parseInt(params[2]));
                ant.setPosX(Integer.parseInt(params[3]));
                ant.setPosY(Integer.parseInt(params[4]));
                ant.setPositionBornX(Integer.parseInt(params[5]));
                ant.setPositionBornY(Integer.parseInt(params[6]));

                if(params[1].equals("Warrior Ant")){
                    ((WarriorAnt)ant).setCenter_x(Integer.parseInt(params[7]));
                    ((WarriorAnt)ant).setCenter_y(Integer.parseInt(params[8]));
                    ant.setTimeLive(AntExample.TimeLivingWarrior);
                }
                else{
                    ((WorkerAnt)ant).setDeltaX(Integer.parseInt(params[7]));
                    ((WorkerAnt)ant).setDeltaY(Integer.parseInt(params[8]));
                    ant.setTimeLive(AntExample.TimeLivingWorker);
                }
                AntExample.idList.add(ant.getId());
                AntExample.BornList.put(ant.getId(), AntExample.TimeSimulation);
                AntExample.list.add(ant);
                ant.draw(scr.getCanvas());
            }
            System.out.println("RECEIVED!\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendObj(Vector<Ant> ants){
        try {
            writer.write(ants.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < ants.size(); i++){
            Ant ant = ants.get(i);
            String params = ant.getId() + "," + ant.getName() +
                    "," + ant.getSize() + "," + ant.getPosX() +
                    "," + ant.getPosY() + "," + ant.getPositionBornX() +
                    "," + ant.getPositionBornY() + ",";

            if(ant.getName().equals("Warrior Ant")){
                params += ((WarriorAnt)ant).getCenter_x() +
                        "," + ((WarriorAnt)ant).getCenter_y() +
                        "," + ((WarriorAnt)ant).getAngle_rad();
            }
            else{
                params += ((WorkerAnt)ant).getDeltaX() +
                        "," + ((WorkerAnt)ant).getDeltaY();
            }

            try {
                writer.write(params + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getObjects(){
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
                clientListUpdater.close();
                writer.write("Exit\n");
                writer.flush();
                Thread.sleep(80);

                socket.close();
                reader.close();
                writer.close();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
