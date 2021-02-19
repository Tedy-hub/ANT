package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.*;
import javax.imageio.*;


public class ControlPanel extends JPanel {

    private Image im;
    public ControlPanel()  {
        try {//тип обязательная проверка на правильность пути
            im = ImageIO.read(new File("D:\\REP_JAVA\\ANT-Project\\src\\com\\company\\Picture\\ant.png"));
            System.out.println("Изображение считалось");
        }catch (IOException e) { System.out.println("нет");}



    }
    //метод для отрисовки и расположения изображения
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.drawImage(im,22,22,1280,1024,null);
    }
}
