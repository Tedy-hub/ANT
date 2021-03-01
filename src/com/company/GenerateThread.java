package com.company;

import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;
import com.company.ant.Ant;

import java.util.ArrayList;

import javax.swing.*;

public class GenerateThread extends Thread {
    private AntExample window;
    private ArrayList<Ant> antlist;
    Ant ant;
    private double P;
    private int N;

    public GenerateThread(AntExample window, double P, int N, Ant ant, ArrayList<Ant> antlist) {
        this.window = window;
        this.P = P;
        this.N = N;
        this.antlist = antlist;
        this.ant = ant;
    }

    public void run() {
        //рисуем муравьев
        while (!this.isInterrupted()){
            try {
                this.sleep(N);
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.interrupt();
            }
            System.out.println("есть");
            if(Math.random()<P) {
                System.out.println(Math.random());
                if (ant.getName() == "Warrior Ant") {
                    ant = new WarriorAnt();
                } else {
                    ant = new WorkerAnt();
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ant.draw(window);
                    }
                });
                antlist.add(ant);
            }
        }
    }
}






