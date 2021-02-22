package com.company;

//import static com.company.ControlFrame.createGUI;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        JFrame window = new AntExample("Hello ant");
        window.setVisible(true);
        // Спавнит муравья по таймеру
        Habitat habitat = new Habitat(window, 1280, 1024);
        habitat.spawnAnt();

       //ControlFrame Controller = new ControlFrame();
       //Controller.CreateGui();

       
    }
}
