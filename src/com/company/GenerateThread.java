package com.company;

import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;
import com.company.ant.Ant;

import java.util.ArrayList;

import javax.swing.*;

public class GenerateThread extends Thread {
    private JPanel window;
    private ArrayList<Ant> antlist;
    String antName;
    Ant ant;
    private double P;
    private int N;

    public GenerateThread(JPanel window, double P, int N, String ant, ArrayList<Ant> antlist) {
        this.window = window;
        this.P = P;
        this.N = N;
        this.antlist = antlist;
        antName = ant;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    public void run() {
        //рисуем муравьев
        while (!this.isInterrupted()){
            System.out.println("есть");
            if(Math.random()<P) {
                System.out.println(Math.random());
                if (antName == WarriorAnt.getStaticName()) {
                    ant = new WarriorAnt();
                } else {
                    ant = new WorkerAnt();
                }
                // вроде работает но какой-то костыль, чтобы при нажатии E не продолжжалась отрисовка
                if(!this.isInterrupted()) {
                    ant.draw(window);
                    antlist.add(ant);
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






