package com.company.ant;

import com.company.AntExample;
import com.company.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WarriorAnt extends Ant {

    private static String staticName = "Warrior Ant";
    static public int quantity_ant = 0;//для отслеживания кол-ва объектов
    static public Image warrior_ant;
    private int center_x;
    private int center_y;
    private int radius = 30;
    double angle_rad;

    public WarriorAnt(){
       setName("Warrior Ant");

       // случайный размер от 2 до 4 ед.
       Random rand = new Random();
       int size = rand.nextInt(2);
       setSize(size + 2);

       angle_rad = 0;
       this.SetTimeBorn(AntExample.TimeSimulation);
       this.setId(rand.nextInt() & Integer.MAX_VALUE);

       quantity_ant++;
   }
    public static int GetQuantityAntThisGroup(){
        return quantity_ant;
    }

    public static String getStaticName(){ return staticName; }

    @Override
    public void draw(JPanel window) {
        // Рисует в 0, 0 если окно недостаточно большого размера

        int x = 0, y = 0;
        try {
            x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 100);
            y = ThreadLocalRandom.current().nextInt(window.getHeight() - window.getHeight(),
                    window.getHeight() - this.getSize() * 100);
        }
        catch(IllegalArgumentException ia){
           // ia.printStackTrace();
            x = 0;
            y = window.getHeight() - window.getHeight();;
        }
        this.setPosX(x);
        this.setPosY(y);
        center_y = x;
        center_x = y;


        window.getGraphics().drawImage(warrior_ant, getPosX(), getPosY(), getSize() * 100, getSize() * 100, null);

        //System.out.println("Warrior X: " + x + " Y: " + y + " Quantity: " + quantity_ant);

    }

    @Override
    public void run(int speed) {

        int omega = (int) ((int) 2*Math.PI*speed);//в данном случае speed это частота

        int Y = (int) (center_x+radius * Math.sin(angle_rad));
        int X = (int) (center_y+radius * Math.cos(angle_rad));

        int fps_avg = 1;//число обновлений в секунду
        double ang_inc = omega/fps_avg;
        ang_inc = Math.PI/24;
        angle_rad +=ang_inc;

        this.setPosX(Y);
        this.setPosY(X);
    }

//    @Override
//    public void RespawnAnt(MyPanel window){
//        //window.setAntInfo(this);
//        window.repaint();
//        //window.setAntInfo(this.getPosX(), this.getPosY(), getSize() * 100, getSize() * 100);
//
//       // window.getGraphics().drawImage(warrior_ant, this.getPosX(), this.getPosY(), getSize() * 100, getSize() * 100, null);
//
//    }
//
}
