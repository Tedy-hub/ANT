package com.company.ant;

import com.company.AntExample;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WorkerAnt extends Ant implements Serializable {

    enum status {
        goToGoal, goToBornPlace, onBornPlace
    }

    public static String staticName = "Worker Ant";
    static public int quantity_ant = 0;//для отслеживания кол-ва объектов
    static public Image worker_ant;
    status antStatus;
    int deltaY;
    int deltaX;
    public static int speed = 10;

    public WorkerAnt(){
        setName("Worker Ant");

        // случайный размер от 1 до 3 ед.
        Random rand = new Random();
        int size = rand.nextInt(2);
        setSize(size + 1);
        antStatus = status.goToGoal;

        deltaX = -1000;
        deltaY = 0;
        this.setTimeBorn(AntExample.TimeSimulation);
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

        //if(deltaX==-1000){
            int Y = Math.abs(posY-10);
            int X = Math.abs(posX-10);
            if(Y==0){
                deltaX = speed;
                deltaY = 0;
            } else {
                double xdelY = X/Y;
                deltaY = (int) Math.sqrt(Math.pow(speed,2)/(Math.pow(xdelY,2)+1));
                deltaX =  (int) xdelY*deltaY;
            }
        //}

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
            if((Math.abs(posX-getPositionBornX())<50)&&(Math.abs(posY-getPositionBornY())<50)){
                //вернулись в свое место рождения
                antStatus = status.onBornPlace;
            } else {
                runRevert(speed);
            //    this.setPosX(posX+deltaX);
              //  this.setPosY(posY+deltaY);
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
    public void runRevert(int speed){
        int posX = getPositionBornX();
        int posY = getPositionBornY();

        //if(deltaX==-1000){
        int Y = Math.abs(posY-getPosY());
        int X = Math.abs(posX-getPosX());

        if(Y==0){
            deltaX = speed;
            deltaY = 0;
        } else {
            double xdelY = X/Y;
            deltaY = (int) Math.sqrt(Math.pow(speed,2)/(Math.pow(xdelY,2)+1));
            deltaX =  (int) xdelY*deltaY;
        }

        this.setPosX(getPosX()+deltaX);
        this.setPosY(getPosY()+deltaY);
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    @Override
    public Image getImg() {
        return worker_ant;
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
