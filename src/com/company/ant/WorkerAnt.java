package com.company.ant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WorkerAnt extends ant {

    static private int quantity_ant = 0;//для отслеживания кол-ва объектов
    static public ArrayList<Image> worker_ant = new ArrayList<>();
    public int NumberPicture = 1;
    //static public Image worker_ant;

    public WorkerAnt(){
        setName("Worker Ant");

        // случайный размер от 1 до 3 ед.
        Random rand = new Random();
        int size = rand.nextInt(2);
        setSize(size + 1);

        setSpawnTime(1000);
        setSpawnChance(100); //80
        quantity_ant++;
    }

    public int GetQuantityAntThisGroup(){
        return quantity_ant;
    }

    @Override
    public void draw(Window window) {

        int x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 65);
        int y = ThreadLocalRandom.current().nextInt(0, window.getHeight() - this.getSize() * 105);
        this.setPosX(x);
        this.setPosY(y);

        Timer timer = new Timer(58, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        // *65 т.к. муравей-рабочий является прямоугольной картинкой
        if(NumberPicture<16)
            window.getGraphics().drawImage(worker_ant.get(NumberPicture), x, y, getSize() * 65, getSize() * 100, null);
        else return;

        NumberPicture++;
            }
        });
        timer.start();

        //System.out.println("Worker X: " + x + " Y: " + y + " Quantity: " + quantity_ant);
    }
    public void Respawn(Window window){

        window.getGraphics().drawImage(worker_ant.get(15), this.getPosX(), this.getPosY(), getSize() * 65, getSize() * 100, null);

    }
}
