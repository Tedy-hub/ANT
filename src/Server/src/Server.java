package Server.src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        ArrayList<Socket> clients = new ArrayList<>();
        ArrayList<ClientHandler> handlers = new ArrayList<>();

        try{
            ServerSocket servSocket = new ServerSocket(4000);
            System.out.println("Started!");

            while (!servSocket.isClosed()) {
                Socket client = servSocket.accept();
                System.out.println("Connect! " + client.getInetAddress().getHostAddress() + ' ' + client.getPort() + ' ' + clients.size());

                clients.add(client);

                ClientHandler thread = new ClientHandler(clients,handlers);
                handlers.add(thread);
                thread.start();
            }
        } catch(Exception e){ e.printStackTrace(); }
    }
}
