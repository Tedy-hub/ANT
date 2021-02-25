package com.company;

//import static com.company.ControlFrame.createGUI;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class Main {

    public static void main(String[] args) {
        AntExample window = new AntExample("Hello ant");
        window.setVisible(true);
        window.setSize(1000,1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.run();
        //Habitat habitat = new Habitat(window, 1280, 1024);
        //habitat.spawnAnt();

        /*window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                habitat.RespownAllAnts();
            }
        });*/

       //ControlFrame Controller = new ControlFrame();
       //Controller.CreateGui();

       
    }
}
