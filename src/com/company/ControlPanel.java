package com.company;

import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;
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

    private Image soldier_ant;
    private Image builder_ant;
    private final Window window;

    public ControlPanel(Window _window)
    {
        window = _window;
        try {//тип обязательная проверка на правильность пути
            builder_ant = ImageIO.read(new File("src/com/company/Picture/builder.png"));
            System.out.println("Изображение считалось");
        }catch (IOException e) { System.out.println("нет");}


        try {//тип обязательная проверка на правильность пути
            soldier_ant = ImageIO.read(new File("src/com/company/Picture/soldier.png"));
            System.out.println("Изображение считалось");
        }catch (IOException e) { System.out.println("нет");}

    }

    //метод для отрисовки и расположения изображения
    protected void paintComponent(Graphics g) {
            int random_Kod = ThreadLocalRandom.current().nextInt(0, 10);

        //вероятность 70/30
        if(random_Kod > 3){
            WorkerAnt workerAnt = new WorkerAnt();

            int x = ThreadLocalRandom.current().nextInt(workerAnt.getSize()*65, window.getWidth() - workerAnt.getSize()*65);
            int y = ThreadLocalRandom.current().nextInt(workerAnt.getSize()*100, window.getHeight() - workerAnt.getSize()*100);

            super.paintComponent(g);
            // *65 т.к. муравей-рабочий является прямоугольной картинкой
            g.drawImage(builder_ant,x,y,workerAnt.getSize()*65,workerAnt.getSize()*100,null);

            System.out.println("Рабочие: " + workerAnt.GetQuantityAntThisGroup());

        }
        else{
            WarriorAnt warriorAnt = new WarriorAnt();

            int x = ThreadLocalRandom.current().nextInt(warriorAnt.getSize()*100, window.getWidth() - warriorAnt.getSize()*100);
            int y = ThreadLocalRandom.current().nextInt(warriorAnt.getSize()*100, window.getHeight() - warriorAnt.getSize()*100);

            super.paintComponent(g);
            g.drawImage(soldier_ant,x,y,warriorAnt.getSize()*100,warriorAnt.getSize()*100,null);

            System.out.println("Солдаты: " + warriorAnt.GetQuantityAntThisGroup());

        }

    }

}
