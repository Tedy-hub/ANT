package com.company.ant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WorkerAnt extends ant {

    static private int quantity_ant = 0;//для отслеживания кол-ва объектов
    static public Image worker_ant;

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

        // *65 т.к. муравей-рабочий является прямоугольной картинкой
        window.getGraphics().drawImage(worker_ant, x, y, this.getSize() * 65, this.getSize() * 100, null);
        System.out.println("Worker X: " + x + " Y: " + y + " Quantity: " + quantity_ant);
    }
}
