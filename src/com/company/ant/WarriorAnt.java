package com.company.ant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WarriorAnt extends ant{

   static private int quantity_ant = 0;//для отслеживания кол-ва объектов
   static public Image warrior_ant;

   public WarriorAnt(){
       setName("Warrior Ant");

       // случайный размер от 2 до 4 ед.
       Random rand = new Random();
       int size = rand.nextInt(2);
       setSize(size + 2);

       setSpawnTime(1000);
       setSpawnChance(100); // 70
       quantity_ant++;
   }

    public int GetQuantityAntThisGroup(){
        return quantity_ant;
    }

    @Override
    public void draw(Window window) {

        int x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 100);
        int y = ThreadLocalRandom.current().nextInt(0, window.getHeight() - this.getSize() * 100);
        this.setPosX(x);
        this.setPosY(y);

        window.getGraphics().drawImage(warrior_ant, x, y, this.getSize() * 100, this.getSize() * 100, null);
        System.out.println("Warrior X: " + x + " Y: " + y + " Quantity: " + quantity_ant);
    }
}
