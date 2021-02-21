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

    private Image solder_ant;
    private Image builder_ant;

    public ControlPanel()  {
        try {//тип обязательная проверка на правильность пути
            builder_ant = ImageIO.read(new File("src/com/company/Picture/builder.png"));
            System.out.println("Изображение считалось");
        }catch (IOException e) { System.out.println("нет");}


        try {//тип обязательная проверка на правильность пути
            solder_ant = ImageIO.read(new File("src/com/company/Picture/solder.png"));
            System.out.println("Изображение считалось");
        }catch (IOException e) { System.out.println("нет");}

    }

    //метод для отрисовки и расположения изображения
    protected void paintComponent(Graphics g) {
        int random_Kod = ThreadLocalRandom.current().nextInt(0, 10+1);

        //вероятность 70/30
        if(random_Kod > 3){

            int x = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
            int y = ThreadLocalRandom.current().nextInt(0, 1000 + 1);

            super.paintComponent(g);
            g.drawImage(builder_ant,x,y,100,100,null);

        }
        else{

            int x = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
            int y = ThreadLocalRandom.current().nextInt(0, 1000 + 1);

            super.paintComponent(g);
            g.drawImage(solder_ant,x,y,100,100,null);
        }

    }

}
