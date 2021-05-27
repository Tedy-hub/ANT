package com.company;

import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.*;

public class GenerateThread extends Thread {
    private JPanel window;

    String antName;
    Ant ant;
    private double P;
    private int N;
    private boolean isWorked = true;

    public GenerateThread(JPanel window, double P, int N, String ant) {
        this.window = window;
        this.P = P;
        this.N = N;

        antName = ant;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    public void run() {

        while (isWorked){

            if(Math.random()<P) {
                System.out.println(Math.random());
                if (antName == WarriorAnt.getStaticName()) {
                    ant = new WarriorAnt();
                    ant.setTimeLive(AntExample.TimeLivingWarrior);
                    AntExample.BornList.put(ant.getId(), AntExample.TimeSimulation);//добавление времени жизни
                } else {
                    ant = new WorkerAnt();
                    ant.setTimeLive(AntExample.TimeLivingWorker);
                    AntExample.BornList.put(ant.getId(), AntExample.TimeSimulation);//добавление времени жизни
                }

                if(isWorked) {
                    ant.draw(window);
                    AntExample.list.add(ant);//добавление объекта
                    AntExample.idList.add(ant.getId());//добавление id
                }
            }

            try {
                this.sleep(N);
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.interrupt();
            }
        }
    }

    public void stopThread() {
        isWorked = false;
    }
}






