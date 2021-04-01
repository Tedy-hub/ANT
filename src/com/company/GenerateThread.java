package com.company;

import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

import javax.swing.*;

public class GenerateThread extends Thread {
    private JPanel window;
    private ArrayList<Ant> antlist;
    private HashSet<Integer> idList;
    private TreeMap<Integer,Integer> BornList;

    String antName;
    Ant ant;
    private double P;
    private int N;

    public GenerateThread(JPanel window, double P, int N, String ant, ArrayList<Ant> antlist, HashSet<Integer> idList, TreeMap<Integer,Integer> BornList) {
        this.window = window;
        this.P = P;
        this.N = N;

        this.antlist = antlist;
        this.idList = idList;
        this.BornList = BornList;

        antName = ant;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    public void run() {

        while (!this.isInterrupted()){

            if(Math.random()<P) {
                System.out.println(Math.random());
                if (antName == WarriorAnt.getStaticName()) {
                    ant = new WarriorAnt();
                    ant.SetTimeLive(AntExample.TimeLivingWarrior);
                    this.BornList.put(ant.getId(), AntExample.TimeSimulation);//добавление времени жизни
                } else {
                    ant = new WorkerAnt();
                    ant.SetTimeLive(AntExample.TimeLivingWorker);
                    this.BornList.put(ant.getId(), AntExample.TimeSimulation);//добавление времени жизни
                }
                if(!this.isInterrupted()) {
                    ant.draw(window);
                    antlist.add(ant);//добавление объекта
                    idList.add(ant.getId());//добавление id
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
}






