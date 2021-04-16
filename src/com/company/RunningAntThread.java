package com.company;

import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ClientInfoStatus;
import java.util.Vector;

public class RunningAntThread extends Thread {
    private boolean isWorked;
    private Vector<Ant> vector = AntExample.list;
    private String type;
    private MyPanel window;
    private int speed;

    public RunningAntThread(MyPanel window, String type, int speed) {
        this.window = window;
        this.type = type;
        this.speed = speed;
    }

    public void rise(){
        synchronized (this) {
            this.notify();
        }
    }


    public void stopThread() {
        isWorked = false;
    }

    public void run() {

        isWorked = true;
            while (isWorked) {
                checkAndRunAnt();
                window.repaint();
                sleeping(10);
            }
    }

    public void sleeping(int millsec){
        try {
            this.sleep(millsec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //проходит по вектору, проверяет есть ли тут муравьи данного типа и запускают его
    public void checkAndRunAnt(){
        for (Ant antObject : vector) {
            if (antObject.getName() == type)
                antObject.run(speed);
        }
    }
}