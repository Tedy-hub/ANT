package com.company;

import com.company.ant.WorkerAnt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        AntExample window = new AntExample("ЪУЪ");
        window.setVisible(true);
        Image icon = null;

        try {
            icon = ImageIO.read(new File("src/com/company/Picture/logo.png"));
        } catch (IOException e) {
            System.out.println("нет");
        }

        window.setIconImage(icon);
        window.setSize(1000,1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
