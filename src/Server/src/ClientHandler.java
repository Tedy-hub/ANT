package Server.src;

import com.company.AntExample;
import com.company.ant.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class ClientHandler extends Thread {
    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private ObjectOutputStream writeObject;
    private ObjectInputStream readObject;
    private ArrayList<Socket> clients;
    private ArrayList<ClientHandler> handlers;
  //  private String command = " ";
    public ClientHandler requestor;

    public ClientHandler(ArrayList<Socket> clients,ArrayList<ClientHandler> handlers, Socket socket)
    {
        this.socket = socket;
        try {
            writer = new OutputStreamWriter(socket.getOutputStream());
            writeObject =  new ObjectOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            readObject = new ObjectInputStream(socket.getInputStream());
            this.clients = clients;
            this.handlers = handlers;
        } catch (IOException e) {
            e.printStackTrace();
            clients.remove(socket);
            handlers.remove(this);
            try { socket.close(); }
            catch (IOException ex) { ex.printStackTrace(); }
        }

    }

    @Override
    public void run() {
        while(true) {
            try {
                String request = reader.readLine();

//                if(command.equals("giveObjects")){
//                    command = " ";
//                    sendMessage();
//                }

                if(request.equals("Exit")){
                    System.out.println("Socket " + socket.getPort() + " exits...");
                    clients.remove(socket);
                    handlers.remove(this);
                    writer.close();
                    reader.close();
                    socket.close();
                    break;
                }
                if(request.equals("getObjects")){
                    System.out.println("пришла команда для запроса объекта");
                    Random randNum = new Random();
                    int num = randNum.nextInt(handlers.size());
                    while ((num-1) % 2 == 0 || this == handlers.get(num)){
                        num = randNum.nextInt(handlers.size());
                    }
                    handlers.get(num).requestor = this;
                    ClientHandler handl = handlers.get(num);
                   // handl.setCommand("giveObjects");
                    handl.sendMessage();
                }

                if(request.equals("wantToSend")){
                    Vector<Ant> ants = receiveObj();
                    //Vector<Ant> ants = (Vector<Ant>) readObject.readObject();
                    System.out.println("на сервер пришло" + ants.toString());
                    requestor.sendAnts(ants);
                }

                if(request.equals("userList")){
                    writer.write(clients.size() / 2);
                    for (int i = 0; i < clients.size(); i+=2) {
                        writer.write(clients.get(i).getInetAddress().getHostAddress() + ' ' + clients.get(i).getPort() + '\n');
                    }
                    writer.flush();
                }

            } catch (Exception e) {
                e.printStackTrace();
                clients.remove(socket);
                handlers.remove(this);
                try { socket.close(); }
                catch (IOException ex) {}
                break;
            }

        }
    }

    public void sendMessage(){
        try {
            System.out.println("Шлем запрос объектов случайному клиенту");
            writer.write("giveMeAnObject\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendAnts(Vector<Ant> ants) {
        try {
            writer.write("ants\n");
            writer.write(ants.size());
            writer.flush();

            for(int i = 0; i < ants.size(); i++){
                Ant ant = ants.get(i);
                String params = ant.getId() + "," + ant.getName()+
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

                writer.write(params + "\n");
                writer.flush();
            }

            System.out.println("Send objects!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Vector<Ant> receiveObj(){
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
                ants.add(ant);
            }
            System.out.println("RECEIVED!\n");
            return ants;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public void setCommand(String command){
//        this.command = command;
//    }
}
