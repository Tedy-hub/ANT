package com.company;

//import static com.company.ControlFrame.createGUI;
import com.company.ControlFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

    public static void main(String[] args) {
        JFrame window = new AntExample("Hello ant");
        window.setVisible(true);
        window.setSize(1000,1000);

        // Спавнит муравья по таймеру
        ControlFrame Controller = new ControlFrame(window);
        Controller.spawnAnt();
       //ControlFrame Controller = new ControlFrame();
       //Controller.CreateGui();

       
    }
}
