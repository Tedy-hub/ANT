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




    public void CreateGui(){
        // создаем окно
       JFrame Window = new JFrame("ANT-HOME");
       Window.setVisible(true);
       Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Window.setSize(1280,1024);

        JPanel panel = new JPanel();
        Window.add(panel);

       //создаем кнопку START
        JButton START = new JButton("START");
        START.setBackground(Color.ORANGE);
        panel.add(START);

        // логика полсе нажатия на кнопку
        START.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //показывает картинку
                ControlPanel Cpanel = new ControlPanel();
                Window.getContentPane().add(Cpanel);
                Window.show();
                System.out.println("Действие проходит");

            }
        });

        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.setVisible(true);

    }

}
