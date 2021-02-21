package com.company.ant;

import java.util.Random;

public class WorkerAnt extends ant {

    static private int quantity_ant = 0;//для отслеживания кол-ва объектов

    public WorkerAnt(){
        Random rand = new Random();
        setName("Worker Ant");
        // случайный размер от 1 до 3 ед.
        int size = rand.nextInt(2);
        setSize(size + 1);
        setSpawnTime(2000);
        quantity_ant++;
    }

    public int GetQuantityAntThisGroup(){
        return quantity_ant;
    }
}
