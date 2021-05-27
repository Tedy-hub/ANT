package com.company.ant;

import com.company.AntExample;
import com.company.MyPanel;
import com.company.Serializate;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WarriorAnt extends Ant implements Serializable {

    private static String staticName = "Warrior Ant";
    static public int quantity_ant = 0;//для отслеживания кол-ва объектов
    static public Image warrior_ant;
    private int center_x;
    private int center_y;
    private int radius;
    double angle_rad;
    public static int speed = 1;

    public WarriorAnt(){
       setName("Warrior Ant");

       radius = 30;
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
            y = ThreadLocalRandom.current().nextInt(0,
                    window.getHeight() - this.getSize() * 100);
        }
        catch(IllegalArgumentException ia){
           // ia.printStackTrace();
            x = 0;
            y = window.getHeight() - window.getHeight();
        }
        this.setPosX(x);
        this.setPosY(y);
        center_y = y;
        center_x = x;


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
        //ang_inc = Math.PI/24;
        angle_rad +=ang_inc;

        this.setPosX(Y);
        this.setPosY(X);
    }

    public int getCenter_x() {
        return center_x;
    }

    public int getCenter_y() {
        return center_y;
    }

    public double getAngle_rad() {
        return angle_rad;
    }

    public void setCenter_x(int center_x) {
        this.center_x = center_x;
    }

    public void setCenter_y(int center_y) {
        this.center_y = center_y;
    }

    public void setAngle_rad(double angle_rad) {
        this.angle_rad = angle_rad;
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
