package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class ControlFrame extends JFrame{
    private JFrame window;

    ControlFrame(JFrame _window){
        window = _window;
    }

        public void spawnAnt(){
            // логика полсе нажатия на кнопку
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //показывает картинку
                    ControlPanel Cpanel = new ControlPanel(window);

                    // Вот эта дичь рисует не на панели, а на graphics окна
                    Cpanel.paintComponent(window.getGraphics());
                    System.out.println("Действие проходит");
                }
            });
            timer.start();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

}
