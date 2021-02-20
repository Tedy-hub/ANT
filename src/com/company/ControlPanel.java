package com.company;

import com.company.ant.ant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.*;


public class ControlPanel extends JPanel {

    private Image im;
    public ControlPanel()  {
        try {//тип обязательная проверка на правильность пути
            im = ImageIO.read(new File("src/com/company/Picture/ant.png"));
            System.out.println("Изображение считалось");
        }catch (IOException e) { System.out.println("нет");}

    }

    //метод для отрисовки и расположения изображения
    protected void paintComponent(Graphics g) {

        int x = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
        int y = ThreadLocalRandom.current().nextInt(0, 1000 + 1);

        super.paintComponent(g);
        g.drawImage(im,x,y,100,100,null);
    }

}
