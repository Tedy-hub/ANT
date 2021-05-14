package Server.src;

import com.company.ant.Ant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private OutputStreamWriter writer;
    private ObjectInputStream readObject;
    private ArrayList<Socket> clients;
    private ArrayList<ClientHandler> handlers;

    public ClientHandler(ArrayList<Socket> clients,ArrayList<ClientHandler> handlers)
    {
        this.socket = clients.get(clients.size()-1);
        try {
            writer = new OutputStreamWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            readObject = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clients = clients;
        this.handlers = handlers;
    }

        public void sendMessage(){
            try {
                writer.write("giveMeAnObject\n");
                writer.flush();
                System.out.println("Шлем команду следующему рандомному клиенту");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void run() {
        while(true) {
            try {
                String request = reader.readLine();
                //System.out.println("got + " + request);

                if(request.equals("Exit")){
                    System.out.println("Socket " + socket.getPort() + " exits...");
                    clients.remove(socket);
                    writer.close();
                    reader.close();
                    socket.close();
                    break;
                }
                if(request.equals("getObjects")){
                    System.out.println("пришла команда для запроса объекта");
                    Random randNum = new Random();
                    int num = randNum.nextInt(handlers.size()-1);
                    while (this == handlers.get(num)){
                        num = randNum.nextInt(handlers.size() - 1);
                    }
                    ClientHandler handl = handlers.get(num);
                    handl.sendMessage();
                }
                if(request.equals("wantToSend")){
                    Vector<Ant> ants = (Vector<Ant>) readObject.readObject();
                    System.out.println("на сервер пришло" + ants.toString());
                }

                writer.write(clients.size());
                System.out.println("Sent! " + socket.getInetAddress().getHostAddress() + ' ' + socket.getPort());
                for (int i = 0; i < clients.size(); i++) {
                    writer.write(clients.get(i).getInetAddress().getHostAddress() + ' ' + clients.get(i).getPort() + '\n');
                }

                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
