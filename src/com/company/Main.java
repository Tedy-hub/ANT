package com.company;

//import static com.company.ControlFrame.createGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class Main {
    public static void main(String[] args) {
        AntExample window = new AntExample("Hello ant");
        window.setVisible(true);
        window.setSize(1000,1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.run();

    }
}
