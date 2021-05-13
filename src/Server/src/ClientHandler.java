package Server.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private OutputStreamWriter writer;
    private ArrayList<Socket> clients;

    public ClientHandler(ArrayList<Socket> clients)
    {
        this.socket = clients.get(clients.size()-1);
        try {
            writer = new OutputStreamWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clients = clients;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String request = reader.readLine();
                if(request.equals("Exit")){
                    System.out.println("Socket exits...");
                    clients.remove(socket);
                    writer.close();
                    reader.close();
                    socket.close();
                    break;
                }

                writer.write(clients.size());
                System.out.println("Sent! " + socket.getInetAddress().getHostAddress());
                for (int i = 0; i < clients.size(); i++) {
                    writer.write(clients.get(i).getInetAddress().getHostAddress() + '\n');
                }

                writer.flush();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
