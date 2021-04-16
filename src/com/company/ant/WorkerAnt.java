package com.company.ant;

import com.company.AntExample;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WorkerAnt extends Ant {

    enum status {
        goToGoal, goToBornPlace, onBornPlace
    }



    public static String staticName = "Worker Ant";
    static public int quantity_ant = 0;//для отслеживания кол-ва объектов
    static public Image worker_ant;
    status antStatus;
    int deltaY;
    int deltaX;

    public WorkerAnt(){
        setName("Worker Ant");

        // случайный размер от 1 до 3 ед.
        Random rand = new Random();
        int size = rand.nextInt(2);
        setSize(size + 1);
        antStatus = status.goToGoal;

        deltaX = 0;
        deltaY = 0;
        this.SetTimeBorn(AntExample.TimeSimulation);
        this.setId(rand.nextInt() & Integer.MAX_VALUE);

        quantity_ant++;
    }

    public int GetQuantityAntThisGroup(){
        return quantity_ant;
    }

    public static String getStaticName() { return staticName; }

    @Override
    public void draw(JPanel window) {
        // Рисует в 0, 0 если окно недостаточно большого размера
        int x = 0, y = 0;
        try {
            x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 65);
            y = ThreadLocalRandom.current().nextInt(window.getHeight() - window.getHeight(),
                    window.getHeight() - this.getSize() * 100);
        }
        catch(IllegalArgumentException ia){
         //   ia.printStackTrace();
            x = 0;
            y = window.getHeight() - window.getHeight();
        }
        this.setPosX(x);
        this.setPosY(y);
        this.setPositionBornX(x);
        this.setPositionBornY(y);

        window.getGraphics().drawImage(worker_ant, x, y, getSize() * 65, getSize() * 100, null);

        //System.out.println("Worker X: " + x + " Y: " + y + " Quantity: " + quantity_ant);
    }

    @Override
    public void run(int speed) {
        int posX = getPosX();
        int posY = getPosY();

        if(deltaX==0){
            int Y = Math.abs(posY-5);
            int X = Math.abs(posX-10);
            if(Y==0){
                deltaX = speed;
                deltaY = 0;
            } else {
                double xdelY = X/Y;
                deltaY = (int) Math.sqrt(Math.pow(speed,2)/(Math.pow(xdelY,2)+1));
                deltaX = (int) xdelY*deltaY;
            }
        }

        if(antStatus==status.goToGoal){
            if((posX<20)&&(posY<20)){
                //дошли, теперь идем домой
                antStatus = status.goToBornPlace;
            } else {
                //еще идем к цели
                this.setPosX(posX-deltaX);
                this.setPosY(posY-deltaY);
            }
        }
        if(antStatus==status.goToBornPlace){
            if((Math.abs(posX-getPositionBornX())<20)&&(Math.abs(posY-getPositionBornY())<20)){
                //вернулись в свое место рождения
                antStatus = status.onBornPlace;
            } else {
                this.setPosX(posX+deltaX);
                this.setPosY(posY+deltaY);
            }
        }
        if(antStatus==status.onBornPlace){

        }
        //проверяем дошли ли они до места назначения
        /*if((posX<20)&&(posY<20)){
            System.out.println("дошли");
            this.setPosX(posX+deltaX);
            this.setPosY(posY+deltaY);
        } else {
            this.setPosX(posX-deltaX);
            this.setPosY(posY-deltaY);
        }*/
    }


//    @Override
//    public void RespawnAnt(MyPanel window){
//        //window.setAntInfo(this);
//        window.repaint();
//
//        //window.getGraphics().drawImage(worker_ant, this.getPosX(), this.getPosY(), getSize() * 65, getSize() * 100, null);
//
//    }
}
