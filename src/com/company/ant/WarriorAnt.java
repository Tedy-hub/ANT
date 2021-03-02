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
        // Рисует в 0, 0 если окно недостаточно большого размера

        int x = 0, y = 0;
        try {
            x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 100);
            y = ThreadLocalRandom.current().nextInt(window.getHeight() - window.getContentPane().getHeight(),
                    window.getHeight() - this.getSize() * 100);
        }
        catch(IllegalArgumentException ia){
           // ia.printStackTrace();
            x = 0;
            y = window.getHeight() - window.getContentPane().getHeight();;
        }
        this.setPosX(x);
        this.setPosY(y);

        window.getGraphics().drawImage(warrior_ant, x, y, getSize() * 100, getSize() * 100, null);

        System.out.println("Warrior X: " + x + " Y: " + y + " Quantity: " + quantity_ant);

    }

    @Override
    public void RespawnAnt(AntExample window){

        window.getGraphics().drawImage(warrior_ant, this.getPosX(), this.getPosY(), getSize() * 100, getSize() * 100, null);

    }
}
