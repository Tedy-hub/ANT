package com.company.ant;

import com.company.AntExample;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WorkerAnt extends Ant {

    static private int quantity_ant = 0;//для отслеживания кол-ва объектов
    static public Image worker_ant;

    public WorkerAnt(){
        setName("Worker Ant");

        // случайный размер от 1 до 3 ед.
        Random rand = new Random();
        int size = rand.nextInt(2);
        setSize(size + 1);

        quantity_ant++;
    }

    public int GetQuantityAntThisGroup(){
        return quantity_ant;
    }

    @Override
    public void draw(AntExample window) {

        int x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 65);
        int y = ThreadLocalRandom.current().nextInt(0, window.getHeight() - this.getSize() * 105);
        this.setPosX(x);
        this.setPosY(y);

        window.getGraphics().drawImage(worker_ant, x, y, getSize() * 65, getSize() * 100, null);

        System.out.println("Worker X: " + x + " Y: " + y + " Quantity: " + quantity_ant);
    }

    @Override
    public void RespawnAnt(AntExample window){

        window.getGraphics().drawImage(worker_ant, this.getPosX(), this.getPosY(), getSize() * 65, getSize() * 100, null);

    }
}
