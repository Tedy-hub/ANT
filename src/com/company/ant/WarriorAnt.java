package com.company.ant;

import com.company.AntExample;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WarriorAnt extends Ant {

    private static String staticName = "Warrior Ant";
    static public int quantity_ant = 0;//для отслеживания кол-ва объектов
    static public Image warrior_ant;

   public WarriorAnt(){
       setName("Warrior Ant");

       // случайный размер от 2 до 4 ед.
       Random rand = new Random();
       int size = rand.nextInt(2);
       setSize(size + 2);

       quantity_ant++;
   }
    public static int GetQuantityAntThisGroup(){
        return quantity_ant;
    }

    public static String getStaticName(){ return staticName; }

    @Override
    public void draw(AntExample window) {

        int x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 100);
        int y = ThreadLocalRandom.current().nextInt(0, window.getHeight() - this.getSize() * 100);
        this.setPosX(x);
        this.setPosY(y);

        window.getGraphics().drawImage(warrior_ant, x, y, getSize() * 65, getSize() * 100, null);

        System.out.println("Warrior X: " + x + " Y: " + y + " Quantity: " + quantity_ant);

    }

    @Override
    public void RespawnAnt(AntExample window){

        window.getGraphics().drawImage(warrior_ant, this.getPosX(), this.getPosY(), getSize() * 65, getSize() * 100, null);

    }
}
