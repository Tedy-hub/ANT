package com.company.ant;

import java.util.Random;

public class WarriorAnt extends ant{

   static private int quantity_ant = 0;//для отслеживания кол-ва объектов

   public WarriorAnt(){
       Random rand = new Random();
       setName("Warrior Ant");
       // случайный размер от 2 до 4 ед.
       int size = rand.nextInt(2);
       setSize(size + 2);
       setSpawnTime(1000);
       quantity_ant++;
   }

    public int GetQuantityAntThisGroup(){
        return quantity_ant;
    }
}
